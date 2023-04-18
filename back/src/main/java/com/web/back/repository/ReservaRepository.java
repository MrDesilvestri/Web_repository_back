package com.web.back.repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.back.Entities.Reserva;

public interface ReservaRepository extends JpaRepository<Reserva, Integer> {
}
