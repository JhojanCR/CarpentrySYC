package com.syc.carpentry.controller;

import com.syc.carpentry.model.Servicio;
import com.syc.carpentry.service.ServicioService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@RestController
@RequestMapping("/api/servicios")
@CrossOrigin(origins = "*") // para permitir peticiones en el frontend después

public class ServicioController {

    @Autowired
    private ServicioService servicioService;

    @GetMapping
    public List<Servicio> listar(){
        return servicioService.listarTodos();
    }

    @PostMapping
    public Servicio guardar(@RequestBody Servicio servicio){
        return servicioService.guardar(servicio);
    }

    @GetMapping("/{id}")
    public void eliminar(@PathVariable Long id){
        servicioService.eliminar(id);
    }

}
