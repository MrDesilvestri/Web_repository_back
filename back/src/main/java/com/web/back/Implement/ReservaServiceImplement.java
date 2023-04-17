package com.web.back.Implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.back.Entities.Reserva;
import com.web.back.CRUD.ReservaRepository;
import com.web.back.Service.ReservasService;
@Service
public class ReservaServiceImplement implements ReservasService{
    @Autowired
    private ReservaRepository reservaRepository;
    
    @Override
    public void registrarReserva(Reserva reserva) {
        reservaRepository.save(reserva);
    }

    @Override
    public void eliminarReserva(int id) {
        reservaRepository.delete(id);
    }

    @Override
    public Reserva obtenerReserva(int id) {
        return reservaRepository.findOne(id);
    }

    @Override
    public List<Reserva> listarReservas() {
        return reservaRepository.findAll();
    }

    @Override
    public void actualizarReserva(Reserva reserva) {
        reservaRepository.update(reserva);
    }
    
}
