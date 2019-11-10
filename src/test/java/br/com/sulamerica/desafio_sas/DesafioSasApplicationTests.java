package br.com.sulamerica.desafio_sas;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.sulamerica.desafio_sas.controller.CargoController;
import br.com.sulamerica.desafio_sas.controller.PerfilController;
import br.com.sulamerica.desafio_sas.controller.UsuarioController;

@SpringBootTest
class DesafioSasApplicationTests {

	@Autowired
	private PerfilController perfilController;

	@Autowired
	private CargoController cargoController;

	@Autowired
	private UsuarioController usuarioController;

	@Test
	void contextLoads() {
		assertThat(perfilController).isNotNull();
		assertThat(cargoController).isNotNull();
		assertThat(usuarioController).isNotNull();
	}

}
