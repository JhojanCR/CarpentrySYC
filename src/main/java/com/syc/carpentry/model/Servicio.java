package com.syc.carpentry.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Servicio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String descripcion;
    private String imagenUrl;

    // Getters y setters para tomar las variables private

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}

    public String getDescripcion() {return descripcion;}
    public void  setDescripcion(String descripcion) {this.descripcion = descripcion;}

    public String getImagenUrl() {return imagenUrl;}
    public void setImagenUrl() {this.imagenUrl = imagenUrl;}


}
