package com.proyectoejemplo.ejemplo.modelo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table
public class Pelicula {
    
    //Espablecer propiedades

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pelicula")
    private Integer id;

    @NotBlank // garantizar que los campos de entrada no estén vacíos y contengan al menos un carácter visible.
    private String titulo;

    @NotBlank
    private String sinopsis;

    @NotNull // Al ser un valor de fecha, NotNull significa que este campo no puede ser nulo.
    @DateTimeFormat(iso = ISO.DATE)
    private LocalDate fechaEstreno;

    @NotBlank
    private String youtubeTrailerId;

    private String rutaPortada;

    @NotEmpty // al ser una lista de seleccion desplegable, la anotacion exige que sea seleccionado almenos un valor (genero)
    @ManyToMany(fetch = FetchType.LAZY) //relacion muchos a muchos que solamente se carga cuando lo requeramos (LAZY)
    @JoinTable(name = "genero_pelicula", //crear tabla "genero_pelicula" que contien 2 columnas ("id_pelicula" y "id_genero")
                joinColumns = @JoinColumn(name = "id_pelicula"),
                inverseJoinColumns = @JoinColumn(name = "id_genero"))
                private List<Genero> generos;
                
    @Transient // este elemento no sera almacenado en la BD (dato temporal)
    private MultipartFile portada;

    // Crear Getters y Settters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public LocalDate getFechaEstreno() {
        return fechaEstreno;
    }

    public void setFechaEstreno(LocalDate fechaEstreno) {
        this.fechaEstreno = fechaEstreno;
    }

    public String getYoutubeTrailerId() {
        return youtubeTrailerId;
    }

    public void setYoutubeTrailerId(String youtubeTrailerId) {
        this.youtubeTrailerId = youtubeTrailerId;
    }

    public String getRutaPortada() {
        return rutaPortada;
    }

    public void setRutaPortada(String rutaPortada) {
        this.rutaPortada = rutaPortada;
    }

    public List<Genero> getGeneros() {
        return generos;
    }

    public void setGeneros(List<Genero> generos) {
        this.generos = generos;
    }

    public MultipartFile getPortada() {
        return portada;
    }

    public void setPortada(MultipartFile portada) {
        this.portada = portada;
    }

    // Crear construtores
    public Pelicula() {
    }

    public Pelicula(Integer id, @NotBlank String titulo, @NotBlank String sinopsis, @NotNull LocalDate fechaEstreno,
            @NotBlank String youtubeTrailerId, String rutaPortada, @NotBlank List<Genero> generos,
            MultipartFile portada) {
        this.id = id;
        this.titulo = titulo;
        this.sinopsis = sinopsis;
        this.fechaEstreno = fechaEstreno;
        this.youtubeTrailerId = youtubeTrailerId;
        this.rutaPortada = rutaPortada;
        this.generos = generos;
        this.portada = portada;
    }

    public Pelicula(@NotBlank String titulo, @NotBlank String sinopsis, @NotNull LocalDate fechaEstreno,
            @NotBlank String youtubeTrailerId, String rutaPortada, @NotBlank List<Genero> generos,
            MultipartFile portada) {
        this.titulo = titulo;
        this.sinopsis = sinopsis;
        this.fechaEstreno = fechaEstreno;
        this.youtubeTrailerId = youtubeTrailerId;
        this.rutaPortada = rutaPortada;
        this.generos = generos;
        this.portada = portada;
    }
}
