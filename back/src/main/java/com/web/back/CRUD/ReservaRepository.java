package com.web.back.CRUD;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.back.Entities.Reserva;

public interface ReservaRepository extends JpaRepository<Reserva, Integer> {
    List<Reserva> findByUsuarioId(int usuarioId);
    List<Reserva> findByCanchaId(int canchaId);
    Optional<Reserva> findById(Long id);
    Reserva findByIdC(Long id);
    List<Reserva> findByFechaInicioBetween(Date fechaInicio, Date fechaFin);
    void deleteById(Long id);
}
