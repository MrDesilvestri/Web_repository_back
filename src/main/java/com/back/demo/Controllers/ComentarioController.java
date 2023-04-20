package com.back.demo.Controllers;

import com.back.demo.CRUD.ComentarioRepo;
import com.back.demo.Model.Comentario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Service
@RequestMapping("/comentario")
public class ComentarioController {

    ComentarioRepo comentarioRepo;
    @Autowired
    public ComentarioController(ComentarioRepo comentarioRepo) {
        this.comentarioRepo = comentarioRepo;
    }

    @CrossOrigin
    @GetMapping( "/listar")
    public ResponseEntity<List<Comentario>> getUsuarios(){
        List<Comentario> comentarios = new ArrayList<>();
        comentarioRepo.findAll().forEach(comentarios::add);
        return ResponseEntity.ok(comentarios);
    }
    @CrossOrigin
    @PostMapping("/crear_comentario")
    public ResponseEntity<?> insertar(@RequestBody Comentario comentario) throws Exception{
        if(comentario == null){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("No se puede crear un comentario vacio");
        }else{
            comentarioRepo.save(comentario);
            return ResponseEntity.status(HttpStatus.CREATED).body(comentario);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comentario> updateUsuario(@RequestBody Comentario comentario) {
        return ResponseEntity.status(HttpStatus.CREATED).body(comentario);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Comentario> getUsuario(@PathVariable("id") int id) throws Exception{
        Comentario comentario = comentarioRepo.findById(id).get();
        return ResponseEntity.ok(comentario);
    }
}
