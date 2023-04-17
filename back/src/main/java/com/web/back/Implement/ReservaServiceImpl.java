package com.web.back.Implement;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.back.CRUD.ReservaRepository;
import com.web.back.Entities.Reserva;
import com.web.back.Service.ReservaService;

@Service
public class ReservaServiceImpl implements ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Override
    public Reserva guardarReserva(Reserva reserva) {
        return reservaRepository.save(reserva);
    }

    @Override
    public Optional<Reserva> buscarReservaPorId(Long id) {
        return reservaRepository.findById(id);
    }

    @Override
    public Reserva buscarReservaPorIdC(Long id) {
        return reservaRepository.findByIdC(id);
    }

    @Override
    public List<Reserva> buscarTodasLasReservas() {
        return reservaRepository.findAll();
    }

    @Override
    public void eliminarReserva(Long id) {
        reservaRepository.deleteById(id);
    }

    @Override
    public Reserva actualizarReserva(Reserva reserva) {
        return reservaRepository.save(reserva);
    }

    /*
    @Override
    public Reserva guardarReserva(Reserva reserva) {
        return reservaRepository.save(reserva);
    }

    @Override
    public Optional<Reserva> buscarReservaPorId(long id) {
        return reservaRepository.findById(id);
    }

    @Override
    public Reserva buscarReservaPorIdC(long id) {
        return reservaRepository.findByIdC(id);
    }

    @Override
    public List<Reserva> buscarTodasLasReservas() {
        return reservaRepository.findAll();
    }

    @Override
    public void eliminarReserva(int id) {
        reservaRepository.deleteById(id);
    }

    @Override
    public Reserva actualizarReserva(Reserva reserva) {
        return reservaRepository.save(reserva);
    } */
}
