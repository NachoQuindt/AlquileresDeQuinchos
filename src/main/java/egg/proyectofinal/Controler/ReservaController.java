package egg.proyectofinal.Controler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author 54113
 */
@Controller
@RequestMapping(path="/reserva")
public class ReservaController {

    @GetMapping("/inicio") // como accedo a este metodo a traves de un get
    public String inicio(){
        return "reserva/inicio";//en pantalla me va a mostrar el html inicio
    }
}
