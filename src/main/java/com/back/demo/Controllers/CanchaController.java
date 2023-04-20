package com.back.demo.Controllers;


import com.back.demo.CRUD.CanchaRepo;
import com.back.demo.Model.Cancha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Service
@RestController
@RequestMapping("/cancha")
public class CanchaController {

    @Autowired
    private static CanchaRepo canchaRepo;

    @GetMapping( "/listar")
    public ResponseEntity<?> getUsuarios(){
        List<Cancha> canchas = new ArrayList<>();
        canchaRepo.findAll().forEach(canchas::add);
        return ResponseEntity.ok(canchas);
    }

    @PostMapping("/crear_cancha")
    public static ResponseEntity<?> insertar(@RequestBody Cancha cancha){
        canchaRepo.save(cancha);
        return ResponseEntity.status(HttpStatus.CREATED).body(cancha);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUsuario(@RequestBody Cancha cancha) {
        canchaRepo.save(cancha);
        return ResponseEntity.status(HttpStatus.CREATED).body(cancha);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getUsuario(@PathVariable("id") int id) throws Exception{
        Cancha cancha = canchaRepo.findById(id).get();
        return ResponseEntity.ok(cancha);
    }
}
