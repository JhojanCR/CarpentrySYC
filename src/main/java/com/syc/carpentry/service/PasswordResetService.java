package com.syc.carpentry.service;

import com.syc.carpentry.model.PasswordResetToken;
import com.syc.carpentry.model.Usuario;
import com.syc.carpentry.repository.PasswordResetTokenRepository;
import com.syc.carpentry.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class PasswordResetService {

    @Autowired
    private PasswordResetTokenRepository tokenRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Crea un token de recuperación de contraseña para un email dado
     */
    @Transactional
    public String createPasswordResetToken(String email) {
        // Buscar usuario por email
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(email);

        if (usuarioOpt.isEmpty()) {
            // No revelamos si el email existe o no por seguridad
            return null;
        }

        Usuario usuario = usuarioOpt.get();

        // Eliminar tokens anteriores del usuario
        tokenRepository.deleteByUsuario(usuario);

        // Generar token único
        String token = UUID.randomUUID().toString();

        // Crear y guardar el nuevo token
        PasswordResetToken resetToken = new PasswordResetToken(token, usuario);
        tokenRepository.save(resetToken);

        return token;
    }

    /**
     * Valida un token de recuperación
     */
    public boolean validatePasswordResetToken(String token) {
        Optional<PasswordResetToken> resetTokenOpt = tokenRepository.findByToken(token);

        if (resetTokenOpt.isEmpty()) {
            return false;
        }

        PasswordResetToken resetToken = resetTokenOpt.get();
        return resetToken.isValid();
    }

    /**
     * Obtiene el usuario asociado a un token
     */
    public Optional<Usuario> getUserByToken(String token) {
        Optional<PasswordResetToken> resetTokenOpt = tokenRepository.findByToken(token);

        if (resetTokenOpt.isEmpty()) {
            return Optional.empty();
        }

        PasswordResetToken resetToken = resetTokenOpt.get();

        if (!resetToken.isValid()) {
            return Optional.empty();
        }

        return Optional.of(resetToken.getUsuario());
    }

    /**
     * Resetea la contraseña usando el token
     */
    @Transactional
    public boolean resetPassword(String token, String newPassword) {
        Optional<PasswordResetToken> resetTokenOpt = tokenRepository.findByToken(token);

        if (resetTokenOpt.isEmpty()) {
            return false;
        }

        PasswordResetToken resetToken = resetTokenOpt.get();

        if (!resetToken.isValid()) {
            return false;
        }

        // Actualizar la contraseña del usuario (texto plano, sin encriptación)
        Usuario usuario = resetToken.getUsuario();
        usuario.setPassword(newPassword);
        usuarioRepository.save(usuario);

        // Marcar el token como usado
        resetToken.setUsed(true);
        tokenRepository.save(resetToken);

        return true;
    }

    /**
     * Simula el envío de email (en producción se usaría un servicio de email real)
     */
    public void sendPasswordResetEmail(String email, String token) {
        String resetUrl = "http://localhost:8080/reset-password?token=" + token;

        // En desarrollo, solo imprimimos en consola
        System.out.println("==============================================");
        System.out.println("EMAIL DE RECUPERACIÓN DE CONTRASEÑA");
        System.out.println("==============================================");
        System.out.println("Para: " + email);
        System.out.println("Asunto: Recuperación de contraseña - Carpentry SYC");
        System.out.println("");
        System.out.println("Hola,");
        System.out.println("");
        System.out.println("Has solicitado restablecer tu contraseña.");
        System.out.println("Haz clic en el siguiente enlace para crear una nueva contraseña:");
        System.out.println("");
        System.out.println(resetUrl);
        System.out.println("");
        System.out.println("Este enlace expirará en 24 horas.");
        System.out.println("Si no solicitaste este cambio, ignora este correo.");
        System.out.println("");
        System.out.println("Saludos,");
        System.out.println("Equipo de Carpentry SYC");
        System.out.println("==============================================");
    }
}
