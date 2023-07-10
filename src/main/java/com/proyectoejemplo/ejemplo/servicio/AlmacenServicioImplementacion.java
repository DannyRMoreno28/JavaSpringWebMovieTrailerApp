package com.proyectoejemplo.ejemplo.servicio;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;

import com.proyectoejemplo.ejemplo.excepciones.AlmacenExcepcion;
import com.proyectoejemplo.ejemplo.excepciones.FileNotFoundException;

@Service
public class AlmacenServicioImplementacion implements AlmacenServicio {

// Ubicación de almacenamiento de archivos.
    @Value("${storage.location}") // se utiliza para inyectar el valor de la propiedad "storage.location" del archivo de configuración en la variable storageLocation. 
    private String storageLocation;

// Inicializa el directorio de almacenamiento de archivos.
    @PostConstruct //anotacion que indica que el metodo se ejecutara cada vez que halla una nueva instancia de esta clase
    @Override
    public void iniciarAlmacenDeArchivos() {
        try {
             // Crea el directorio de almacenamiento si no existe
            Files.createDirectories(Paths.get(storageLocation));
        } catch (IOException excepcion) {
            throw new AlmacenExcepcion("Erros al inicializar la ubicación del archivo.");
        }
    }

// Almacena un archivo en el directorio de almacenamiento.
    @Override
    public String almacenArchivo(MultipartFile archivo) {
        String nombreArchivo = archivo.getOriginalFilename();
        if (archivo.isEmpty()) {
            throw new AlmacenExcepcion("No se puede almacenar un archivo vacio.");
        }
        try {
            // Obtiener el flujo de entrada del archivo
            InputStream inputStream = archivo.getInputStream();
            // Copiar el archivo al directorio de almacenamiento
            Files.copy(inputStream, Paths.get(storageLocation).resolve(nombreArchivo),
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException excepcion) {
            throw new AlmacenExcepcion("Error al almacenar el archivo " + nombreArchivo, excepcion);
        }
        return nombreArchivo;
    }

// Obtiene la ruta completa de un archivo en el directorio de almacenamiento.
    @Override
    public Path cargarArchivo(String nombreArchivo) {
        // Devolver la ruta completa del archivo en el almacenamiento
        return Paths.get(storageLocation).resolve(nombreArchivo);
    }

// Carga un archivo como un recurso.
    @Override
    public Resource cargarComoRecurso(String nombreArchivo) {
        try {
            // Cargar el archivo como un recurso
            Path archivo = cargarArchivo(nombreArchivo);
            Resource recurso = new UrlResource(archivo.toUri());

            // Verificar si el recurso existe y es legible
            if (recurso.exists() || recurso.isReadable()) {
                return recurso;
            } else {
                throw new FileNotFoundException("No se puedo encontrar el archivo " + nombreArchivo);               
            }
        } catch (MalformedURLException excepcion) {
            throw new FileNotFoundException("No se puedo encontrar el archivo " + nombreArchivo, excepcion); 
        }
    }

// Elimina un archivo del directorio de almacenamiento.
    @Override
    public void eliminarArchivo(String nombreArchivo) {
        // Eliminar el archivo del almacenamiento
        Path archivo = cargarArchivo(nombreArchivo);
        try {
            FileSystemUtils.deleteRecursively(archivo);
        } catch (Exception excepcion) {
            System.out.println(excepcion);
        }
    }

}
