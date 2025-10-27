package com.syc.carpentry.config;

import com.syc.carpentry.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    // Bean para encriptar contraseñas con BCrypt
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Bean para configurar la cadena de filtros de seguridad
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // Configuración de autorización de peticiones
                .authorizeHttpRequests(auth -> auth
                        // Rutas públicas (sin autenticación)
                        .requestMatchers("/", "/login", "/register", "/forgot-password", "/reset-password",
                                "/api/**", "/styles.css", "/script.js", "/scripts.js",
                                "/assets/**", "/public/**", "/servicios", "/proyectos", "/contacto", "/nosotros").permitAll()

                        // Rutas del admin requieren autenticación y rol ADMIN
                        .requestMatchers("/admin/**").hasRole("ADMIN")

                        // Cualquier otra ruta requiere autenticación
                        .anyRequest().authenticated()
                )

                // Configuración del formulario de login
                .formLogin(form -> form
                        .loginPage("/login")  // Página personalizada de login
                        .loginProcessingUrl("/login")  // URL donde se procesa el login
                        .defaultSuccessUrl("/admin/dashboard", true)  // Redirección después de login exitoso
                        .failureUrl("/login?error=true")  // Redirección si falla el login
                        .usernameParameter("username")  // Nombre del parámetro de usuario
                        .passwordParameter("password")  // Nombre del parámetro de contraseña
                        .permitAll()
                )

                // Configuración del logout
                .logout(logout -> logout
                        .logoutUrl("/logout")  // URL para cerrar sesión
                        .logoutSuccessUrl("/login?logout=true")  // Redirección después de logout
                        .invalidateHttpSession(true)  // Invalidar la sesión
                        .deleteCookies("JSESSIONID")  // Eliminar cookies
                        .permitAll()
                )

                // Habilitar CSRF (protección contra ataques cross-site)
                .csrf(csrf -> csrf.ignoringRequestMatchers("/api/**"));  // Deshabilitar CSRF solo para API REST

        return http.build();
    }

    // Configurar AuthenticationManager con UserDetailsService y PasswordEncoder
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
            http.getSharedObject(AuthenticationManagerBuilder.class);

        authenticationManagerBuilder
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());

        return authenticationManagerBuilder.build();
    }
}
