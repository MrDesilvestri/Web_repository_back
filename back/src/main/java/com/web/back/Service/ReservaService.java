package com.web.back.Service;
import java.util.List;
import java.util.Optional;
import com.web.back.Entities.Reserva;

public interface ReservaService {
    
    public Reserva guardarReserva(Reserva reserva);
    
    public Optional<Reserva> buscarReservaPorId(Long id);

    public Reserva buscarReservaPorIdC(Long id);
    
    public List<Reserva> buscarTodasLasReservas();
    
    public void eliminarReserva(Long id);
    
    public Reserva actualizarReserva(Reserva reserva);
    
}
