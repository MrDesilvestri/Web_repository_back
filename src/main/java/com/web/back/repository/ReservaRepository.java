package com.web.back.repository;



import com.web.back.Entities.Reserva;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaRepository extends CrudRepository<Reserva, Long> {

    @Query(value = "SELECT * FROM reserva WHERE id_cancha = ?1", nativeQuery = true)
    Iterable<Reserva> findAllByIdCancha(Long idCancha);

    @Query(value = "SELECT * FROM reserva WHERE id_usuario = ?1", nativeQuery = true)
    Iterable<Reserva> findAllByIdUsuario(Long idUsuario);

    @Query(value = "SELECT * FROM reserva", nativeQuery = true)
    Iterable<Reserva> findAllReservas();
}
