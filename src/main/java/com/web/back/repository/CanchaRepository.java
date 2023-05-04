package com.web.back.repository;

import com.web.back.Entities.Cancha;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface CanchaRepository extends CrudRepository<Cancha, Long> {

}
