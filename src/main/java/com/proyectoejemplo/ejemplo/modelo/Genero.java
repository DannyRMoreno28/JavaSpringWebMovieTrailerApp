package com.proyectoejemplo.ejemplo.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Genero {

    @Id
    @Column(name = "id_genero")
    private Integer id;
    private String titulo;

    // Crear constructores
    public Genero(Integer id, String titulo) {
        this.id = id;
        this.titulo = titulo;
    }

    public Genero(Integer id) {
        this.id = id;
    }

    public Genero(String titulo) {
        this.titulo = titulo;
    }

    public Genero() {
    }


    // Crear Getters
    public Integer getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    // Crear Setters
    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

}
