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
@RequestMapping("/api/reservations")
public class ReservaController {

    @Autowired
    private ReservaRepository reservaRepository;
    @Autowired
    private CanchaRepository canchaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;



    /*@Operation(summary = "Get the orders list filteded by email or field title with a sort order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Orders listed", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Reserva.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ReservaNotFoundException.class))) })
    @CrossOrigin
    @GetMapping("/list")
    public ResponseEntity<?> getAll() {

    ) {
            List<Cancha> list = reservaRepository.getAllOrders(nickname, filter, limit);
            return ResponseEntity.ok(list);
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
    }*/
}
