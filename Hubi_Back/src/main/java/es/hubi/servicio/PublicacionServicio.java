package es.hubi.servicio;

import org.springframework.web.multipart.MultipartFile;
import es.hubi.modelo.Publicacion;
import java.util.List;

public interface PublicacionServicio {
    List<Publicacion> listarPublicaciones(Long categoria);
    Publicacion crearPublicacion(Publicacion publicacion, MultipartFile file);
    Publicacion obtenerPublicacionPorId(Long id);
}
