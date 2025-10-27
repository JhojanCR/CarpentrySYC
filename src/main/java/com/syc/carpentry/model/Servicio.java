package com.syc.carpentry.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Servicio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String descripcion;
    private String imagenUrl;

    private LocalDateTime fechaCreacion;

    // Getters y setters para tomar las variables private

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

    // Método que se ejecuta antes de persistir para establecer la fecha automáticamente
    @PrePersist
    protected void onCreate() {
        if (fechaCreacion == null) {
            fechaCreacion = LocalDateTime.now();
        }
    }

}
