package com.backend.back.Controllers;

import com.backend.back.CRUD.UsuarioRepo;
import com.backend.back.Model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Service
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    UsuarioRepo usuarioRepo;


    @GetMapping( "/listar")
    public  ResponseEntity<List<Usuario>> getUsuarios(){
        List<Usuario> usuarios = usuarioRepo.findAll();
        return ResponseEntity.ok(usuarios);
    }

    @PostMapping("/")
    public ResponseEntity<Usuario> insertar(@RequestBody Usuario usuario) throws Exception{
        Usuario usuarios = new Usuario(usuario);
        usuarioRepo.save(usuario);
        return ResponseEntity.ok(usuarios);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUsuario(@RequestBody Usuario usuario) {

        Usuario updatedUsuario = usuarioRepo.save(usuario);
        return ResponseEntity.ok(updatedUsuario);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuario(@PathVariable("id") int id) throws Exception{
        Usuario usuario = usuarioRepo.findById(id).get();
        return ResponseEntity.ok(usuario);
    }


}
