package com.web.back.Service;
import java.util.List;
import java.util.Optional;
import com.web.back.Entities.Reserva;

public interface ReservaService {
    List<Reserva> listAll();

    public Reserva listById(int id);
    
    public Reserva guardarReserva(Reserva reserva);

    public Reserva actualizarReserva(Reserva reserva);

    public void eliminarReserva(int id);
    

    
}
