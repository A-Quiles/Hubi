package es.hubi.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import es.hubi.modelo.Publicacion;

public interface PublicacionRepositorio extends JpaRepository<Publicacion, Long> {
  
}
