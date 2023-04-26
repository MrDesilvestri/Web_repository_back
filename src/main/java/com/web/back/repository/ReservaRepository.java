package com.web.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.back.Entities.Reserva;
public interface ReservaRepository extends JpaRepository<Reserva, Integer> {
}
