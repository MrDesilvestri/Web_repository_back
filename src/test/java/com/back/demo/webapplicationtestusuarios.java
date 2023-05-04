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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    /*public void testUpdateUsuario() {
      User usuario = new User();
      usuario.setNombre("Juan Perez");
      usuario.setEmail("juanpe@hotmail.com");
      usuario.setTelefono(30012345679L);
      usuario.setPassword("panchito123");

      User usuarioGuardado = usuarioRepository.save(usuario);
      long id = usuarioGuardado.getId();
      User usuarioActualizado = new User();
      usuarioActualizado.setNombre("Juan Perez Gomez");
      usuarioActualizado.setEmail("juanpe@gmail.com");
      usuarioActualizado.setTelefono(300123454349L);
      usuarioActualizado.setPassword("1234345");

      when(UsuarioController.updateUsuario(id, usuarioActualizado)).thenReturn(new ResponseEntity<>(HttpStatus.OK));
      ResponseEntity<?> respuesta = usuarioController.updateUsuario(id, usuarioActualizado);
      assertEquals(HttpStatus.OK, respuesta.getStatusCode());
    }*/
}
