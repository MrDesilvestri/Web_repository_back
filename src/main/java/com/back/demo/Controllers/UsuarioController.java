package com.back.demo.Controllers;

import com.back.demo.CRUD.UsuarioRepo;
import com.back.demo.Model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Service
@RequestMapping("/usuario")
public class UsuarioController {

    static UsuarioRepo usuarioRepo;

    @Autowired
    public UsuarioController(UsuarioRepo usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
    }


    @GetMapping("/listar")
    public ResponseEntity<?> getUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        usuarioRepo.findAll().forEach(usuarios::add);
        if (usuarios.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No hay usuarios");
        } else
            return ResponseEntity.status(HttpStatus.OK).body(usuarios);
    }

    @PostMapping("/crear_usuario")
    public static ResponseEntity<?> insertar(@RequestBody Usuario usuario) throws Exception {
        if (usuarioRepo.existsById(usuario.getId())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("El usuario ya existe");
        } else {
            usuarioRepo.save(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUsuario(@RequestBody Usuario usuario) {
        if (!usuarioRepo.existsById(usuario.getId())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(usuario);
        }else{
            usuarioRepo.save(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
        }
    }

    @GetMapping("/{id}")
    public static ResponseEntity<Usuario> getUsuario(@PathVariable("id") int id) throws Exception {
        Usuario usuario = usuarioRepo.findById(id).get();
        return ResponseEntity.ok(usuario);
    }


}
