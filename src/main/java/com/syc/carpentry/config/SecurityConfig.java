package com.syc.carpentry.config;

// Anotación que indica que esta clase contiene configuración de Spring
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.Customizer;

// Importa las clases necesarias para configurar la seguridad HTTP
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // Indica que esta clase se usará para configurar beans (componentes) de Spring
public class SecurityConfig {

    // Define un bean de tipo SecurityFilterChain, que es la cadena de filtros de seguridad
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // Configura que todas las peticiones estarán permitidas sin autenticación
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll() // Permite cualquier solicitud sin autenticación
                )

                // Desactiva la protección contra CSRF (Cross-Site Request Forgery),
                // útil si estás haciendo pruebas o no tienes formularios protegidos aún
                .csrf(csrf -> csrf.disable())

                // Desactiva el formulario de inicio de sesión por defecto de Spring Security
                .formLogin(Customizer.withDefaults())

                // Desactiva el login básico por HTTP (usuario/clave que aparece en un pop-up)
                .httpBasic(Customizer.withDefaults());

        // Retorna la configuración de seguridad
        return http.build();
    }


}
