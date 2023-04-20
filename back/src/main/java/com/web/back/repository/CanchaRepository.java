package com.web.back.repository;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.back.Entities.Cancha;

public interface CanchaRepository extends JpaRepository<Cancha, Integer> {
}
