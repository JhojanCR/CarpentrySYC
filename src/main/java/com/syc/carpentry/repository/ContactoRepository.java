package com.syc.carpentry.repository;

import com.syc.carpentry.model.MensajeContacto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactoRepository extends JpaRepository<MensajeContacto, Long> {}