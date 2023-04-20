package com.back.demo;
import com.back.demo.Controllers.CanchaController;
import com.back.demo.Controllers.UsuarioController;
import com.back.demo.Model.Cancha;
import com.back.demo.Model.Usuario;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WebApplicationTests {
	@Test
	void Test1() throws Exception {
		Usuario testuser = new Usuario(777, "Pepito", "pepito@gmail.com", "1234", "123456789");
		UsuarioController.insertar(testuser);
	}
	@Test
	void Test2() throws Exception {
		Cancha testcancha = new Cancha(123, "CampNou", "Bueno, Bonito y Barato", "Barcelona", 1500);
		CanchaController.insertar(testcancha);
	}

}
