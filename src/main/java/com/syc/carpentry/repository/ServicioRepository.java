package com.syc.carpentry.repository;

import com.syc.carpentry.model.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ServicioRepository extends JpaRepository<Servicio, Long> {

    // Contar servicios creados en un rango de fechas
    @Query("SELECT COUNT(s) FROM Servicio s WHERE s.fechaCreacion >= :startDate AND s.fechaCreacion < :endDate")
    Long countByFechaCreacionBetween(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    // Obtener lista de servicios creados en un rango de fechas
    @Query("SELECT s FROM Servicio s WHERE s.fechaCreacion >= :startDate AND s.fechaCreacion < :endDate ORDER BY s.fechaCreacion DESC")
    List<Servicio> findByFechaCreacionBetween(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

}
