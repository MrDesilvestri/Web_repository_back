package com.web.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.back.Entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
