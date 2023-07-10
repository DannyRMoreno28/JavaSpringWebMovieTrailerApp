package com.proyectoejemplo.ejemplo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyectoejemplo.ejemplo.modelo.Genero;

public interface GeneroRepositorio extends JpaRepository<Genero, Integer> {
    
}
