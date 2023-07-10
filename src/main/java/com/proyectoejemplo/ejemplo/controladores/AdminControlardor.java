package com.proyectoejemplo.ejemplo.controladores;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.proyectoejemplo.ejemplo.modelo.Genero;
import com.proyectoejemplo.ejemplo.modelo.Pelicula;
import com.proyectoejemplo.ejemplo.repositorio.GeneroRepositorio;
import com.proyectoejemplo.ejemplo.repositorio.PeliculaRepositorio;
import com.proyectoejemplo.ejemplo.servicio.AlmacenServicioImplementacion;

@Controller
@RequestMapping("/admin")
public class AdminControlardor {
    
    @Autowired
    private PeliculaRepositorio peliculaRepositorio;

    @Autowired
    private GeneroRepositorio generoRepositorio;

    @Autowired
    private AlmacenServicioImplementacion servicio;

    @GetMapping("")
    public ModelAndView verPaginaInicio(@PageableDefault(sort = "titulo", size = 5) Pageable pageable){
        Page<Pelicula> peliculas = peliculaRepositorio.findAll(pageable);
        return new ModelAndView("admin/index").addObject("peliculas", peliculas);
    }

    @GetMapping("/peliculas/nuevo")
    public ModelAndView mostrarFormularioNuevaPelicula(){
        List<Genero> generos = generoRepositorio.findAll(Sort.by("titulo"));
        return new ModelAndView("admin/nueva-pelicula")
                .addObject("pelicula", new Pelicula())
                .addObject("generos", generos);
    }

    @PostMapping("/peliculas/nuevo")
    public ModelAndView registrarPelicula(@Validated Pelicula pelicula, BindingResult bindingResult){
        if(bindingResult.hasErrors() || pelicula.getPortada().isEmpty()){
            if(pelicula.getPortada().isEmpty()){
                bindingResult.rejectValue("portada", "MultipartNotEmpty");
            }

            List<Genero> generos = generoRepositorio.findAll(Sort.by("titulo"));
            return new ModelAndView("admin/nueva-pelicula")
                .addObject("pelicula", pelicula)
                .addObject("generos", generos);
        }

        String rutaPortada = servicio.almacenArchivo(pelicula.getPortada());
        pelicula.setRutaPortada(rutaPortada);

        peliculaRepositorio.save(pelicula);
        return new ModelAndView("redirect:/admin");
    }

    @GetMapping("/peliculas/{id}/editar")
    public ModelAndView mostrarFormularioEditarPelicula(@PathVariable Integer id){
        Optional<Pelicula> optionalPelicula = peliculaRepositorio.findById(id);
        Pelicula pelicula = optionalPelicula.orElseThrow(() -> new NoSuchElementException("Pelicula no encontrada"));
        List<Genero> generos = generoRepositorio.findAll(Sort.by("titulo"));

        return new ModelAndView("admin/editar-pelicula")
                .addObject("pelicula", pelicula)
                .addObject("generos", generos);
        
    }

    @PostMapping("/peliculas/{id}/editar")
    public ModelAndView actualizarPelicula(@PathVariable Integer id, @Validated Pelicula pelicula, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            List<Genero> generos = generoRepositorio.findAll(Sort.by("titulo"));

            return new ModelAndView("admin/editar-pelicula")
                .addObject("pelicula", pelicula)
                .addObject("generos", generos);
        }

        Optional<Pelicula> optionalPelicula = peliculaRepositorio.findById(id);
        Pelicula peliculaDB = optionalPelicula.orElseThrow(() -> new NoSuchElementException("Pelicula no encontrada"));

        peliculaDB.setTitulo(pelicula.getTitulo());
        peliculaDB.setSinopsis(pelicula.getSinopsis());
        peliculaDB.setFechaEstreno(pelicula.getFechaEstreno());
        peliculaDB.setYoutubeTrailerId(pelicula.getYoutubeTrailerId());
        peliculaDB.setGeneros(pelicula.getGeneros());

        if(!pelicula.getPortada().isEmpty()){
            servicio.eliminarArchivo(peliculaDB.getRutaPortada());
            String rutaPortada = servicio.almacenArchivo(pelicula.getPortada());
            peliculaDB.setRutaPortada(rutaPortada);
        }

        peliculaRepositorio.save(peliculaDB);
        return new ModelAndView("redirect:/admin");
    }

    @PostMapping("/peliculas/{id}/eliminar")
    public String eliminarPelicula(@PathVariable Integer id){
        Optional<Pelicula> optionalPelicula = peliculaRepositorio.findById(id);
        Pelicula pelicula = optionalPelicula.orElseThrow(() -> new NoSuchElementException("Pelicula no encontrada"));
        
        peliculaRepositorio.delete(pelicula);
        servicio.eliminarArchivo(pelicula.getRutaPortada());
        
        return "redirect:/admin";
    }
}
