package com.syc.carpentry.service;

import com.syc.carpentry.model.Servicio;
import com.syc.carpentry.repository.ServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioService {

    @Autowired
    private ServicioRepository repo;

    public List<Servicio> listarTodos(){
        return repo.findAll();
    }

    public Servicio guardar(Servicio servicio){
        return repo.save(servicio);
    }

    public Servicio buscarPorId(Long id){
        return repo.findById(id).orElse(null);
    }

    public void eliminar(Long id){
        repo.deleteById(id);
    }

}
