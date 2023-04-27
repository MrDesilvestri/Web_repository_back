package com.web.back.Controllers;

import com.web.back.Entities.Cancha;
import com.web.back.Exceptions.CanchaExceptions.CanchaFoundException;
import com.web.back.Exceptions.CanchaExceptions.CanchaNotFoundException;
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
            @ApiResponse(responseCode = "200", description = "Returns a list of fields ordered and filtered based on query parameters",
             content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Cancha.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content)})
    @CrossOrigin
    @GetMapping("/list")
    public ResponseEntity<?> listAll(
            @RequestParam(value = "id", required = false, defaultValue = "id") String id,
            @RequestParam(value = "nombre", required = false, defaultValue = "nombre") String nombre,
            @RequestParam(value = "descripcion", required = false, defaultValue = "descripcion") String descripcion,
            @RequestParam(value = "ubicacion", required = false, defaultValue = "ubicacion") String ubicacion,
            @RequestParam(value = "precioHora", required = false, defaultValue = "precioHora") String precioHora){
        List<Cancha> canchas = new ArrayList<>();
        canchaRepository.findAll().forEach(canchas::add);
        return ResponseEntity.status(HttpStatus.OK).body(canchas);
    }

    @CrossOrigin
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

    @CrossOrigin
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

    @CrossOrigin
    @Operation(summary = "Update a field in the catalog")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "field updated", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Cancha.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CanchaNotFoundException.class))),
        @ApiResponse(responseCode = "404", description = "field with given id does not exist", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CanchaNotFoundException.class))) })
    @PutMapping("/update/={id}")
    public ResponseEntity<?> actualizarCancha(@RequestBody @Valid Cancha cancha , @PathVariable("id") long id) {
        Cancha canchaExistente = canchaRepository.findById(id).orElseThrow(() -> new CanchaNotFoundException(id));
        
        canchaExistente.setNombre(cancha.getNombre());
        canchaExistente.setUbicacion(cancha.getUbicacion());
        canchaExistente.setDescripcion(cancha.getDescripcion());
        canchaExistente.setPrecioHora(cancha.getPrecioHora());
        canchaRepository.save(canchaExistente);
        return ResponseEntity.status(HttpStatus.OK).body(canchaExistente);
    }

    @CrossOrigin
    @Operation(summary = "Delete a field in the catalog by its id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "field deleted", content = @Content),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CanchaNotFoundException.class))),
        @ApiResponse(responseCode = "404", description = "Book is in use or does not exist", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CanchaNotFoundException.class))) })
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<?> eliminarCancha(@PathVariable("id") long id) {
        Cancha cancha = canchaRepository.findById(id).orElseThrow(() -> new CanchaNotFoundException(id));
        canchaRepository.delete(cancha);
        return ResponseEntity.status(HttpStatus.OK).body("Cancha con el id " + id + " fue eliminada");
    }
}
