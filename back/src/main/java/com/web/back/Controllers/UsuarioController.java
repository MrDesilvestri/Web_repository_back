package com.web.back.Controllers;

import java.util.List;
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

import com.web.back.Entities.Usuario;
import com.web.back.Service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    
    @Autowired
    private UsuarioService usuarioService;
    
    // Endpoint para obtener todos los usuarios
    @GetMapping("/")
    public ResponseEntity<List<Usuario>> getAllUsuarios() {
        List<Usuario> usuarios = usuarioService.buscarTodosLosUsuarios();
        return ResponseEntity.ok(usuarios);
    }
    
    // Endpoint para obtener un usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Usuario>> getUsuarioById(@PathVariable(value = "id") Long usuarioId) {
        Optional<Usuario> usuario = usuarioService.buscarUsuarioPorId(usuarioId);
        return ResponseEntity.ok(usuario);
    }
    
    // Endpoint para crear un usuario
    @PostMapping("/")
    public ResponseEntity<Usuario> createUsuario(@RequestBody Usuario usuario) {
        Usuario newUsuario = usuarioService.guardarUsuario(usuario);
        return ResponseEntity.ok(newUsuario);
    }
    
    // Endpoint para actualizar un usuario por ID
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable(value = "id") Long usuarioId, 
                                                  @RequestBody Usuario usuario) {
        Usuario updatedUsuario = usuarioService.actualizarUsuario(usuarioId, usuario);
        return ResponseEntity.ok(updatedUsuario);
    }
    
    // Endpoint para eliminar un usuario por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable(value = "id") Long usuarioId) {
        usuarioService.eliminarUsuario(usuarioId);
        return ResponseEntity.ok().build();
    }
    
}
