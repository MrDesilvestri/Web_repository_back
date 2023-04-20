package com.backend.back.Controllers;

import com.backend.back.CRUD.CanchaRepo;
import com.backend.back.Model.Cancha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
@RequestMapping("/cancha")
public class CanchaController {
    @Autowired
    CanchaRepo canchaRepo;

    @GetMapping( "/listar")
    public ResponseEntity<List<Cancha>> getUsuarios(){
        List<Cancha> canchas = canchaRepo.findAll();
        return ResponseEntity.ok(canchas);
    }

    @PostMapping("/")
    public ResponseEntity<Cancha> insertar(@RequestBody Cancha cancha) throws Exception{
        Cancha cancha1 = new Cancha(cancha);
        canchaRepo.save(cancha1);
        return ResponseEntity.ok(cancha1);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cancha> updateUsuario(@RequestBody Cancha cancha) {

        Cancha updatedCancha = new Cancha(cancha);
        return ResponseEntity.ok(updatedCancha);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Cancha> getUsuario(@PathVariable("id") int id) throws Exception{
        Cancha cancha = canchaRepo.findById(id).get();
        return ResponseEntity.ok(cancha);
    }

}
