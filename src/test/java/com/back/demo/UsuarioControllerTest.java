package com.back.demo;

import com.web.back.Controllers.UsuarioController;
import com.web.back.Entities.User;
import com.web.back.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


@SpringBootTest
@ContextConfiguration(classes = {com.web.back.BackApplication.class})
public class UsuarioControllerTest {

  @Autowired
  private UsuarioRepository usuarioRepository;

  @Test
  public void testFindUserById() {
    try {
      User user = usuarioRepository.findById(13L).get();
      assertEquals(user.getId(), 13L);
    } catch (Exception e) {
      assert (false);
    }
  }

  @Test
  public void testAddUser() {
    try {
      User user = new User();
      user.setName("Juan Perez");
      user.setEmail("tomEm@hotmail.com");
      user.setTelefono("3222263066");
      user.setPassword("panchito123");
      usuarioRepository.save(user);
      User userGuardado = usuarioRepository.findById(15L).get();
      assertEquals(userGuardado.getId(), 15L);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      assert (false);
    }
  }

  @Test
  public void testUpdateUser() {
    try {
      User user = usuarioRepository.findById(13L).get();
      user.setName("Juan Perez");
      user.setEmail("juanP@gmail.com");
      usuarioRepository.save(user);
      User userGuardado = usuarioRepository.findById(13L).get();
      assertEquals(userGuardado.getEmail(), "juanP@gmail.com");
    } catch (Exception e) {
      System.out.println(e.getMessage());
      assert (false);
    }
  }
}
