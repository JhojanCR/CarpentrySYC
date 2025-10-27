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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired private UsuarioRepository usuarioRepository;
    @Autowired private ProyectoRepository proyectoRepository;
    @Autowired private ServicioRepository servicioRepository;
    @Autowired private ContactoRepository contactoRepository;

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

        return "admin/dashboard";
    }

    // --- CRUD de Servicios ---
    @GetMapping("/servicios")
    public String servicios(Model model) {
        model.addAttribute("pageTitle", "Servicios - Admin SYC");
        model.addAttribute("servicios", servicioRepository.findAll());
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
    public String proyectos(Model model) {
        model.addAttribute("pageTitle", "Proyectos - Admin SYC");
        model.addAttribute("proyectos", proyectoRepository.findAll());
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
        // NOTA: En un proyecto real, aquí deberías encriptar la contraseña
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
    public String contactos(Model model) {
        model.addAttribute("pageTitle", "Contactos - Admin SYC");
        model.addAttribute("contactos", contactoRepository.findAll());
        return "admin/contactos";
    }
    
    @GetMapping("/contactos/eliminar/{id}")
    public String eliminarContacto(@PathVariable Long id) {
        contactoRepository.deleteById(id);
        return "redirect:/admin/contactos";
    }
}