package com.back.demo;
import com.web.back.Controllers.CanchaController;
import com.web.back.Controllers.UsuarioController;
import com.web.back.Entities.User;
import com.web.back.repository.CanchaRepository;
import com.web.back.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@DataJpaTest
@SpringBootTest
@ActiveProfiles("testUsuarios")
@ComponentScan(basePackages = {"com.web.back.repository"})
@Configuration
@EnableAutoConfiguration
public class webapplicationtestusuarios {

  @Autowired
  private UsuarioRepository usuarioRepository;

  @MockBean
  private UsuarioController usuarioController;

   @Test
    public void testFindById() {
     User usuario = new User();
     usuario.setId(1L);
     usuario.setNombre("Juan Perez");
     usuario.setEmail("juanpe@hotmail.com");
     usuario.setTelefono(30012345679L);
     usuario.setPassword("panchito123");

     User usuarioGuardado = usuarioRepository.save(usuario);
     long id = usuarioGuardado.getId();
     User usuarioEncontrado = usuarioRepository.findById(id).orElse(null);
     assertEquals(usuarioGuardado, usuarioEncontrado);
    }


}
