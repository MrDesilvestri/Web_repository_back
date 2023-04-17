package com.web.back.Implement;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.back.CRUD.ComentarioRepository;
import com.web.back.Entities.Comentario;
import com.web.back.Service.ComentarioService;

@Service
public class ComentarioServiceImpl implements ComentarioService {

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Override
    public Comentario guardarComentario(Comentario comentario) {
        return comentarioRepository.save(comentario);
    }

    @Override
    public Optional<Comentario> buscarComentarioPorId(int id) {
        return comentarioRepository.findById(id);
    }

    @Override
    public List<Comentario> buscarTodosLosComentarios() {
        return comentarioRepository.findAll();
    }

    @Override
    public void eliminarComentario(long id) {
        comentarioRepository.deleteById(id);
    }

    @Override
    public Comentario actualizarComentario(long id, Comentario comentario) {
        return comentarioRepository.save(comentario);
    }
}
