/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package egg.proyectofinal.Controler;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import egg.proyectofinal.model.ServicioEli;



/**
 *
 * @author 54113
 */
@Controller
@RequestMapping(path="/servicio")

public class ServicioControler {
    
    @GetMapping("/inicio")
    public String inicio(Model model){
        //obtener los servicios
        ServicioEli s = new ServicioEli();
        s.setDescripcion("wifi");
        s.setValor(Double.valueOf("100.5"));
        model.addAttribute("Servicio1", s);
        return "servicio/inicio";
    }
}
