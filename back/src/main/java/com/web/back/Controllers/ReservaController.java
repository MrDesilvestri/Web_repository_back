package com.web.back.Controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @GetMapping
    public ResponseEntity<?> obtenerTodasLasReservas() {
        List<Reserva> reservas = reservaService.listAll();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(reservas);
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
    
    @PostMapping
    public ResponseEntity<Reserva> crearReserva(@RequestBody Reserva reserva) {
        Reserva nuevaReserva = reservaService.guardarReserva(reserva);
        return ResponseEntity.created(URI.create("/reservas/" + nuevaReserva.getId())).body(nuevaReserva);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Reserva> actualizarReserva(@PathVariable int id, @RequestBody Reserva reserva) {
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
    public ResponseEntity<Void> eliminarReserva(@PathVariable int id) {
        Reserva reservaExistente = reservaService.listById(id);
        if (reservaExistente != null) {
            reservaService.eliminarReserva(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
