package com.back.demo.CRUD;

import com.back.demo.Model.Cancha;
import org.springframework.data.repository.CrudRepository;

public interface CanchaRepo extends CrudRepository<Cancha, Integer> {
}
