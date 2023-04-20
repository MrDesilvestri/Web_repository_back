package com.back.demo.CRUD;

import com.back.demo.Model.Reserva;
import org.springframework.data.repository.CrudRepository;

public interface ReservaRepo extends CrudRepository<Reserva, Integer> {
}
