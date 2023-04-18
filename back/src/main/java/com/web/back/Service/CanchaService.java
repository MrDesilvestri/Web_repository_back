package com.web.back.Service;

import java.util.List;

import com.web.back.Entities.Cancha;

public interface CanchaService {

    List<Cancha> listAll();

    public Cancha guardarCancha(Cancha cancha);

    Cancha listById(int id);

    void eliminarCancha(int id);

    Cancha actualizarCancha(long canchaID, Cancha cancha);

}