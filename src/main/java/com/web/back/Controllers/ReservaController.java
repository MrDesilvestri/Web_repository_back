package com.web.back.Controllers;

import java.net.URI;

import com.web.back.Entities.Reserva;
import com.web.back.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 4200)
@RequestMapping("/api/reservations")
public class ReservaController {

    @Autowired
    private ReservaRepository reservaRepository;

    @PostMapping("/create")
    public ResponseEntity<Reserva> crearReserva(@RequestBody Reserva reserva) {
        Reserva nuevaReserva = reservaRepository.save(reserva);
        return ResponseEntity.created(URI.create("/api/reservations/" + nuevaReserva.getId())).body(nuevaReserva);
    }
}
