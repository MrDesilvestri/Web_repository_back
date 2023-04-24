package com.web.back.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.back.Entities.Comentario;

public interface ComentarioRepository extends JpaRepository<Comentario, Integer> {
}
