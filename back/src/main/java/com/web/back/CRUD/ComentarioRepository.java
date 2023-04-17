package com.web.back.CRUD;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.back.Entities.Comentario;

public interface ComentarioRepository extends JpaRepository<Comentario, Integer> {
    List<Comentario> findAll();
    Optional<Comentario> findById(Long id);
    void deleteById(long id);
    Comentario save(Comentario comentario);
    void delete(Comentario comentario);
}
