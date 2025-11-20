package com.syc.carpentry.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * CLASE ENTIDAD - SERVICIO
 *
 * Representa los servicios de carpintería que ofrece la empresa.
 * Ejemplos: "Muebles a medida", "Reparación de puertas", "Instalación de cocinas"
 *
 * Esta entidad se muestra en:
 * - Página pública: Para que los clientes vean los servicios disponibles
 * - Dashboard admin: Para gestionar (crear, editar, eliminar) los servicios
 * - Reportes: Para ver estadísticas de servicios creados
 */
@Entity
public class Servicio {

    // ==================== ATRIBUTOS ====================

    /**
     * ID - Identificador único auto-generado
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * NOMBRE - Nombre del servicio
     * Ejemplo: "Fabricación de Muebles a Medida"
     */
    private String nombre;

    /**
     * DESCRIPCION - Descripción detallada del servicio
     * Ejemplo: "Creamos muebles personalizados según tus necesidades..."
     */
    private String descripcion;

    /**
     * IMAGEN_URL - Ruta o URL de la imagen del servicio
     * Ejemplo: "/assets/img/muebles.jpg"
     *
     * Se usa para mostrar una imagen visual del servicio en la página web
     */
    private String imagenUrl;

    /**
     * FECHA_CREACION - Fecha y hora en que se creó este servicio
     *
     * LocalDateTime: Clase de Java 8+ para manejar fechas con hora
     * - Incluye: año, mes, día, hora, minuto, segundo
     * - Mejor que Date() del antiguo Java
     *
     * Se usa para:
     * - Ordenar servicios por fecha
     * - Generar reportes de servicios creados por mes
     * - Mostrar cuándo se agregó un servicio
     */
    private LocalDateTime fechaCreacion;

    // ==================== GETTERS Y SETTERS ====================

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}

    public String getDescripcion() {return descripcion;}
    public void  setDescripcion(String descripcion) {this.descripcion = descripcion;}

    public String getImagenUrl() {return imagenUrl;}
    public void setImagenUrl(String imagenUrl) {this.imagenUrl = imagenUrl;}

    public LocalDateTime getFechaCreacion() {return fechaCreacion;}
    public void setFechaCreacion(LocalDateTime fechaCreacion) {this.fechaCreacion = fechaCreacion;}

    // ==================== MÉTODOS DE CICLO DE VIDA JPA ====================

    /**
     * METODO AUTOMATICO - Se ejecuta ANTES de guardar en la base de datos
     *
     * @PrePersist: Anotación de JPA que marca un método para ejecutarse antes de INSERT
     *
     * ¿Cómo funciona?
     * 1. Creas un nuevo Servicio: Servicio s = new Servicio();
     * 2. Estableces nombre y descripción: s.setNombre("Muebles");
     * 3. Llamas a save(): servicioRepository.save(s);
     * 4. ANTES de guardar en MySQL, JPA llama automáticamente a onCreate()
     * 5. onCreate() establece la fecha actual: fechaCreacion = LocalDateTime.now()
     * 6. Ahora SÍ guarda en la base de datos con la fecha incluida
     *
     * Ventaja: No tienes que acordarte de setear la fecha manualmente cada vez
     */
    @PrePersist
    protected void onCreate() {
        // Si la fecha no se estableció manualmente, ponle la fecha actual
        if (fechaCreacion == null) {
            fechaCreacion = LocalDateTime.now(); // now() = fecha y hora actual
        }
    }

}
