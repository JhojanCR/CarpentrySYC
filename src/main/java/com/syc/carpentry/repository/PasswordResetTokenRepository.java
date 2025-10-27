package com.syc.carpentry.repository;

import com.syc.carpentry.model.PasswordResetToken;
import com.syc.carpentry.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {

    // Buscar token por el string del token
    Optional<PasswordResetToken> findByToken(String token);

    // Buscar tokens por usuario
    Optional<PasswordResetToken> findByUsuario(Usuario usuario);

    // Eliminar tokens expirados (para limpieza periódica)
    void deleteByExpiryDateBefore(LocalDateTime date);

    // Eliminar tokens por usuario
    void deleteByUsuario(Usuario usuario);
}
