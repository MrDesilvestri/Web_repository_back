package com.web.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.back.Entities.Comentario;

public interface ComentarioRepository extends JpaRepository<Comentario, Integer> {
}
