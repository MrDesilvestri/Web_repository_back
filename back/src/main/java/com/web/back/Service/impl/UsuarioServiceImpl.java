package com.web.back.Service.impl;

import com.web.back.Entities.User;
import com.web.back.Service.UsuarioService;
import com.web.back.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public List<User> listAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public User listById(int id) {
        return usuarioRepository.findById(id).get();
    }

    @Override
    public User guardarUsuario(User usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public User actualizarUsuario(User usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public void eliminarUsuario(int id) {
        usuarioRepository.deleteById(id);
    }
}
