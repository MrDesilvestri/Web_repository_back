package com.web.back.CRUD;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.back.Entities.Cancha;

public interface CanchaRepository extends JpaRepository<Cancha, Integer> {
    List<Cancha> findByNombre(String nombre);
    List<Cancha> findByUbicacion(String ubicacion);
    List<Cancha> findByPrecioHoraLessThan(BigDecimal precio);
    void deleteById(long id);
    Optional<Cancha> findById(long id);
    Cancha findByIdC(long id);
}
