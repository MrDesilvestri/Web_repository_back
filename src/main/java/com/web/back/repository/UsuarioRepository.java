package com.web.back.repository;

import org.springframework.data.repository.CrudRepository;

import com.web.back.Entities.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends CrudRepository<User, Long> {
}
