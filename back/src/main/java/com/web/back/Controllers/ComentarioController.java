package com.web.back.Controllers;

import java.util.HashMap;
import java.util.Map;
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

import com.web.back.Entities.Comentario;
import com.web.back.Exceptions.ComentarioNotFoundException;
import com.web.back.Service.ComentarioService;

@RestController
@RequestMapping("/comentario")
public class ComentarioController {

    @Autowired
    private ComentarioService comentarioService;

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Comentario>> getComentarioById(@PathVariable(value = "id") int comentarioId)
            throws ComentarioNotFoundException {
        Optional<Comentario> comentario = comentarioService.buscarComentarioPorId(comentarioId);
        return ResponseEntity.ok().body(comentario);
    }

    @PostMapping("/")
    public Comentario createComentario(@RequestBody Comentario comentario) {
        return comentarioService.guardarComentario(comentario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comentario> updateComentario(@PathVariable(value = "id") Long comentarioId,
            @RequestBody Comentario comentario) throws ComentarioNotFoundException {
        Comentario updatedComentario = comentarioService.actualizarComentario(comentarioId, comentario);
        return ResponseEntity.ok(updatedComentario);
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteComentario(@PathVariable(value = "id") Long comentarioId)
            throws ComentarioNotFoundException {
        comentarioService.eliminarComentario(comentarioId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
