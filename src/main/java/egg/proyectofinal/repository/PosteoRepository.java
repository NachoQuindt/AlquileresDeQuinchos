package egg.proyectofinal.repository;

import egg.proyectofinal.model.Posteo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author user
 */
@Repository
public interface PosteoRepository extends JpaRepository<Posteo, Integer> {
}