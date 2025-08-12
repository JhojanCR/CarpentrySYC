package com.syc.carpentry.controller;

// Anotación que indica que esta clase es un controlador
import org.springframework.stereotype.Controller;

// Anotación que indica que esta clase es un controlador
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PaginasController {

    // Mapea la URL "/" a la vista "index"
    @GetMapping("/")
    public String index() {
        return "index";
    }

    // Mapea la URL "/nosotros" a la vista "nosotros"
    @GetMapping("/nosotros")
    public String nosotros() {
        return "nosotros";
    }

    // Mapea la URL "/contacto" a la vista "contacto"
    @GetMapping("/contacto")
    public String contacto() {
        return "contacto";
    }

    // Mapea la URL "/servicios" a la vista "servicios"
    @GetMapping("/servicios")
    public String servicios() {
        return "servicios";
    }
}
