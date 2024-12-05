package egg.proyectofinal.Controler;

import egg.proyectofinal.model.Posteo;
import egg.proyectofinal.service.PosteoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path="/post") 
public class PosteoController {
    
	@Autowired
	private PosteoService posteoService;

    @GetMapping("/postear")
    public String post(Model model){
        Posteo post = new Posteo();
        model.addAttribute("post",post);
        List<Posteo> lista=posteoService.lista();
        model.addAttribute("listaDePosteo", lista);
       return "post/postear";
    }
 
    @PostMapping("/crear")
    public String crear (@ModelAttribute("user") Posteo posteo, Model model){
        System.out.println("Propiedad:" + posteo.getPropiedad());
        System.out.println("Usuario:" + posteo.getUsuario());        
        posteoService.crear(posteo);
        Posteo post = new Posteo(); 
        model.addAttribute("post",post);
        List<Posteo> lista=posteoService.lista();
        model.addAttribute("listaDePosteos", lista);
        return "post/postear";
    }
    
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, Model model) {
        Posteo post = new Posteo(); 
        model.addAttribute("post",post);
        posteoService.eliminarPorId(id); 
        List<Posteo> lista=posteoService.lista();
        model.addAttribute("listaDePosto", lista);
        return "post/postear";
    }
}
