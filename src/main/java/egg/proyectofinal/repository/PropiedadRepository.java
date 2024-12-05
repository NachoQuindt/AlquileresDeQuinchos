package egg.proyectofinal.repository;

import egg.proyectofinal.model.Propiedad;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para gestionar las operaciones de la entidad Propiedad.
 * Extiende JpaRepository para ofrecer CRUD básico.
 */
@Repository // Marca esta interfaz como un repositorio de Spring
public interface PropiedadRepository extends JpaRepository<Propiedad, Long> {
    
	// No es necesario implementar métodos CRUD, JpaRepository los proporciona automáticamente.
    @Query("FROM Propiedad u WHERE u.nombre = ?1")
    public List<Propiedad> findByNombre(String nombre);

}
