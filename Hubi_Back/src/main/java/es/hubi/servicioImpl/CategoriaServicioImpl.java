package es.hubi.servicioImpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import es.hubi.modelo.Categoria;
import es.hubi.repositorio.CategoriaRepositorio;
import es.hubi.servicio.CategoriaServicio;

@Service
public class CategoriaServicioImpl implements CategoriaServicio {

    @Autowired
    private CategoriaRepositorio categoriaRepositorio;
    
    @Override
    public List<Categoria> listarCategorias() {
        return categoriaRepositorio.findAll();
    }
}
