package com.web.back.Service;

import java.util.List;
import java.util.Optional;

import com.web.back.Entities.Cancha;

public interface CanchaService {
    
    public Cancha guardarCancha(Cancha cancha);
    
    public Optional<Cancha> buscarCanchaPorId(Long id);

    public Cancha buscarCanchaPorIdC(Long id);
    
    public List<Cancha> buscarTodasLasCanchas();
    
    public void eliminarCancha(Long id);
    
    public Cancha actualizarCancha(long canchaID, Cancha cancha);
    
}
