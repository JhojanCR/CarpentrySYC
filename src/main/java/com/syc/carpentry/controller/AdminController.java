package com.syc.carpentry.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    
    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        // Datos de ejemplo para el dashboard
        model.addAttribute("serviciosCount", 8);
        model.addAttribute("proyectosCount", 15);
        model.addAttribute("contactosCount", 23);
        model.addAttribute("usuariosCount", 5);
        model.addAttribute("pageTitle", "Dashboard - Admin SYC");
        
        return "admin/dashboard";
    }
    
    @GetMapping("/servicios")
    public String servicios(Model model) {
        model.addAttribute("pageTitle", "Servicios - Admin SYC");
        return "admin/servicios";
    }
    
    @GetMapping("/proyectos")
    public String proyectos(Model model) {
        model.addAttribute("pageTitle", "Proyectos - Admin SYC");
        return "admin/proyectos";
    }
    
    @GetMapping("/contactos")
    public String contactos(Model model) {
        model.addAttribute("pageTitle", "Contactos - Admin SYC");
        return "admin/contactos";
    }
    
    @GetMapping("/usuarios")
    public String usuarios(Model model) {
        model.addAttribute("pageTitle", "Usuarios - Admin SYC");
        return "admin/usuarios";
    }
}
