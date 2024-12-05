package egg.proyectofinal.Controler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import egg.proyectofinal.exceptions.MiException;
import egg.proyectofinal.model.Rol;
import egg.proyectofinal.repository.RolRepository;
import egg.proyectofinal.service.UsuarioService;

/**
 *
 * @author 54113
 */
@Controller
@RequestMapping(path="/homecontroller")
public class HomeController {
    
    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired 
    private RolRepository rolRepository;

    @GetMapping("/inicio")
    public String inicio(){
    	//return "homecontroller/index";
    	return "homecontroller/prueba";
    }

    @GetMapping("/registrar")
    public String registrar (Model model){
        List <Rol> roles =rolRepository.findAll();
        model.addAttribute("roles",roles);
        //UsuarioAux x = new UsuarioAux();
        model.addAttribute("idRolSeleccionado", Long.getLong("0"));
        return "homecontroller/registro";
    }
       
    @PostMapping("/registro")
    public String registro(@RequestParam String nombre, @RequestParam String apellido, 
                            @RequestParam String email, @RequestParam Long idRolSeleccionado,
                            @RequestParam String contrasena, String contrasena2, ModelMap modelo){  
        System.out.println("id= " + idRolSeleccionado);
        try {
            usuarioService.registrar(nombre, apellido, email, idRolSeleccionado, contrasena, contrasena2);
            modelo.put("exito", "Usuario registrado correctamente");
            //return "homecontroller/index";
            return "homecontroller/prueba";
        } catch (MiException ex) {
            System.out.println("MiException: " + ex.getMessage());
            modelo.put("error", ex.getMessage());
            return "homecontroller/registro";
        }
    }
    
    @GetMapping("/login")
    public String login(@RequestParam(required = false)String error, ModelMap modelo){
        if(error != null){
            modelo.put("error", "Usuario o contraseña invalida");
        }
        return "homecontroller/login";
    }
}
