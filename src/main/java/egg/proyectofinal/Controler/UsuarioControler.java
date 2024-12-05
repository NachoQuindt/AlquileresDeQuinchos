package egg.proyectofinal.Controler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import egg.proyectofinal.model.Rol;
import egg.proyectofinal.model.Usuario;
import egg.proyectofinal.repository.RolRepository;
import egg.proyectofinal.service.UsuarioService;

/**
 *
 * @author 54113
 */
@Controller
@RequestMapping(path="/usuario") //esto es mi url de acceso a este controlador como usuario
public class UsuarioControler {
    
    @Autowired
    private UsuarioService usuarioService;//al tenerlo como autowired lo instancia automatico y puedo acceder a sus atributos y metodos
            
    @Autowired
    private RolRepository rolRepository;
    
    @GetMapping("/inicio") // como accedo a este metodo a traves de un get
    public String inicio(Model model){
        Usuario user = new Usuario(); //aca creo un usuario user
        model.addAttribute("user",user);
        List<Usuario> lista=usuarioService.lista();
        model.addAttribute("listaDeUsuario", lista);
       return "usuario/inicio";//en pantalla me va a mostrar el html inicio
    }
    
    @GetMapping("/preregistro") //como accedo a este metodo desde un get.
    public String preregistrar (Model model){ //atenti que aparece el Model un objeto contenedor de otros objetos
        Usuario user = new Usuario(); //aca creo un usuario user
        model.addAttribute("user",user); //y aca se llena el objeto model con el user
        return "usuario/registro"; //en pantalla me va a mostrar el html registro
    }
    
    @PostMapping("/crear")//este recibe un objeto model con los datos del usuario
    public String crear (@ModelAttribute("user") Usuario usuario, Model model){
        System.out.println("nombre:" + usuario.getNombre());
        System.out.println("apellido:" + usuario.getApellido());
        //System.out.println("numeroTelefono:" + usuario.getNumeroTelefono());
        System.out.println("mail: " + usuario.getEmail());
        usuarioService.crear(usuario);//aca me comunico con el usuarioService para usar su metodo crear
        Usuario user = new Usuario(); //aca creo un usuario user
        model.addAttribute("user",user);
        List<Usuario> lista=usuarioService.lista();
        model.addAttribute("listaDeUsuario", lista);
        return "usuario/inicio";
    }
    
    @GetMapping("/eliminar/{id}")//este metodo o esta ruta se activa cuando toco el boton delete al lado de la lista de nombres
    public String eliminar(@PathVariable Long id, Model model) {
        Usuario user = new Usuario(); //aca creo un usuario user
        model.addAttribute("user",user);
        usuarioService.eliminarPorId(id); // aca llamo al metodo que va a eliminar en el service
        List<Usuario> lista=usuarioService.lista();//aca vuelvo a listar los elementos de la lista
        model.addAttribute("listaDeUsuario", lista);//esto es como mando un objeto desde el controlador a la vista
        return "usuario/inicio";
    }
    
    @PostMapping("/buscarPorNombre")
    public String buscarPorNombre(@ModelAttribute("user") Usuario usuario, Model model){//el model user es el que va vacio al html y el usuario es como vuelve lleno
        Usuario user = new Usuario(); //aca creo un usuario user
        System.out.println("buscar nombre: " + usuario.getNombre());
        model.addAttribute("user",user);
        List<Usuario> lista=null;
        if (usuario.getNombre()==null || usuario.getNombre().length()==0) {
            lista=usuarioService.lista();            
        } else {
            lista= usuarioService.buscarPorNombre(usuario.getNombre());
        }
        System.out.println("size: " + lista.size());
        model.addAttribute("listaDeUsuario", lista);//model transforma la lista en pasable a html
        return "usuario/inicio";
    }
    
    @GetMapping("/modificarUsuario/{id}")
    public String modificarUsuario (@PathVariable Long id, Model model){
        Usuario usuarioporId=usuarioService.buscarPorId(id);
        List <Rol> roles =rolRepository.findAll();
        model.addAttribute("roles",roles);
        model.addAttribute("idRolSeleccionado", Long.getLong("0"));
        model.addAttribute("user",usuarioporId);
    return "usuario/modificar";   
    }
    
       
    @PostMapping("/modificarUsuarioEnBase")
    public String modificarUsuarioEnBase(@ModelAttribute("user") Usuario usuario, Model model){
        usuarioService.modificar(usuario);//aca me comunico con el usuarioService para usar su metodo crear
        Usuario user = new Usuario(); //aca creo un usuario user
        model.addAttribute("user",user);
        List<Usuario> lista=usuarioService.lista();
        model.addAttribute("listaDeUsuario", lista);
        
        return "usuario/inicio";
    }
}
