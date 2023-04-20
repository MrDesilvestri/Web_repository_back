package com.backend.back.CRUD;

import com.backend.back.Model.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComentarioRepo extends JpaRepository <Comentario, Integer> {
}
