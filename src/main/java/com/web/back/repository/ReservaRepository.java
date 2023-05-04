package com.web.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.back.Entities.Reserva;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Integer> {
}
