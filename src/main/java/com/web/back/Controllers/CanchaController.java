package com.web.back.Controllers;

import com.web.back.Entities.Cancha;
import com.web.back.Exceptions.CanchaFoundException;
import com.web.back.Exceptions.CanchaNotFoundException;
import com.web.back.repository.CanchaRepository;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
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

    @Autowired
    private CanchaRepository canchaRepository;

    @Operation(summary = "Get the field catalog")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returns a list of fields ordered and filtered based on query parameters", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Cancha.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content)})
    @GetMapping("/list")
    private ResponseEntity<?> listAll(
            @RequestParam(value = "id", required = false, defaultValue = "id") long id,
            @RequestParam(value = "nombre", required = false, defaultValue = "nombre") String nombre,
            @RequestParam(value = "descripcion", required = false, defaultValue = "descripcion") String descripcion,
            @RequestParam(value = "ubicacion", required = false, defaultValue = "ubicacion") String ubicacion,
            @RequestParam(value = "precioHora", required = false, defaultValue = "precioHora") String precioHora){
        List<Cancha> canchas = new ArrayList<>();
        canchaRepository.findAll().forEach(canchas::add);
        return ResponseEntity.status(HttpStatus.OK).body(canchas);
    }

    @Operation(summary = "Get the field by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returns the field with the id given",content = { @Content(mediaType = "application/json",schema = @Schema(implementation = Cancha.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = CanchaNotFoundException.class)) }),
            @ApiResponse(responseCode = "404", description = "The field was not found", content = @Content) })
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerCancha(@Parameter(description = "id of field to be searched") @PathVariable long id) {
        Cancha cancha = canchaRepository.findById(id).orElseThrow(() -> new CanchaFoundException(id));
        return ResponseEntity.status(HttpStatus.OK).body(cancha);
    }

    @Operation(summary = "Add a new field in to the catalog")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "field saved", content = { @Content(mediaType = "application/json",schema = @Schema(implementation = Cancha.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = CanchaNotFoundException.class))}),
            @ApiResponse(responseCode = "404", description = "field with given id already exist", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CanchaFoundException.class))) })
    @PostMapping("/add")
    public ResponseEntity<?> crearCancha(@RequestBody @Valid Cancha cancha) {
        Cancha canchaExistente = canchaRepository.findById(cancha.getId()).orElse(null);
        if (canchaExistente != null) {
            throw new CanchaFoundException(cancha.getId());
        }else{
            canchaRepository.save(cancha);
            return ResponseEntity.status(HttpStatus.CREATED).body(cancha);
        }
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
