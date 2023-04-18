package com.web.back.Controllers;

import java.net.URI;
import java.util.List;

import com.web.back.DTO.ReservaDTO;
import com.web.back.Entities.Cancha;
import com.web.back.Entities.Usuario;
import com.web.back.Service.CanchaService;
import com.web.back.Service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.back.Entities.Reserva;
import com.web.back.Service.ReservaService;

@RestController
@RequestMapping("/reserva")
public class ReservaController {
    
    private final ReservaService reservaService;
    private final UsuarioService usuarioService;
    private final CanchaService canchaService;

    public ReservaController(ReservaService reservaService, UsuarioService usuarioService, CanchaService canchaService) {
        this.reservaService = reservaService;
        this.usuarioService = usuarioService;
        this.canchaService = canchaService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> obtenerTodasLasReservas() {
        List<Reserva> reservas = reservaService.listAll();
        if (reservas.size()>0){
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(reservas);
        }else{
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("No hay reservas en la base de datos");
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerReservaPorId(@PathVariable int id) {
        Reserva reserva = reservaService.listById(id);
        if (reserva != null) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(reserva);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró la reserva con id " + id);
        }
    }
    
    @PostMapping("/crear_reserva")
    public ResponseEntity<?> crearReserva(@RequestBody ReservaDTO reserva) {
        Usuario usuario = usuarioService.listById(reserva.getUsuarioId());
        Cancha cancha = canchaService.listById(reserva.getCanchaId());
        Reserva nuevaReserva = new Reserva(usuario, cancha, reserva.getFechaInicio(), reserva.getFechaFin());
        reservaService.guardarReserva(nuevaReserva);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Nueva reserva creada : "+nuevaReserva);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarReserva(@PathVariable int id, @RequestBody Reserva reserva) {
        Reserva reservaExistente = reservaService.listById(id);
        if (reservaExistente != null) {
            reservaExistente.setFechaInicio(reserva.getFechaInicio());
            reservaExistente.setFechaFin(reserva.getFechaFin());
            reservaExistente.setCancha(reserva.getCancha());
            reservaExistente.setUsuario(reserva.getUsuario());
            reservaService.guardarReserva(reservaExistente);
            return ResponseEntity.ok(reservaExistente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarReserva(@PathVariable int id) {
        Reserva reservaExistente = reservaService.listById(id);
        if (reservaExistente != null) {
            reservaService.eliminarReserva(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
