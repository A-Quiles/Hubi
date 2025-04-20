package es.hubi.servicioImpl;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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
    public List<Publicacion> listarPublicaciones(Long categoria, List<String> hashtags) {
        return publicacionRepositorio.buscarPorCategoriaYHashtags(categoria, hashtags);
    }

    @Override
    @Transactional
    public Publicacion crearPublicacion(Publicacion publicacion, MultipartFile file) {
        if (publicacion == null || publicacion.getMensaje() == null || publicacion.getMensaje().isEmpty()) {
            throw new HubiException("El mensaje de la publicación no puede estar vacío");
        }
        if (publicacion.getNombre() == null || publicacion.getNombre().isEmpty()) {
            throw new HubiException("El nombre de la publicación no puede estar vacío");
        }

        try {
            // Procesar imagen si existe
            if (file != null && !file.isEmpty()) {
                publicacion.setImagen(file.getBytes());
            }

            // Fijar fecha actual
            publicacion.setFecha(LocalDateTime.now());

            // Extraer y guardar hashtags
            String hashtags = extraerHashtags(publicacion.getMensaje());
            publicacion.setHashtags(hashtags);

            // Guardar en BD
            return publicacionRepositorio.save(publicacion);

        } catch (IOException e) {
            throw new HubiException("Error al procesar la imagen de la publicación", e);
        } catch (DataAccessException e) {
            throw new HubiException("Error al guardar la publicación en la base de datos", e);
        } catch (Exception e) {
            throw new HubiException("Error inesperado al crear la publicación", e);
        }
    }

    @Override
    public Publicacion obtenerPublicacionPorId(Long id) {
        return publicacionRepositorio.findById(id)
            .orElseThrow(() -> new HubiException("Publicación no encontrada con ID: " + id));
    }

    // ----------------------------------------------------------------
    // Utilitario para extraer hashtags únicos y almacenarlos en minúscula
    // ----------------------------------------------------------------
    private String extraerHashtags(String mensaje) {
        Pattern pattern = Pattern.compile("#\\w+");
        return pattern.matcher(mensaje).results()
            .map(m -> m.group().toLowerCase())
            .distinct()
            .collect(Collectors.joining(","));
    }
}
