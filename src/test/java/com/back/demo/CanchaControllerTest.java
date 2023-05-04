package com.back.demo;

import com.web.back.Controllers.CanchaController;
import com.web.back.Entities.Cancha;
import com.web.back.repository.CanchaRepository;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Optional;
import static org.mockito.Mockito.when;

public class CanchaControllerTest {

  private final static Long ID = 1L;

  @MockBean
  private CanchaRepository canchaRepository = Mockito.mock(CanchaRepository.class);
  @Before
  @Test
  public void init(){
    Cancha cancha = new Cancha();
    cancha.setId(1L);
    cancha.setNombre("la chirinchi");
    cancha.setDescripcion("abuelita");
    cancha.setUbicacion("mi casa");
    cancha.setPrecioHora(BigDecimal.valueOf(40000));
    when(canchaRepository.findById(1L)).thenReturn(Optional.of(cancha));
  }
}
