package com.syc.carpentry.controller;

import com.syc.carpentry.model.MensajeContacto;
import com.syc.carpentry.model.Proyecto;
import com.syc.carpentry.model.Servicio;
import com.syc.carpentry.model.Usuario;
import com.syc.carpentry.repository.ContactoRepository;
import com.syc.carpentry.repository.ProyectoRepository;
import com.syc.carpentry.repository.ServicioRepository;
import com.syc.carpentry.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired private UsuarioRepository usuarioRepository;
    @Autowired private ProyectoRepository proyectoRepository;
    @Autowired private ServicioRepository servicioRepository;
    @Autowired private ContactoRepository contactoRepository;
    @Autowired private PasswordEncoder passwordEncoder;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("pageTitle", "Dashboard - Admin SYC");

        // Estadísticas generales
        model.addAttribute("serviciosCount", servicioRepository.count());
        model.addAttribute("proyectosCount", proyectoRepository.count());
        model.addAttribute("contactosCount", contactoRepository.count());
        model.addAttribute("usuariosCount", usuarioRepository.count());

        // Actividad reciente (últimos 5 proyectos y 5 contactos)
        model.addAttribute("proyectosRecientes", proyectoRepository.findTop5ByOrderByIdDesc());
        model.addAttribute("contactosRecientes", contactoRepository.findTop5ByOrderByFechaEnvioDesc());

        // Datos para gráficos de los últimos 6 meses
        List<String> mesesLabels = new ArrayList<>();
        List<Long> serviciosPorMes = new ArrayList<>();
        List<Long> proyectosPorMes = new ArrayList<>();

        LocalDate hoy = LocalDate.now();

        // Generar datos de los últimos 6 meses
        for (int i = 5; i >= 0; i--) {
            LocalDate mesActual = hoy.minusMonths(i);
            LocalDate inicioDeMes = mesActual.withDayOfMonth(1);
            LocalDate finDeMes = inicioDeMes.plusMonths(1);

            // Nombre del mes en español
            String nombreMes = mesActual.getMonth().getDisplayName(TextStyle.FULL, new Locale("es", "ES"));
            nombreMes = nombreMes.substring(0, 1).toUpperCase() + nombreMes.substring(1);
            mesesLabels.add(nombreMes);

            // Contar servicios creados en ese mes
            LocalDateTime inicioMesDateTime = inicioDeMes.atStartOfDay();
            LocalDateTime finMesDateTime = finDeMes.atStartOfDay();
            Long countServicios = servicioRepository.countByFechaCreacionBetween(inicioMesDateTime, finMesDateTime);
            serviciosPorMes.add(countServicios != null ? countServicios : 0L);

            // Contar proyectos completados en ese mes
            Long countProyectos = proyectoRepository.countCompletadosByFechaInicioBetween(inicioDeMes, finDeMes);
            proyectosPorMes.add(countProyectos != null ? countProyectos : 0L);
        }

        model.addAttribute("mesesLabels", mesesLabels);
        model.addAttribute("serviciosPorMes", serviciosPorMes);
        model.addAttribute("proyectosPorMes", proyectosPorMes);

        return "admin/dashboard";
    }

    // --- CRUD de Servicios ---
    @GetMapping("/servicios")
    public String servicios(@RequestParam(required = false) String busqueda, Model model) {
        model.addAttribute("pageTitle", "Servicios - Admin SYC");

        // Si hay búsqueda, filtrar; si no, mostrar todos
        List<Servicio> servicios;
        if (busqueda != null && !busqueda.trim().isEmpty()) {
            // Intentar buscar por ID si es numérico
            try {
                Long id = Long.parseLong(busqueda.trim());
                servicios = servicioRepository.findById(id)
                    .map(List::of)  // Convierte Optional<Servicio> en List<Servicio>
                    .orElse(List.of());  // Si no existe, lista vacía
            } catch (NumberFormatException e) {
                // Si no es numérico, buscar por nombre
                servicios = servicioRepository.buscarPorNombre(busqueda);
            }
            model.addAttribute("busqueda", busqueda);  // Para mantener el valor en el input
        } else {
            servicios = servicioRepository.findAll();
        }

        model.addAttribute("servicios", servicios);
        model.addAttribute("servicioNuevo", new Servicio());
        return "admin/servicios";
    }

    @PostMapping("/servicios/guardar")
    public String guardarServicio(@ModelAttribute Servicio servicio) {
        servicioRepository.save(servicio);
        return "redirect:/admin/servicios";
    }

    @GetMapping("/servicios/eliminar/{id}")
    public String eliminarServicio(@PathVariable Long id) {
        servicioRepository.deleteById(id);
        return "redirect:/admin/servicios";
    }

    // --- CRUD de Proyectos ---
    @GetMapping("/proyectos")
    public String proyectos(@RequestParam(required = false) String busqueda, Model model) {
        model.addAttribute("pageTitle", "Proyectos - Admin SYC");

        // Si hay búsqueda, filtrar; si no, mostrar todos
        List<Proyecto> proyectos;
        if (busqueda != null && !busqueda.trim().isEmpty()) {
            // Intentar buscar por ID si es numérico
            try {
                Long id = Long.parseLong(busqueda.trim());
                proyectos = proyectoRepository.findById(id)
                    .map(List::of)
                    .orElse(List.of());
            } catch (NumberFormatException e) {
                // Si no es numérico, buscar por nombre
                proyectos = proyectoRepository.buscarPorNombre(busqueda);
            }
            model.addAttribute("busqueda", busqueda);
        } else {
            proyectos = proyectoRepository.findAll();
        }

        model.addAttribute("proyectos", proyectos);
        model.addAttribute("proyectoNuevo", new Proyecto());
        return "admin/proyectos";
    }
    
    @PostMapping("/proyectos/guardar")
    public String guardarProyecto(@ModelAttribute Proyecto proyecto) {
        proyectoRepository.save(proyecto);
        return "redirect:/admin/proyectos";
    }

    @GetMapping("/proyectos/eliminar/{id}")
    public String eliminarProyecto(@PathVariable Long id) {
        proyectoRepository.deleteById(id);
        return "redirect:/admin/proyectos";
    }

    // --- CRUD de Usuarios ---
    @GetMapping("/usuarios")
    public String usuarios(Model model) {
        model.addAttribute("pageTitle", "Usuarios - Admin SYC");
        model.addAttribute("usuarios", usuarioRepository.findAll());
        model.addAttribute("usuarioNuevo", new Usuario());
        return "admin/usuarios";
    }

    @PostMapping("/usuarios/guardar")
    public String guardarUsuario(@ModelAttribute Usuario usuario) {
        // Si es una actualización sin cambio de contraseña, mantener la anterior
        if (usuario.getId() != null && (usuario.getPassword() == null || usuario.getPassword().isEmpty())) {
            Usuario usuarioExistente = usuarioRepository.findById(usuario.getId()).orElse(null);
            if (usuarioExistente != null) {
                usuario.setPassword(usuarioExistente.getPassword());
            }
        }
        // Guardar contraseña en texto plano (sin encriptación)
        usuarioRepository.save(usuario);
        return "redirect:/admin/usuarios";
    }

    @GetMapping("/usuarios/eliminar/{id}")
    public String eliminarUsuario(@PathVariable Long id) {
        usuarioRepository.deleteById(id);
        return "redirect:/admin/usuarios";
    }

    // --- Gestión de Contactos (Leer y Eliminar) ---
    @GetMapping("/contactos")
    public String contactos(@RequestParam(required = false) String busqueda, Model model) {
        model.addAttribute("pageTitle", "Contactos - Admin SYC");

        // Si hay búsqueda, filtrar; si no, mostrar todos
        List<MensajeContacto> contactos;
        if (busqueda != null && !busqueda.trim().isEmpty()) {
            // Intentar buscar por ID si es numérico
            try {
                Long id = Long.parseLong(busqueda.trim());
                contactos = contactoRepository.findById(id)
                    .map(List::of)
                    .orElse(List.of());
            } catch (NumberFormatException e) {
                // Si no es numérico, buscar por nombre o asunto
                contactos = contactoRepository.buscarPorNombreOAsunto(busqueda);
            }
            model.addAttribute("busqueda", busqueda);
        } else {
            contactos = contactoRepository.findAll();
        }

        model.addAttribute("contactos", contactos);
        return "admin/contactos";
    }
    
    @GetMapping("/contactos/eliminar/{id}")
    public String eliminarContacto(@PathVariable Long id) {
        contactoRepository.deleteById(id);
        return "redirect:/admin/contactos";
    }
}