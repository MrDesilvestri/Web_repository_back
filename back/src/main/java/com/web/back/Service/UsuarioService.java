package com.web.back.Service;

import java.util.List;
import java.util.Optional;
import com.web.back.Entities.Usuario;

public interface UsuarioService {

    List<Usuario> listAll();

    Usuario listById(int id);
    Usuario guardarUsuario(Usuario usuario);

    Usuario actualizarUsuario(Usuario usuario);
    
    void eliminarUsuario(int id);
}
