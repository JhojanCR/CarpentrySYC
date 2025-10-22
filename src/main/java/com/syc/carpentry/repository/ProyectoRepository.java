package com.syc.carpentry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.syc.carpentry.model.Proyecto;
import org.springframework.stereotype.Repository;

public interface ProyectoRepository extends JpaRepository<Proyecto, Long> {

}
