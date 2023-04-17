package com.web.back.CRUD;
import java.util.List;
import org.springframework.data.repository.Repository;

import com.web.back.Entities.Cancha;

public interface CanchaRepository extends Repository<Cancha, Integer>{
    List<Cancha> findAll();
    Cancha findOne(int id);
    void update(Cancha cancha);
    Cancha save(Cancha cancha);
    void delete(Cancha cancha);
}
