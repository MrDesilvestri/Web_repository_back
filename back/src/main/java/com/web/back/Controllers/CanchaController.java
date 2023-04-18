package com.web.back.Controllers;

import com.web.back.Entities.Cancha;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.web.back.Service.CanchaService;

import java.util.List;

@RestController
@RequestMapping("/canchas")
public class CanchaController {
    private final CanchaService canchaService;

    public CanchaController(CanchaService canchaService) {
        this.canchaService = canchaService;
    }

    @GetMapping("/listar")
    private ResponseEntity<?> listAll(){
        List<Cancha> cancha = canchaService.listAll();
        return ResponseEntity.ok(cancha);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerCancha(@PathVariable int id) {
        Cancha cancha = canchaService.listById(id);
        return ResponseEntity.ok(cancha);
    }
    @PostMapping("/crear")
    public ResponseEntity<Cancha> crearCancha(@RequestBody Cancha cancha) {
        Cancha nuevaCancha = canchaService.guardarCancha(cancha);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaCancha);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Cancha> actualizarCancha(@PathVariable int id, @RequestBody Cancha cancha) {
        Cancha canchaExistente = canchaService.listById(id);
        canchaExistente.setId(id);
        canchaExistente.setNombre(cancha.getNombre());
        canchaExistente.setUbicacion(cancha.getUbicacion());
        canchaExistente.setDescripcion(cancha.getDescripcion());
        canchaExistente.setPrecioHora(cancha.getPrecioHora());
        Cancha canchaActualizada = canchaService.actualizarCancha( canchaExistente);
        return ResponseEntity.ok(canchaActualizada);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCancha(@PathVariable("id") int id) {
        canchaService.eliminarCancha(id);
        return ResponseEntity.noContent().build();
    }
}
