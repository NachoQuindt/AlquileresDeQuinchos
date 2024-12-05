/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package egg.proyectofinal.service;

import egg.proyectofinal.model.Reserva;
import egg.proyectofinal.model.Usuario;
import egg.proyectofinal.repository.ReservaRepository;
import egg.proyectofinal.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author 54113
 */
@Service
public class ReservaService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private ReservaRepository reservaRepository;
    
    @Transactional
    public Reserva crear (LocalDate fecha, String comentario, List<String> servicios, Long idusuario){
        Usuario usuarioPorId=usuarioRepository.findById(idusuario.intValue()).get();
        Reserva reserva = new Reserva();
        reserva.setComentario(comentario);
        reserva.setFecha(fecha);
        //reserva.setServicios(servicios);
        reserva.setUsuario(usuarioPorId);
        reservaRepository.save(reserva);
        return reserva;
    }
}
