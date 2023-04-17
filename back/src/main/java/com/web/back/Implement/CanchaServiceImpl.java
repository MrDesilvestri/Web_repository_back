package com.web.back.Implement;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.back.CRUD.CanchaRepository;
import com.web.back.Entities.Cancha;
import com.web.back.Service.CanchaService;

@Service
public class CanchaServiceImpl implements CanchaService {

    @Autowired
    private CanchaRepository canchaRepository;

    @Override
    public Cancha guardarCancha(Cancha cancha) {
        return canchaRepository.save(cancha);
    }

    @Override
    public Optional<Cancha> buscarCanchaPorId(Long id) {
        return canchaRepository.findById(id);
    }

    @Override
    public List<Cancha> buscarTodasLasCanchas() {
        return canchaRepository.findAll();
    }

    @Override
    public void eliminarCancha(Long id) {
        canchaRepository.deleteById(id);
    }

    @Override
    public Cancha actualizarCancha(long canchaID, Cancha cancha) {
        return canchaRepository.save(cancha);
    }

    @Override
    public Cancha buscarCanchaPorIdC(Long id) {
        return canchaRepository.findByIdC(id);
    }
}
