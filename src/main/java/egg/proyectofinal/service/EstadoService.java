package egg.proyectofinal.service;

import egg.proyectofinal.model.Estado;
import egg.proyectofinal.repository.EstadoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author 54113
 */

@Service
public class EstadoService {

	@Autowired  
    private EstadoRepository estadoRepository; // Acceso al EstadoRepository  
    
    public Estado crear(Estado estado) {  
        return estadoRepository.save(estado); // Guarda en la BD  
    }  
    
    public List<Estado> lista() {  
        return estadoRepository.findAll(); // Devuelve todos los estados  
    }  

    public void eliminarPorId(Long id) {  
        estadoRepository.deleteById(id); // Elimina por ID  
    }  
    
    public Estado buscarPorId(Long id) {  
        return estadoRepository.findById(id).orElse(null); // Buscar por ID  
    }  
    
    public Estado modificar(Estado estado) {  
        return estadoRepository.save(estado); // Modifica un estado  
    }  
}
