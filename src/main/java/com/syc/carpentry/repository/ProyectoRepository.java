package com.syc.carpentry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.syc.carpentry.model.Proyecto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

public interface ProyectoRepository extends JpaRepository<Proyecto, Long> {

    // Obtener los últimos 5 proyectos ordenados por ID descendente
    List<Proyecto> findTop5ByOrderByIdDesc();

    // Contar proyectos completados en un rango de fechas
    @Query("SELECT COUNT(p) FROM Proyecto p WHERE p.estado = 'Completado' AND p.fechaInicio >= :startDate AND p.fechaInicio < :endDate")
    Long countCompletadosByFechaInicioBetween(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    // Obtener lista de proyectos en un rango de fechas
    @Query("SELECT p FROM Proyecto p WHERE p.fechaInicio >= :startDate AND p.fechaInicio < :endDate ORDER BY p.fechaInicio DESC")
    List<Proyecto> findByFechaInicioBetween(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    // Buscar proyectos por nombre (búsqueda parcial, ignora mayúsculas/minúsculas)
    @Query("SELECT p FROM Proyecto p WHERE LOWER(p.nombre) LIKE LOWER(CONCAT('%', :busqueda, '%'))")
    List<Proyecto> buscarPorNombre(@Param("busqueda") String busqueda);

}
