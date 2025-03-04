package es.hubi.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import es.hubi.modelo.Categoria;
import es.hubi.modelo.Publicacion;
import es.hubi.servicio.PublicacionServicio;

import java.util.List;

@RestController
@RequestMapping("/publicaciones")
public class PublicacionControlador {

    @Autowired
    private PublicacionServicio publicacionServicio;

    // Crear una nueva publicación con imagen
    @PostMapping
    public ResponseEntity<Publicacion> crearPublicacion(
            @RequestParam("mensaje") String mensaje,
            @RequestParam("nombre") String nombre,
            @RequestParam(value = "categoria", required = false) Long categoriaId,
            @RequestParam(value = "imagen", required = false) MultipartFile file) {

        Publicacion publicacion = new Publicacion();
        publicacion.setMensaje(mensaje);
        publicacion.setNombre(nombre);
        if (categoriaId != null) {
            publicacion.setCategoria(new Categoria());
            publicacion.getCategoria().setId(categoriaId);
        }

        Publicacion nuevaPublicacion = publicacionServicio.crearPublicacion(publicacion, file);
        return ResponseEntity.ok(nuevaPublicacion);
    }

    // Obtener una publicación por ID (incluyendo la imagen si existe)
    @GetMapping("/{id}")
    public ResponseEntity<Publicacion> obtenerPublicacionPorId(@PathVariable Long id) {
        return ResponseEntity.ok(publicacionServicio.obtenerPublicacionPorId(id));
    }

    // Listar todas las publicaciones
    @GetMapping
    public ResponseEntity<List<Publicacion>> listarPublicaciones(@RequestParam(required = false) Long categoria) {
        return ResponseEntity.ok(publicacionServicio.listarPublicaciones(categoria));
    }
}
