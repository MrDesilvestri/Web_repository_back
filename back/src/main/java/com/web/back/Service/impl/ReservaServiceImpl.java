package com.web.back.Service.impl;

import com.web.back.Entities.Reserva;
import com.web.back.Service.ReservaService;
import com.web.back.repository.ReservaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaServiceImpl implements ReservaService {

    private final ReservaRepository reservaRepository;

    public ReservaServiceImpl(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    @Override
    public List<Reserva> listAll() {
        return reservaRepository.findAll();
    }

    @Override
    public Reserva listById(int id) {
        return reservaRepository.findById(id).get();
    }

    @Override
    public Reserva guardarReserva(Reserva reserva) {
        return reservaRepository.save(reserva);
    }

    @Override
    public Reserva actualizarReserva(Reserva reserva) {
        return reservaRepository.save(reserva);
    }

    @Override
    public void eliminarReserva(int id) {
        reservaRepository.deleteById(id);
    }
}
