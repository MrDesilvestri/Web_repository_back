package com.web.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.back.Entities.User;

public interface UsuarioRepository extends JpaRepository<User, Integer> {
}