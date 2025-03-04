package es.hubi.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import es.hubi.modelo.Categoria;

public interface CategoriaRepositorio extends JpaRepository<Categoria, Long> {
}
