package com.web.back.Service.impl;

import com.web.back.Entities.Comentario;
import com.web.back.Service.ComentarioService;
import com.web.back.repository.ComentarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ComentarioServiceImpl implements ComentarioService {
    private final ComentarioRepository comentarioRepository;

    public ComentarioServiceImpl(ComentarioRepository comentarioRepository) {
        this.comentarioRepository = comentarioRepository;
    }

    @Override
    public List<Comentario> listAll() {
        return comentarioRepository.findAll();
    }

    @Override
    public Comentario guardarComentario(Comentario comentario) {
        return comentarioRepository.save(comentario);
    }

    @Override
    public Comentario listById(int id) {
        return comentarioRepository.findById(id).get();
    }
    @Override
    public Comentario actualizarComentario(Comentario comentario) {
        return comentarioRepository.save(comentario);
    }

    @Override
    public void eliminarComentario(int id) {
        comentarioRepository.deleteById(id);
    }
}
