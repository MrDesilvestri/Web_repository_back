package com.web.back.Controllers;

import java.util.List;

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

import com.web.back.Entities.User;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // Endpoint para obtener todos los usuarios
    @GetMapping
    public ResponseEntity<?> getAllUsuarios() {
        List<User> usuarios = usuarioService.listAll();
        return ResponseEntity.status(HttpStatus.OK).body(usuarios);
    }
    
    // Endpoint para obtener un usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getUsuarioById(@PathVariable int id) {
        User usuario = usuarioService.listById(id);
        return ResponseEntity.status(HttpStatus.OK).body(usuario);
    }
    
    // Endpoint para crear un usuario
    @PostMapping("/crear_usuario")
    public ResponseEntity<User> createUsuario(@RequestBody User usuario) {
        User UsuarioCreate = usuarioService.guardarUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(UsuarioCreate);
    }
    
    // Endpoint para actualizar un usuario por ID
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUsuario(@PathVariable int id, @RequestBody User usuario) {
        usuario.setId(id);
        User updatedUsuario = usuarioService.actualizarUsuario(usuario);
        return ResponseEntity.ok(updatedUsuario);
    }
    
    // Endpoint para eliminar un usuario por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable int id) {
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.ok().build();
    }
}
