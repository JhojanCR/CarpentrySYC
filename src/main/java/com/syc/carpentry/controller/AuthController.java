package com.syc.carpentry.controller;

import com.syc.carpentry.model.Usuario;
import com.syc.carpentry.service.PasswordResetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class AuthController {

    @Autowired
    private PasswordResetService passwordResetService;

    @GetMapping("/login")
    public String login() {
        return "auth/login"; // templates/auth/login.html
    }

    @GetMapping("/register")
    public String register() {
        return "auth/register"; // templates/auth/register.html
    }

    // Mostrar formulario de "Olvidé mi contraseña"
    @GetMapping("/forgot-password")
    public String forgotPassword() {
        return "auth/forgot-password";
    }

    // Procesar solicitud de recuperación de contraseña
    @PostMapping("/forgot-password")
    public String processForgotPassword(@RequestParam("email") String email,
                                       RedirectAttributes redirectAttributes) {
        try {
            String token = passwordResetService.createPasswordResetToken(email);

            if (token != null) {
                // Enviar email (en desarrollo se muestra en consola)
                passwordResetService.sendPasswordResetEmail(email, token);

                redirectAttributes.addFlashAttribute("successMessage",
                        "Si el correo existe, recibirás un enlace para restablecer tu contraseña. Revisa la consola del servidor.");
            } else {
                // Por seguridad, no revelamos si el email existe o no
                redirectAttributes.addFlashAttribute("successMessage",
                        "Si el correo existe, recibirás un enlace para restablecer tu contraseña. Revisa la consola del servidor.");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage",
                    "Ocurrió un error al procesar tu solicitud. Intenta nuevamente.");
        }

        return "redirect:/forgot-password";
    }

    // Mostrar formulario para resetear contraseña
    @GetMapping("/reset-password")
    public String resetPassword(@RequestParam("token") String token, Model model) {
        // Validar el token
        if (!passwordResetService.validatePasswordResetToken(token)) {
            model.addAttribute("error", "El enlace de recuperación es inválido o ha expirado.");
            return "auth/reset-password";
        }

        // Obtener el usuario del token
        Optional<Usuario> usuario = passwordResetService.getUserByToken(token);
        if (usuario.isEmpty()) {
            model.addAttribute("error", "El enlace de recuperación es inválido o ha expirado.");
            return "auth/reset-password";
        }

        model.addAttribute("token", token);
        model.addAttribute("email", usuario.get().getEmail());
        return "auth/reset-password";
    }

    // Procesar el cambio de contraseña
    @PostMapping("/reset-password")
    public String processResetPassword(@RequestParam("token") String token,
                                      @RequestParam("password") String password,
                                      @RequestParam("confirmPassword") String confirmPassword,
                                      RedirectAttributes redirectAttributes) {

        // Validar que las contraseñas coincidan
        if (!password.equals(confirmPassword)) {
            redirectAttributes.addFlashAttribute("errorMessage", "Las contraseñas no coinciden.");
            return "redirect:/reset-password?token=" + token;
        }

        // Validar longitud mínima
        if (password.length() < 6) {
            redirectAttributes.addFlashAttribute("errorMessage", "La contraseña debe tener al menos 6 caracteres.");
            return "redirect:/reset-password?token=" + token;
        }

        // Intentar resetear la contraseña
        boolean success = passwordResetService.resetPassword(token, password);

        if (success) {
            redirectAttributes.addFlashAttribute("successMessage",
                    "Tu contraseña ha sido actualizada exitosamente. Ahora puedes iniciar sesión.");
            return "redirect:/login";
        } else {
            redirectAttributes.addFlashAttribute("errorMessage",
                    "El enlace de recuperación es inválido o ha expirado.");
            return "redirect:/reset-password?token=" + token;
        }
    }
}
