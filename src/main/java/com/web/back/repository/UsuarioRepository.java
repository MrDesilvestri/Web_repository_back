package com.web.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.back.Entities.User;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<User, Long> {

    Boolean existsByEmail(String email);

    Optional<User> findByEmail(String username);

    boolean existsByIdentification(String identification);
}
