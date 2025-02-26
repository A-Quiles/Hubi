package es.hubi.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import es.hubi.modelo.Publicacion;
import es.hubi.servicio.PublicacionServicio;

import java.util.List;

@RestController
@RequestMapping("/publicaciones")
public class PublicacionControlador {

    @Autowired
    private PublicacionServicio publicacionServicio;

    // Crear una nueva publicación
    @PostMapping
    public Publicacion crearPublicacion(@RequestBody Publicacion publicacion) {
        return publicacionServicio.crearPublicacion(publicacion);
    }

    // Listar todas las publicaciones
    @GetMapping
    public List<Publicacion> listarPublicaciones() {
        return publicacionServicio.listarPublicaciones();
    }

    // Obtener una publicación por ID
    @GetMapping("/{id}")
    public Publicacion obtenerPublicacionPorId(@PathVariable Long id) {
        return publicacionServicio.obtenerPublicacionPorId(id);
    }

    // Actualizar una publicación
    @PutMapping("/{id}")
    public Publicacion actualizarPublicacion(@PathVariable Long id, @RequestBody Publicacion publicacion) {
        return publicacionServicio.actualizarPublicacion(id, publicacion);
    }

    // Eliminar una publicación
    @DeleteMapping("/{id}")
    public void eliminarPublicacion(@PathVariable Long id) {
        publicacionServicio.eliminarPublicacion(id);
    }
}
