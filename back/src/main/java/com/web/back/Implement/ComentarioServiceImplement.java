package com.web.back.Implement;

import java.util.List;

import org.springframework.stereotype.Service;

import com.web.back.CRUD.ComentarioRepository;
import com.web.back.Entities.Comentario;
import com.web.back.Entities.Usuario;
import com.web.back.Service.ComentarioService;

@Service
public class ComentarioServiceImplement implements ComentarioService{

    private ComentarioRepository comentarioRepository;
    @Override
    public void agregarComentario(Comentario comentario) {
        comentarioRepository.save(comentario);
    }

    @Override
    public List<Comentario> obtenerComentariosPorCancha(int id) {
        return comentarioRepository.findByCancha(id);
    }

    @Override
    public List<Comentario> obtenerComentariosPorUsuario(Usuario usuario) {
        return comentarioRepository.findByUsuario(usuario);
    }

    @Override
    public void eliminarComentario(Comentario comentario) {
        comentarioRepository.delete(comentario);
    }
    
}
