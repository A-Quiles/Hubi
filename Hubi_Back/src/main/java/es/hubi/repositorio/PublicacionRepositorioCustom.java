package es.hubi.repositorio;
import java.util.List;

import es.hubi.modelo.Publicacion;

public interface PublicacionRepositorioCustom {
    List<Publicacion> buscarPorCategoriaYHashtags(Long categoriaId, List<String> hashtags);
}

