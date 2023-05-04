package com.back.demo;



import com.web.back.Entities.Cancha;
import com.web.back.repository.CanchaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.math.BigDecimal;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ContextConfiguration(classes = {com.web.back.BackApplication.class})
public class CanchaControllerTest {

  @Autowired
  private CanchaRepository canchaRepository;

  @Test
  public void testFindCanchas() {
    try {
      Iterator<Cancha> cancha = canchaRepository.findAll().iterator();
      int cantidad = 0;
      while (cancha.hasNext()) {
        cancha.next();
        cantidad++;
      }
      assert (cantidad > 0);
    } catch (Exception e) {
      assert (false);
    }
  }
  @Test
  public void testFindCanchaById() {
    try {
      Cancha cancha = canchaRepository.findById(2L).get();
      assert (cancha.getId() == 2L);
    } catch (Exception e) {
      assert (false);
    }
  }
  @Test
  public void testFindCanchaByIdNotFound() {
    try {
      Cancha cancha = canchaRepository.findById(100L).get();
       assert (cancha.getId() == 100L); //lo que hace es que compara si el id es 100 y si no lo es, falla
    } catch (Exception e) {
      assert (true);
      System.out.println("No se encontro la cancha");
    }
  }
  @Test
  public void testAgregarUnaCancha(){
    try{
      Iterator<Cancha> canchaIterator = canchaRepository.findAll().iterator();
      int cantidad = 0;
      while(canchaIterator.hasNext()){
        canchaIterator.next();
        cantidad++;
      }
      Cancha cancha = new Cancha();
      cancha.setNombre("Cancha de prueba2");
      cancha.setDescripcion("Cancha de prueba2");
      cancha.setPrecioHora(BigDecimal.valueOf(1000));
      cancha.setUbicacion("Calle falsa 1233");
      canchaRepository.save(cancha);
      int cantidadDespues = 0;
      canchaIterator = canchaRepository.findAll().iterator();
      while(canchaIterator.hasNext()){
        canchaIterator.next();
        cantidadDespues++;
      }
      assertEquals (cantidadDespues ,cantidad + 1);
    }catch (Exception e){
      assert (false);
    }
  }
}
