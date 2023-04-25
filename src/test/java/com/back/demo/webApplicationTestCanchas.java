package com.back.demo;

import com.web.back.Controllers.CanchaController;
import com.web.back.Entities.Cancha;
import com.web.back.repository.CanchaRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class webApplicationTestCanchas {

    @InjectMocks
    private CanchaController canchaController;

    @Mock
    private CanchaRepository canchaRepository;

    @Test
    public void testListAll_withNoParams_returnsAllCanchas() {
        // Arrange
        List<Cancha> canchas = new ArrayList<>();
        canchas.add(new Cancha(1, "Cancha 1", "Descripción de la Cancha 1", "Ubicación 1", 10000.0));
        canchas.add(new Cancha(2, "Cancha 2", "Descripción de la Cancha 2", "Ubicación 2", 200.000));
        canchas.add(new Cancha(3, "Cancha 3", "Descripción de la Cancha 3", "Ubicación 3", 300.000));
        Mockito.when(canchaRepository.findAll()).thenReturn(canchas);

        // Act
        ResponseEntity<?> response = canchaController.listAll(null, null, null, null, null);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(canchas);
    }
}
