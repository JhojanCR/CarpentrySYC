package com.syc.carpentry.repository;

import com.syc.carpentry.model.MensajeContacto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ContactoRepository extends JpaRepository<MensajeContacto, Long> {

    // Obtener los últimos 5 contactos ordenados por fecha de envío descendente
    List<MensajeContacto> findTop5ByOrderByFechaEnvioDesc();

}