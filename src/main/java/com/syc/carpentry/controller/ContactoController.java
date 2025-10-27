package com.syc.carpentry.controller;

import com.syc.carpentry.model.MensajeContacto;
import com.syc.carpentry.repository.ContactoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/contacto")
public class ContactoController {

    @Autowired
    private ContactoRepository contactoRepository;

    @PostMapping
    public ResponseEntity<String> recibirMensaje(@RequestBody MensajeContacto mensajeContacto){
        try {
            // Establecer la fecha de envío automáticamente
            mensajeContacto.setFechaEnvio(LocalDateTime.now());

            // Guardar en la base de datos
            contactoRepository.save(mensajeContacto);

            System.out.println("Mensaje recibido y guardado en BD");
            System.out.println("Nombre: " + mensajeContacto.getNombre());
            System.out.println("Correo: " + mensajeContacto.getCorreo());
            System.out.println("Asunto: " + mensajeContacto.getAsunto());

            return ResponseEntity.ok("Mensaje recibido y guardado correctamente.");
        } catch (Exception e) {
            System.err.println("Error al guardar mensaje: " + e.getMessage());
            return ResponseEntity.status(500).body("Error al procesar el mensaje.");
        }
    }
}
