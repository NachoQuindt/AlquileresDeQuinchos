package egg.proyectofinal.model;  

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity  
public class Estado implements Serializable {  

	private static final long serialVersionUID = 1L;

	@Id  
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
    private Long idEstado;  
    
    private String descripcion;  

    // Getters y setters  

    public Long getIdEstado() {  
        return idEstado;  
    }  

    public void setIdEstado(Long idEstado) {  
        this.idEstado = idEstado;  
    }  

    public String getDescripcion() {  
        return descripcion;  
    }  

    public void setDescripcion(String descripcion) {  
        this.descripcion = descripcion;  
    }  
}