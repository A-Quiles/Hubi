package es.hubi.controlador;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import es.hubi.modelo.Categoria;
import es.hubi.servicio.CategoriaServicio;

@RestController
@RequestMapping("/categorias")
public class CategoriaControlador {

    @Autowired
    private CategoriaServicio categoriaServicio;
    
    // Listar todas las categorías
    @GetMapping
    public List<Categoria> listarCategorias() {
        return categoriaServicio.listarCategorias();
    }
}
