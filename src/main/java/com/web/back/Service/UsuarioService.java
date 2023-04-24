package com.web.back.Service;

import java.util.List;

import com.web.back.Entities.User;

public interface UsuarioService {

    List<User> listAll();

    User listById(int id);
    User guardarUsuario(User usuario);

    User actualizarUsuario(User usuario);
    
    void eliminarUsuario(int id);
}
