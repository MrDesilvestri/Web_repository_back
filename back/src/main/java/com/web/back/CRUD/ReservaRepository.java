package com.web.back.CRUD;
import java.util.List;
import org.springframework.data.repository.Repository;

import com.web.back.Entities.Reserva;
public interface ReservaRepository extends Repository<Reserva, Integer>{
    List <Reserva> findAll();
    Reserva findOne(int id);
    Reserva save(Reserva reserva);
    void update(Reserva reserva);
    void delete(int id);
}
