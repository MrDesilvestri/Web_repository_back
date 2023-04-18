package com.web.back.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.back.Entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}