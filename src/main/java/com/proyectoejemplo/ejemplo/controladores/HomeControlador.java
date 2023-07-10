package com.proyectoejemplo.ejemplo.controladores;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.proyectoejemplo.ejemplo.modelo.Pelicula;
import com.proyectoejemplo.ejemplo.repositorio.PeliculaRepositorio;

@Controller
@RequestMapping("")
public class HomeControlador {
    
    @Autowired
    private PeliculaRepositorio peliculaRepositorio;

        @GetMapping("")
    public ModelAndView verPaginaInicio() {
        List<Pelicula> ultimasPeliculas = peliculaRepositorio.findAll(PageRequest.of(0, 4, Sort.by("fechaEstreno").descending())).toList();
        /* Imprimir en consola las películas en la lista, usando un for para recorrer los objetos de "ultimasPeliculas" el método toString()
            significa que el controlador está enviando la lista de películas al modelo con el nombre "peliculas" correctamente. 
        for(Pelicula pelicula : ultimasPeliculas){
            System.out.println(pelicula.getTitulo()); 
        }
        */
        return new ModelAndView("index").addObject("peliculas", ultimasPeliculas);
    }

    @GetMapping("/peliculas")
    public ModelAndView listarPeliculas(@PageableDefault(sort = "fechaEstreno", direction = Sort.Direction.DESC) Pageable pageable){
        Page<Pelicula> peliculas = peliculaRepositorio.findAll(pageable);
        return new ModelAndView("peliculas").addObject("peliculas", peliculas);
    }

    @GetMapping("/peliculas/{id}")
	public ModelAndView mostrarDetallesPelicula(@PathVariable Integer id) {
		Optional<Pelicula> optionalPelicula = peliculaRepositorio.findById(id);
        Pelicula pelicula = optionalPelicula.orElseThrow(() -> new NoSuchElementException("Pelicula no encontrada"));
		return new ModelAndView("pelicula").addObject("pelicula",pelicula);
	}
}

