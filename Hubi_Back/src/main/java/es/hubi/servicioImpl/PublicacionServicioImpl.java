package es.hubi.servicioImpl;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import es.hubi.excepciones.HubiException;
import es.hubi.modelo.Publicacion;
import es.hubi.repositorio.PublicacionRepositorio;
import es.hubi.servicio.PublicacionServicio;

@Service
public class PublicacionServicioImpl implements PublicacionServicio {

    @Autowired
    private PublicacionRepositorio publicacionRepositorio;

    @Override
    public List<Publicacion> listarPublicaciones(Long categoria) {
        if (categoria == null) {
            return publicacionRepositorio.findTop500ByOrderByFechaDesc();
        } else {
            return publicacionRepositorio.findTop500ByCategoriaIdOrderByFechaDesc(categoria);
        }
    }

    @Override
    @Transactional
    public Publicacion crearPublicacion(Publicacion publicacion, MultipartFile file) {
        // Validar datos de la publicación
        if (publicacion == null || publicacion.getMensaje() == null || publicacion.getMensaje().isEmpty()) {
            throw new HubiException("El mensaje de la publicación no puede estar vacío");
        }

        if (publicacion.getNombre() == null || publicacion.getNombre().isEmpty()) {
            throw new HubiException("El nombre de la publicación no puede estar vacío");
        }

        try {
            // Procesar la imagen si está presente
            if (file != null && !file.isEmpty()) {
                publicacion.setImagen(file.getBytes());
            }

            // Establecer la fecha de la publicación
            publicacion.setFecha(LocalDateTime.now());

            // Guardar la publicación en la base de datos
            return publicacionRepositorio.save(publicacion);
        } catch (IOException e) {
            // Excepción en caso de error al procesar la imagen
            throw new HubiException("Error al procesar la imagen de la publicación", e);
        } catch (DataAccessException e) {
            // Excepción en caso de error al guardar en la base de datos
            throw new HubiException("Error al guardar la publicación en la base de datos", e);
        } catch (Exception e) {
            // Capturar cualquier otro tipo de error inesperado
            throw new HubiException("Error inesperado al crear la publicación", e);
        }
    }

    @Override
    public Publicacion obtenerPublicacionPorId(Long id) {
        return publicacionRepositorio.findById(id)
                .orElseThrow(() -> new HubiException("Publicación no encontrada con ID: " + id));
    }
}
