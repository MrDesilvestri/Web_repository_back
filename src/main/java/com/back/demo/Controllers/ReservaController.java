package com.back.demo.Controllers;


import com.back.demo.CRUD.ReservaRepo;
import com.back.demo.Model.Reserva;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Service
@RestController
@RequestMapping("/reserva")
public class ReservaController {
    ReservaRepo reservaRepo;
    @Autowired
    public ReservaController(ReservaRepo reservaRepo) {
        this.reservaRepo = reservaRepo;
    }
    @CrossOrigin
    @GetMapping( "/listar")
    public ResponseEntity<?> getReservas(){
       List<Reserva> reservas = new ArrayList<>();
         reservaRepo.findAll().forEach(reservas::add);
         if (reservas.isEmpty())
             return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay reservas");
         else
             return ResponseEntity.status(HttpStatus.OK).body(reservas);
    }

    @CrossOrigin
    @PostMapping("/crear_reserva")
    public ResponseEntity<?> insertar(@RequestBody Reserva reserva) throws Exception{
        reservaRepo.save(reserva);
        if (reserva == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se pudo crear la reserva");
        else
            return ResponseEntity.status(HttpStatus.CREATED).body(reserva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUsuario(@RequestBody Reserva reserva) {

        Reserva updatedReserva = reservaRepo.save(reserva);
        return ResponseEntity.ok(updatedReserva);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getUsuario(@PathVariable("id") int id){
        Reserva reserva = reservaRepo.findById(id).get();
        return ResponseEntity.ok(reserva);
    }
}
