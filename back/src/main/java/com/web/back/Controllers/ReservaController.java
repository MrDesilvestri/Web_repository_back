package com.web.back.Controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
    
    @Autowired
    private ReservaService reservaService;
    
    @GetMapping
    public List<Reserva> obtenerTodasLasReservas() {
        return reservaService.buscarTodasLasReservas();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Reserva>> obtenerReservaPorId(@PathVariable Long id) {
        Optional<Reserva> reserva = reservaService.buscarReservaPorId(id);
        if (reserva != null) {
            return ResponseEntity.ok(reserva);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping
    public ResponseEntity<Reserva> crearReserva(@RequestBody Reserva reserva) {
        Reserva nuevaReserva = reservaService.guardarReserva(reserva);
        return ResponseEntity.created(URI.create("/reservas/" + nuevaReserva.getId())).body(nuevaReserva);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Reserva> actualizarReserva(@PathVariable Long id, @RequestBody Reserva reserva) {
        Reserva reservaExistente = reservaService.buscarReservaPorIdC(id);
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
    public ResponseEntity<Void> eliminarReserva(@PathVariable Long id) {
        Reserva reservaExistente = reservaService.buscarReservaPorIdC(id);
        if (reservaExistente != null) {
            reservaService.eliminarReserva(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
