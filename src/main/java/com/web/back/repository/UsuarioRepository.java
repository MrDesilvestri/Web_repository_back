package com.web.back.repository;

import org.springframework.data.repository.CrudRepository;

import com.web.back.Entities.User;

public interface UsuarioRepository extends CrudRepository<User, Long> {
}