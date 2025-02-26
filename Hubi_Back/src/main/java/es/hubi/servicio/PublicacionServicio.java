package es.hubi.servicio;

import java.util.List;

import es.hubi.modelo.Publicacion;

public interface PublicacionServicio {

    // Crear una publicación
    Publicacion crearPublicacion(Publicacion publicacion);

    // Listar todas las publicaciones
    List<Publicacion> listarPublicaciones();

    // Buscar una publicación por ID
    Publicacion obtenerPublicacionPorId(Long id);

    // Actualizar una publicación
    Publicacion actualizarPublicacion(Long id, Publicacion publicacionActualizada);

    // Eliminar una publicación
    void eliminarPublicacion(Long id);

}
