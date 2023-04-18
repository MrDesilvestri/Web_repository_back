package com.web.back.Service.impl;

import com.web.back.Entities.Cancha;
import com.web.back.Service.CanchaService;
import com.web.back.repository.CanchaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CanchaServiceImpl implements CanchaService {
    private final CanchaRepository canchaRepository;

    public CanchaServiceImpl(CanchaRepository canchaRepository) {
        this.canchaRepository = canchaRepository;
    }

    @Override
    public List<Cancha> listAll() {
        return canchaRepository.findAll();
    }
    @Override
    public Cancha listById(int id) {
        return canchaRepository.findById(id).get();
    }

    @Override
    public Cancha guardarCancha(Cancha cancha) {
        return canchaRepository.save(cancha);
    }

    @Override
    public Cancha actualizarCancha(Cancha cancha) {
        return canchaRepository.save(cancha);
    }

    @Override
    public void eliminarCancha(int id) {canchaRepository.deleteById(id);}
}
