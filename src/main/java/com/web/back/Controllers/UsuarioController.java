package com.web.back.Controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.back.Entities.User;
import com.web.back.Exceptions.UserExceptions.UsuarioFoundException;
import com.web.back.Exceptions.UserExceptions.UsuarioNotFoundException;
import com.web.back.Exceptions.UserExceptions.UsuarioReserveException;
import com.web.back.repository.UsuarioRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UsuarioController {
    
    @Autowired
    private UsuarioRepository usuarioRepository;


    // Endpoint para obtener todos los usuarios
    @CrossOrigin
    @Operation(summary = "Gets the list of users")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Returns a list of users filtered by a parameter",
         content = { @Content(mediaType = "application/json",schema = @Schema(implementation = User.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad request",content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioNotFoundException.class))) })
    @GetMapping("/all")
    public ResponseEntity<?> getAllUsuarios(@RequestParam(value = "filter", required = false, defaultValue = "") String filter) {
        List<User> usuarios = new ArrayList<>();
        usuarioRepository.findAll().forEach(usuarios::add);
        return ResponseEntity.status(HttpStatus.OK).body(usuarios);
    }
    
    // Endpoint para obtener un usuario por ID
    @Operation(summary = "Get an user by its id")
    @CrossOrigin
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "User found", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = User.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioNotFoundException.class))),
        @ApiResponse(responseCode = "404", description = "User not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioNotFoundException.class))) })
    @GetMapping("/{id}")
    public ResponseEntity<?> getUsuarioById(@PathVariable Long id) {
        User user = usuarioRepository.findById(id).orElseThrow(() -> new UsuarioNotFoundException(id));
        return ResponseEntity.ok().body(user);
    }
    
    // Endpoint para crear un usuario
    @CrossOrigin
    @Operation(summary = "Add a new User received as a parameter")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "User created", content = { @Content(mediaType = "application/json",schema = @Schema(implementation = User.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioNotFoundException.class))),
        @ApiResponse(responseCode = "404", description = "User with given id already exist", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioFoundException.class))),
        @ApiResponse(responseCode = "406", description = "User nickname unavaliable", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioNotFoundException.class))) })
    @PostMapping("/add")
    public ResponseEntity<?> createUsuario(@RequestBody @Valid User usuario) {
        if(usuarioRepository.existsById(usuario.getId())) {
            throw new UsuarioFoundException(usuario.getId());
        }else{
            User newUser = usuarioRepository.save(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
        }
    }

     // Endpoint para actualizar un usuario por ID
    @CrossOrigin
    @Operation(summary = "Update an user received as a parameter")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "User updated", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = User.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioNotFoundException.class))),
        @ApiResponse(responseCode = "404", description = "User not found",content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioNotFoundException.class))) })
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUsuario(@PathVariable Long id, @RequestBody User usuario) {
        User user = usuarioRepository.findById(id).orElseThrow(() -> new UsuarioNotFoundException(id));
        if(user != null){
            throw new UsuarioNotFoundException(id);
        }else{
            usuarioRepository.save(usuario);
            return ResponseEntity.status(HttpStatus.OK).body(usuario);
        }
    }
    
    // Endpoint para eliminar un usuario por ID
    @CrossOrigin
    @Operation(summary = "Delete a user by its id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "User deleted", content = @Content),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioNotFoundException.class))),
        @ApiResponse(responseCode = "404", description = "user not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioNotFoundException.class))),
        @ApiResponse(responseCode = "406", description = "user has a reserve asociated", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioReserveException.class))) })
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<?> deleteUsuario(@PathVariable Long id) {
        User user = usuarioRepository.findById(id).orElseThrow(() -> new UsuarioNotFoundException(id));
        if(user != null){
            throw new UsuarioNotFoundException(id);
        }else{
            usuarioRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("User with id " + id +" deleted successfully");
        }
    }
}
