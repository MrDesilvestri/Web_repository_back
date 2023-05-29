package com.web.back.Controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import com.web.back.Entities.Cancha;
import com.web.back.Entities.Reserva;
import com.web.back.Exceptions.ReservaExceptions.ReservaNotFoundException;
import com.web.back.repository.CanchaRepository;
import com.web.back.repository.ReservaRepository;
import com.web.back.repository.UsuarioRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 4200)
@RequestMapping("/api/reservations")
public class ReservaController {

    @Autowired
    private ReservaRepository reservaRepository;
    @Autowired
    private CanchaRepository canchaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/create")
    public ResponseEntity<Reserva> crearReserva(@RequestBody Reserva reserva) {
        Reserva nuevaReserva = reservaRepository.save(reserva);
        return ResponseEntity.created(URI.create("/api/reservations/" + nuevaReserva.getId())).body(nuevaReserva);
    }
}
