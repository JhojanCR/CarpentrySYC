package com.syc.carpentry.controller;

import com.syc.carpentry.model.Usuario;
import com.syc.carpentry.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class UsuarioController {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    @GetMapping("/register")
    public String mostrarRegistro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "auth/register";
    }

    @PostMapping("/register")
    public String registrar(@ModelAttribute Usuario usuario) {
        usuario.setRol("USER"); // por defecto
        usuarioRepository.save(usuario);
        return "redirect:/auth/login";
    }

}

