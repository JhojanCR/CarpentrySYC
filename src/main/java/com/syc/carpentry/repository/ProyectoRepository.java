package com.syc.carpentry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.syc.carpentry.model.Proyecto;
import org.springframework.stereotype.Repository;
import java.util.List;

public interface ProyectoRepository extends JpaRepository<Proyecto, Long> {

    // Obtener los últimos 5 proyectos ordenados por ID descendente
    List<Proyecto> findTop5ByOrderByIdDesc();

}
