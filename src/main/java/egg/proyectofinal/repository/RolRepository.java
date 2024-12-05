package egg.proyectofinal.repository;

/**
 *
 * @author 54113
 */
import egg.proyectofinal.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {
	
    public Rol findByDescripcion(String descripcion);
    
}