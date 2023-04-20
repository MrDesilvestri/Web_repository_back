package com.back.demo.CRUD;

import com.back.demo.Model.Comentario;
import org.springframework.data.repository.CrudRepository;

public interface ComentarioRepo extends CrudRepository<Comentario, Integer> {
}
