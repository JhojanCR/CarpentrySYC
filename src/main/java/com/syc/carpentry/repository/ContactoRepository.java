package com.syc.carpentry.repository;

import com.syc.carpentry.model.MensajeContacto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ContactoRepository extends JpaRepository<MensajeContacto, Long> {

    // Obtener los últimos 5 contactos ordenados por fecha de envío descendente
    List<MensajeContacto> findTop5ByOrderByFechaEnvioDesc();

    // Obtener lista de contactos en un rango de fechas
    @Query("SELECT c FROM MensajeContacto c WHERE c.fechaEnvio >= :startDate AND c.fechaEnvio < :endDate ORDER BY c.fechaEnvio DESC")
    List<MensajeContacto> findByFechaEnvioBetween(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

}