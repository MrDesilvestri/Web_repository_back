package com.backend.back.Controllers;

import com.backend.back.CRUD.ReservaRepo;
import com.backend.back.Model.Reserva;
import com.backend.back.Model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
@RequestMapping("/reserva")
public class ReservaController {
    @Autowired
    ReservaRepo reservaRepo;

    @GetMapping( "/listar")
    public ResponseEntity<List<Reserva>> getUsuarios(){
        List<Reserva> reservas = reservaRepo.findAll();
        return ResponseEntity.ok(reservas);
    }

    @PostMapping("/")
    public ResponseEntity<Reserva> insertar(@RequestBody Reserva reserva) throws Exception{
        Reserva reserva1 = new Reserva(reserva);
        reservaRepo.save(reserva1);
        return ResponseEntity.ok(reserva1);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reserva> updateUsuario(@RequestBody Reserva reserva) {

        Reserva updatedReserva = reservaRepo.save(reserva);
        return ResponseEntity.ok(updatedReserva);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Reserva> getUsuario(@PathVariable("id") int id) throws Exception{
        Reserva reserva = reservaRepo.findById(id).get();
        return ResponseEntity.ok(reserva);
    }
}
