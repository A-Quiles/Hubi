package es.hubi.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import es.hubi.modelo.Publicacion;

public interface PublicacionRepositorio extends JpaRepository<Publicacion, Long>, PublicacionRepositorioCustom {

	 @Query("SELECT p FROM Publicacion p ORDER BY p.fecha DESC LIMIT 500")
	 List<Publicacion> findTop500ByOrderByFechaDesc();

	 @Query("SELECT p FROM Publicacion p WHERE p.categoria.id = :categoriaId ORDER BY p.fecha DESC LIMIT 500")
	 List<Publicacion> findTop500ByCategoriaIdOrderByFechaDesc(@Param("categoriaId") Long categoriaId);
}
