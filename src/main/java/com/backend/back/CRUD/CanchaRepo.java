package com.backend.back.CRUD;

import com.backend.back.Model.Cancha;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CanchaRepo extends JpaRepository <Cancha, Integer> {
}
