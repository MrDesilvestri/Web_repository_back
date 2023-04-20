package com.backend.back.CRUD;

import com.backend.back.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepo extends JpaRepository <Usuario, Integer> {
}
