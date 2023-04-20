package com.backend.back.CRUD;

import com.backend.back.Model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservaRepo extends JpaRepository <Reserva, Integer> {
}
