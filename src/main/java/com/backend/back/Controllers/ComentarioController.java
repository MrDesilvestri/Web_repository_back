package com.backend.back.Controllers;

import com.backend.back.CRUD.ComentarioRepo;
import com.backend.back.Model.Comentario;
import com.backend.back.Model.Reserva;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
@RequestMapping("/comentario")
public class ComentarioController {
    @Autowired
    ComentarioRepo comentarioRepo;
    @GetMapping( "/listar")
    public ResponseEntity<List<Comentario>> getUsuarios(){
        List<Comentario> comentarios = comentarioRepo.findAll();
        return ResponseEntity.ok(comentarios);
    }

    @PostMapping("/")
    public ResponseEntity<Comentario> insertar(@RequestBody Comentario comentario) throws Exception{
        Comentario comentario1 = new Comentario(comentario);
        comentarioRepo.save(comentario1);
        return ResponseEntity.ok(comentario1);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comentario> updateUsuario(@RequestBody Comentario comentario) {

        Comentario updatedComentario = new Comentario(comentario);
        return ResponseEntity.ok(updatedComentario);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Comentario> getUsuario(@PathVariable("id") int id) throws Exception{
        Comentario comentario = comentarioRepo.findById(id).get();
        return ResponseEntity.ok(comentario);
    }
}
