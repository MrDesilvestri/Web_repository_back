package com.back.demo.CRUD;

import com.back.demo.Model.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepo extends CrudRepository<Usuario, Integer> {
}
