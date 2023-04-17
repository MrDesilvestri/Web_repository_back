package com.web.back.Implement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.back.CRUD.UsuarioRepository;
import com.web.back.Entities.Usuario;
import com.web.back.Service.UsuarioService;
@Service
public class UsuarioServiceImplement implements UsuarioService{
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Override
    public void insertarUsuario(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    @Override
    public void eliminarUsuario(Usuario usuario) {
        usuarioRepository.delete(usuario);
    }

    @Override
    public Usuario obtenerUsuarioPorId(int id) {
        return usuarioRepository.findOne(id);
    }

    @Override
    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario obtenerUsuarioPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    @Override
    public void actualizarUsuario(Usuario usuario) {
        usuarioRepository.update(usuario);
    }
    
}
