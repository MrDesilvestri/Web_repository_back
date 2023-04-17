package com.web.back.Service;

import java.util.List;

import com.web.back.Entities.Cancha;

public interface CanchaService{
     //para Cancha

     public void insertarCancha(Cancha cancha);
     public void actualizarCancha(Cancha cancha);
     public void eliminarCancha(Cancha cancha);
     public Cancha obtenerCanchaPorId(int id);
     public List<Cancha> obtenerTodasLasCanchas();
}