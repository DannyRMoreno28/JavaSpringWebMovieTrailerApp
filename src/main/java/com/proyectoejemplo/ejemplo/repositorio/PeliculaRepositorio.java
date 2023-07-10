package com.proyectoejemplo.ejemplo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyectoejemplo.ejemplo.modelo.Pelicula;

public interface PeliculaRepositorio extends JpaRepository<Pelicula, Integer> {
    
}
