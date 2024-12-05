package egg.proyectofinal.service;

import egg.proyectofinal.model.Posteo;
import egg.proyectofinal.repository.PosteoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author 54113
 */
@Service
public class PosteoService {
    
    @Autowired
    private PosteoRepository posteoRepository; //aca accedo a UsuarioRepository
    
    public Posteo crear(Posteo x){
        Posteo postConId = posteoRepository.save(x);//este metodo me lo graba en la BD
        return postConId;
    }
    
    public List<Posteo> lista(){
        List<Posteo> listaPost = posteoRepository.findAll();
        return listaPost;
    }

    public void eliminarPorId(Long id) {
        posteoRepository.deleteById(id.intValue()); //y aca uso un metodo del repositorio
    }
    
    
    public Posteo buscarPorId (Long id){
        Posteo posteoPorId=posteoRepository.findById(id.intValue()).get();
        return posteoPorId;
    }
    
    public Posteo modificar (Posteo x){
        return posteoRepository.save(x);        
    }
}
