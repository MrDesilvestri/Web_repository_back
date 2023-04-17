package com.web.back.Service;
import java.util.List;

import com.web.back.Entities.Reserva;
public interface ReservasService{
    //para Reserva

    public void registrarReserva(Reserva reserva);
    public void actualizarReserva(Reserva reserva);
    public void eliminarReserva(int id);
    public Reserva obtenerReserva(int id);
    public List<Reserva> listarReservas();
}
