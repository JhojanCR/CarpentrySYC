package com.syc.carpentry.model;

// Jakarta Persistence API (JPA) - Es el estándar de Java para mapear objetos a bases de datos
// Permite que esta clase Java se convierta automáticamente en una tabla de MySQL
import jakarta.persistence.*;

/**
 * CLASE ENTIDAD - USUARIO
 *
 * Esta clase representa a un usuario del sistema en la base de datos.
 * Es una "Entidad" porque se mapea directamente a una tabla en MySQL.
 *
 * ¿Qué es una Entidad?
 * - Es una clase Java que representa una tabla de base de datos
 * - Cada objeto Usuario = 1 fila en la tabla "usuarios"
 * - Cada atributo (id, nombre, etc.) = 1 columna en la tabla
 *
 * Spring Boot + JPA se encargan automáticamente de:
 * - Crear la tabla si no existe
 * - Convertir objetos Java a filas de MySQL y viceversa
 * - Ejecutar consultas SQL sin que tengas que escribir SQL manualmente
 */

// @Entity: Le dice a JPA que esta clase es una entidad (se mapea a una tabla)
@Entity
// @Table: Especifica el nombre de la tabla en MySQL (si no se pone, usa el nombre de la clase)
@Table(name = "usuarios")
public class Usuario {

    // ==================== ATRIBUTOS (COLUMNAS DE LA TABLA) ====================

    /**
     * ID - Identificador único de cada usuario
     *
     * @Id: Marca este campo como la clave primaria (Primary Key) de la tabla
     * @GeneratedValue: Le dice a JPA que genere automáticamente el valor del ID
     * - strategy = IDENTITY: Usa AUTO_INCREMENT de MySQL para generar IDs automáticos (1, 2, 3, 4...)
     *
     * Long: Tipo numérico para IDs (puede almacenar números muy grandes)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * NOMBRE - Nombre completo del usuario (ej: "Juan Pérez")
     *
     * Sin anotaciones especiales = columna normal en la tabla
     * Puede ser null y no tiene restricciones
     */
    private String nombre;

    /**
     * USERNAME - Nombre de usuario para iniciar sesión (ej: "admin", "jperez")
     *
     * @Column: Permite especificar características de la columna en MySQL
     * - unique = true: No puede haber dos usuarios con el mismo username
     * - nullable = false: Este campo es obligatorio (NOT NULL en MySQL)
     */
    @Column(unique = true, nullable = false)
    private String username;

    /**
     * EMAIL - Correo electrónico del usuario
     *
     * También es único y obligatorio
     * Se usa para recuperación de contraseña
     */
    @Column(unique = true, nullable = false)
    private String email;

    /**
     * PASSWORD - Contraseña del usuario
     *
     * Actualmente se guarda en texto plano (sin encriptación)
     * NOTA: En producción deberías usar BCrypt para mayor seguridad
     */
    private String password;

    /**
     * ROL - Rol del usuario en el sistema
     *
     * Valores posibles:
     * - "ADMIN": Tiene acceso al dashboard administrativo
     * - "USER": Usuario normal sin acceso al dashboard
     *
     * Spring Security usa este campo para controlar permisos
     */
    private String rol; // ADMIN, USER

    // ==================== GETTERS Y SETTERS ====================

    /**
     * GETTERS Y SETTERS - Métodos para acceder y modificar los atributos
     *
     * ¿Por qué usar getters/setters en lugar de atributos públicos?
     * 1. Encapsulación: Los atributos son privados, solo se accede por métodos
     * 2. Control: Puedes agregar validaciones en los setters
     * 3. JavaBeans: Spring, Thymeleaf y JPA requieren getters/setters
     *
     * Ejemplo:
     * - getId(): Devuelve el valor del id
     * - setId(Long id): Asigna un nuevo valor al id
     */

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }

}
