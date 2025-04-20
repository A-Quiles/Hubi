package es.hubi.repositorio;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import es.hubi.modelo.Publicacion;

public class PublicacionRepositorioImpl implements PublicacionRepositorioCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Publicacion> buscarPorCategoriaYHashtags(Long categoriaId, List<String> hashtags) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Publicacion> query = cb.createQuery(Publicacion.class);
        Root<Publicacion> root = query.from(Publicacion.class);

        List<Predicate> predicates = new ArrayList<>();

        if (categoriaId != null) {
            predicates.add(cb.equal(root.get("categoria").get("id"), categoriaId));
        }

        if (hashtags != null && !hashtags.isEmpty()) {
            for (String tag : hashtags) {
                String likePattern = "%#" + tag.toLowerCase() + "%";
                predicates.add(cb.like(cb.lower(root.get("hashtags")), likePattern));
            }
        }

        query.select(root)
            .where(cb.and(predicates.toArray(new Predicate[0])))
            .orderBy(cb.desc(root.get("fecha")));

        return entityManager.createQuery(query)
                .setMaxResults(500)
                .getResultList();
    }
}

