package egg.proyectofinal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egg.proyectofinal.model.Propiedad;
import egg.proyectofinal.repository.PropiedadRepository;

/**
 *
 * @author 54113
 */
@Service
public class PropiedadService {

    @Autowired
    private PropiedadRepository propiedadRepository;
    
    public List<Propiedad> lista(){
        List<Propiedad> listaPropiedad = propiedadRepository.findAll();
        return listaPropiedad;
    }

    public void eliminarPorId(Long id) {
        propiedadRepository.deleteById(id); //y aca uso un metodo del repositorio
    }
    
    public Propiedad buscarPorId (Long id){
        Propiedad propiedadPorId=propiedadRepository.findById(id).get();
        return propiedadPorId;
    }
    
    public Propiedad modificar (Propiedad x){
        return propiedadRepository.save(x);
    }
    
    public List<Propiedad> buscarPorNombre(String nombreABuscar){
        System.out.println("Service nombre: " + nombreABuscar);
        return propiedadRepository.findByNombre(nombreABuscar);
    }
}
