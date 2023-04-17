package com.web.back.Service;

import java.util.List;

import com.web.back.Entities.Usuario;

public interface UsuarioService {
    //para Usuario
    public void insertarUsuario(Usuario usuario);
    public void actualizarUsuario(Usuario usuario);
    public void eliminarUsuario(Usuario usuario);
    public Usuario obtenerUsuarioPorId(int id);
    public List<Usuario> obtenerTodosLosUsuarios();
    public Usuario obtenerUsuarioPorEmail(String email);

   

    

    
}

