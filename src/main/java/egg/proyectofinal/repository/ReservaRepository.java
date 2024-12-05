package egg.proyectofinal.repository;

import egg.proyectofinal.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author 54113
 */
@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {
}
