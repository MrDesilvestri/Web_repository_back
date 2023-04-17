package com.web.back.Service;

import java.util.List;
import java.util.Optional;
import com.web.back.Entities.Usuario;

public interface UsuarioService {
    
    public Usuario guardarUsuario(Usuario usuario);
    
    public Optional<Usuario> buscarUsuarioPorId(Long usuarioId);
    
    public List<Usuario> buscarTodosLosUsuarios();
    
    public void eliminarUsuario(long id);
    
    public Usuario actualizarUsuario(long id, Usuario usuario);
    
}
