package egg.proyectofinal.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import egg.proyectofinal.exceptions.MiException;
import egg.proyectofinal.model.Rol;
import egg.proyectofinal.model.UserInfoDetails;
import egg.proyectofinal.model.Usuario;
import egg.proyectofinal.repository.RolRepository;
import egg.proyectofinal.repository.UsuarioRepository;
import jakarta.transaction.Transactional;

/**
 *
 * @author 54113
 */
@Service
public class UsuarioService implements UserDetailsService {
    
    @Autowired
    private UsuarioRepository usuarioRepository; //aca accedo a UsuarioRepository
    
    @Autowired 
    private RolRepository rolRepositorio;
    
    @Transactional
    public void registrar (String nombre, String apellido, String email, Long idRolSeleccionado, String contrasena, String contrasena2) throws MiException{
        validar(nombre, email, contrasena, contrasena2);
        Usuario  usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setEmail(email);
        usuario.setApellido(apellido);
        
        BCryptPasswordEncoder p = new BCryptPasswordEncoder();
        usuario.setContrasena(p.encode(contrasena));
        
        Optional<Rol> optRol = rolRepositorio.findById(idRolSeleccionado);// busca el rol del id mandado
        Rol rol = optRol.get();//no devuelve el objeto rol lo devuelve como optional y con el get lo saco
        usuario.setRol(rol);
        usuarioRepository.save(usuario);
        
    }
    
    private void validar(String nombre, String email, String contrasena, String contrasena2) throws MiException{
        if ( nombre == null || nombre.isEmpty()){
            throw new MiException("El nombre no puede ser nulo o estar vacio");
        }
        if ( email == null || email.isEmpty()){
            throw new MiException("El mail no puede ser nulo o estar vacio");
        }
        if ( contrasena == null || contrasena.isEmpty()|| contrasena.length()<=5){
            throw new MiException("la contraseña no puede estar vacio o tener menos de cinco caracteres");
        }
        if ( !contrasena.equals(contrasena2)){
            throw new MiException("Las contraseñas ingresadas deben ser iguales");
        }
        
    }
    
    @Deprecated // Uso el metodo registrar para crear usuarios
    public Usuario crear(Usuario x){
        Usuario usuarioConId = usuarioRepository.save(x);//este metodo me lo graba en la BD
        return usuarioConId;
    }
    
    public List<Usuario> lista(){
        List<Usuario> listaUsuarios = usuarioRepository.findAll();
        return listaUsuarios;
    }

    public void eliminarPorId(Long id) {
        usuarioRepository.deleteById(id.intValue()); //y aca uso un metodo del repositorio
    }
    
    public List<Usuario> buscarPorNombre(String nombreABuscar){
        System.out.println("Service nombre: " + nombreABuscar);
        return usuarioRepository.findByNombre(nombreABuscar);
    }
    
    public Usuario buscarUnoPorNombre(String nombreABuscar){
        System.out.println("Service nombre: " + nombreABuscar);
        return usuarioRepository.findOneByNombre(nombreABuscar);
    }
    public Usuario buscarPorId (Long id){
        Usuario usuarioPorId=usuarioRepository.findById(id.intValue()).get();
        return usuarioPorId;
    }
    
    public Usuario modificar (Usuario x){
        return usuarioRepository.save(x);
        
    }

    @Override
    public UserDetails loadUserByUsername(String nombre) throws UsernameNotFoundException {
    	System.out.println("SpringFramework -> loadUserByUsername");
        System.out.println("Nombre logueado: " + nombre);
        Usuario usuario = usuarioRepository.findOneByNombre(nombre);
        if (usuario!=null) {
//            System.out.println("ID usuario encontrado: " + usuario.getId());
//            List<GrantedAuthority>permisos = new ArrayList<GrantedAuthority>();
//            System.out.println("Rol asignado: " + usuario.getRol().getDescripcion());
//            GrantedAuthority p = new SimpleGrantedAuthority(usuario.getRol().getDescripcion());
//            permisos.add(p);
//            return new User(usuario.getNombre(), usuario.getContrasena(), permisos);
System.out.println("Rol asignado: " + usuario.getRol().getDescripcion());
        	UserInfoDetails userInfoDetails = new UserInfoDetails(usuario);
            return userInfoDetails;
            
        }else {
            System.out.println("EXIT sin usuario!!");
            return null;
        }
    }
}
