package cl.vasquez.nomadapp_backend.repository;

import cl.vasquez.nomadapp_backend.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Jpa es una interface (un CONTRATO). Spring Data genera la implementación en runtime
 * -> Por eso se extiende, no se implementa.
 */
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

}
