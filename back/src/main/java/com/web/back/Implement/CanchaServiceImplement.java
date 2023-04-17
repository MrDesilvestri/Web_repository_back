package com.web.back.Implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.back.CRUD.CanchaRepository;
import com.web.back.Entities.Cancha;
import com.web.back.Service.CanchaService;
@Service
public class CanchaServiceImplement implements CanchaService{

    @Autowired
    private CanchaRepository canchaRepository;
    @Override
    public void insertarCancha(Cancha cancha) {
        canchaRepository.save(cancha);
    }

    @Override
    public void eliminarCancha(Cancha cancha) {
        canchaRepository.delete(cancha);
    }

    @Override
    public Cancha obtenerCanchaPorId(int id) {
        return canchaRepository.findOne(id);
    }

    @Override
    public List<Cancha> obtenerTodasLasCanchas() {
        return canchaRepository.findAll();
    }

    @Override
    public void actualizarCancha(Cancha cancha) {
        canchaRepository.update(cancha);
    }
        
}
