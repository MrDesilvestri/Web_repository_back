package com.web.back.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.back.Entities.Usuario;
import com.web.back.Service.UsuarioService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping({"/usuario"})
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;

    @GetMapping
    public Usuario obtenerUsuarioPorId(int id){
        return usuarioService.obtenerUsuarioPorId(id);
    }
    @GetMapping
    public List<Usuario> obtenerTodosLosUsuarios(){
        return usuarioService.obtenerTodosLosUsuarios();
    }
    @GetMapping
    public Usuario obtenerUsuarioPorEmail(String email){
        return usuarioService.obtenerUsuarioPorEmail(email);
    }
}
