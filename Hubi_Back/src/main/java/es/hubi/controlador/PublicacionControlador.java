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

    @GetMapping("/{id}")
    public ResponseEntity<Publicacion> obtenerPublicacionPorId(@PathVariable Long id) {
        return ResponseEntity.ok(publicacionServicio.obtenerPublicacionPorId(id));
    }

    // Aquí añado el parámetro List<String> hashtags
    @GetMapping
    public ResponseEntity<List<Publicacion>> listarPublicaciones(
        @RequestParam(value = "categoriaId", required = false) Long categoriaId,
        @RequestParam(value = "hashtag",     required = false) List<String> hashtags
    ) {
        List<Publicacion> resultado = publicacionServicio.listarPublicaciones(categoriaId, hashtags);
        return ResponseEntity.ok(resultado);
    }
}
