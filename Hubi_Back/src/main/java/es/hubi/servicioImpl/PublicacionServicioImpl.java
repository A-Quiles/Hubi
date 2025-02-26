package es.hubi.servicioImpl;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.hubi.excepciones.HubiException;
import es.hubi.modelo.Publicacion;
import es.hubi.repositorio.PublicacionRepositorio;
import es.hubi.servicio.PublicacionServicio;

@Service
public class PublicacionServicioImpl implements PublicacionServicio {

    @Autowired
    private PublicacionRepositorio publicacionRepositorio;

    // Crear una publicación
    @Override
    public Publicacion crearPublicacion(Publicacion publicacion) {
        publicacion.setFecha(LocalDateTime.now());
        return publicacionRepositorio.save(publicacion);
    }

    // Listar todas las publicaciones
    @Override
    public List<Publicacion> listarPublicaciones() {
        return publicacionRepositorio.findAll();
    }

    // Buscar una publicación por ID
    @Override
    public Publicacion obtenerPublicacionPorId(Long id) {
        return publicacionRepositorio.findById(id)
                .orElseThrow(() -> new HubiException("Publicación no encontrada con ID: " + id));
    }

    // Actualizar una publicación
    @Override
    public Publicacion actualizarPublicacion(Long id, Publicacion publicacionActualizada) {
        Publicacion publicacion = obtenerPublicacionPorId(id);
        publicacion.setMensaje(publicacionActualizada.getMensaje());
        return publicacionRepositorio.save(publicacion);
    }

    // Eliminar una publicación
    @Override
    public void eliminarPublicacion(Long id) {
        Publicacion publicacion = obtenerPublicacionPorId(id);
        publicacionRepositorio.delete(publicacion);
    }

}
