package com.web.back.Controllers;

import com.web.back.Entities.Cancha;
import com.web.back.Entities.User;
import com.web.back.repository.CanchaRepository;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/field")
public class CanchaController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private CanchaRepository canchaRepository;

    @Operation(summary = "Get the field catalog")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returns a list of fields ordered and filtered based on query parameters", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = User.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content)})
    @GetMapping("/list")
    private ResponseEntity<?> listAll(
            @RequestParam(value = "id", required = false, defaultValue = "id") String id,
            @RequestParam(value = "nombre", required = false, defaultValue = "nombre") String nombre,
            @RequestParam(value = "descripcion", required = false, defaultValue = "descripcion") String descripcion,
            @RequestParam(value = "ubicacion", required = false, defaultValue = "ubicacion") String ubicacion,
            @RequestParam(value = "precioHora", required = false, defaultValue = "precioHora") String precioHora){
        List<Cancha> canchas = new ArrayList<>();
        canchaRepository.findAll().forEach(canchas::add);
        return ResponseEntity.status(HttpStatus.OK).body(canchas);
    }

    @Operation(summary = "Get the field by its id")
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
    /*@PutMapping("/{id}")
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
    }*/
}
