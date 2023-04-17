package com.web.back.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.back.Entities.Cancha;
import com.web.back.Service.CanchaService;

@RestController
@RequestMapping("/canchas")
public class CanchaController {

    @Autowired
    private CanchaService canchaService;

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Cancha>> obtenerCancha(@PathVariable("id") Long id) {
        Optional<Cancha> cancha = canchaService.buscarCanchaPorId(id);
        return ResponseEntity.ok(cancha);
    }

    @PostMapping
    public ResponseEntity<Cancha> crearCancha(@RequestBody Cancha cancha) {
        Cancha nuevaCancha = canchaService.guardarCancha(cancha);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaCancha);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cancha> actualizarCancha(@PathVariable("id") Long id, @RequestBody Cancha cancha) {
        Cancha canchaExistente = canchaService.buscarCanchaPorIdC(id);
        canchaExistente.setNombre(cancha.getNombre());
        canchaExistente.setUbicacion(cancha.getUbicacion());
        canchaExistente.setDescripcion(cancha.getDescripcion());
        canchaExistente.setPrecioHora(cancha.getPrecioHora());
        Cancha canchaActualizada = canchaService.actualizarCancha(id, canchaExistente);
        return ResponseEntity.ok(canchaActualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCancha(@PathVariable("id") Long id) {
        canchaService.eliminarCancha(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Cancha>> obtenerTodasLasCanchas() {
        List<Cancha> listaCanchas = canchaService.buscarTodasLasCanchas();
        return ResponseEntity.ok(listaCanchas);
    }
}
