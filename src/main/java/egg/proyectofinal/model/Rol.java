package egg.proyectofinal.model;

import java.io.Serializable;

/**
 *
 * @author 54113
 */
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Rol implements Serializable {
    
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
    private String descripcion;
	
    public Long getId() {
		return id;
	}
	
    public void setId(Long id) {
		this.id = id;
	}
	
    public String getDescripcion() {
		return descripcion;
	}
	
    public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}