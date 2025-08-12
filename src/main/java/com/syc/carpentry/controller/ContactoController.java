package com.syc.carpentry.controller;

import com.syc.carpentry.model.MensajeContacto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contacto")

public class ContactoController {

    @PostMapping
    public String recibirMensaje(@RequestBody MensajeContacto mensajeContacto){
        System.out.println("Mensaje recibido");
        System.out.println("Asunto: " + mensajeContacto.getAsunto());
        System.out.println("Nombre: " + mensajeContacto.getNombre());
        System.out.println("Correo: " + mensajeContacto.getCorreo());
        System.out.println("Celular: " + mensajeContacto.getTelefono());
        System.out.println("Mensaje: " + mensajeContacto.getMensaje());

        return "Mensaje recibido correctamente.";
    }
}
