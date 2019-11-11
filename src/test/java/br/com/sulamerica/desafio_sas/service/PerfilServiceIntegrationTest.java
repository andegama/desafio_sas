package br.com.sulamerica.desafio_sas.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.sulamerica.desafio_sas.entity.Perfil;
import br.com.sulamerica.desafio_sas.exceptions.NegocioException;
import br.com.sulamerica.desafio_sas.repository.PerfilRepository;

/**
 * @author ander
 */
@RunWith(SpringRunner.class)
public class PerfilServiceIntegrationTest {

	@TestConfiguration
    static class PerfilServiceTestContextConfiguration {

		@Bean
        public PerfilService PerfilService() {
            return new PerfilService();
        }
    }

	@Autowired
	private PerfilService service;

	@MockBean
	private PerfilRepository repo;

	/**
	 * @author ander
	 */
	@Before
	public void setUp() {
	    Optional<Perfil> op = Optional.of(new Perfil(1l, "Teste"));

	    when(repo.findById(op.get().getId()))
	      .thenReturn(op);

	    List<Perfil> Perfils = new ArrayList<>();
	    Perfils.add(new Perfil(1l, "Teste"));
	    Perfils.add(new Perfil(2l, "Teste2"));
	    Perfils.add(new Perfil(3l, "Teste3"));

	    when(repo.findAll())
	    	.thenReturn(Perfils);

	    when(repo.save(new Perfil("Teste")))
	    	.thenReturn(new Perfil(1l, "Teste"));

	    when(repo.save(new Perfil(1l, "Teste2")))
    		.thenReturn(new Perfil(1l, "Teste2"));

	    doNothing().when(repo).delete(isA(Perfil.class));
	}

	/**
	 * @author ander
	 * @throws NegocioException
	 */
	@Test
	public void testFindById() throws NegocioException {
	    Long id = 1l;
	    Optional<Perfil> found = service.findById(id);

	    assertThat(found.get().getId()).isEqualTo(id);
	 }

	/**
	 * @author ander
	 */
	@Test
	public void testListAll() {
		List<Perfil> perfils = new ArrayList<>();
	    perfils.add(new Perfil(1l, "Teste"));
	    perfils.add(new Perfil(2l, "Teste2"));
	    perfils.add(new Perfil(3l, "Teste3"));

	    List<Perfil> lista = service.findAll();

	    assertThat(lista).containsAll(perfils);
	}

	/**
	 * @author ander
	 */
	@Test
	public void testSave() {
		Perfil perfil = repo.save(new Perfil("Teste"));
		assertThat(perfil.getId()).isEqualTo(1l);
	}

	/**
	 * @author ander
	 */
	@Test
	public void testUpdate() {
		final String nome = "Teste2";
		Perfil perfil = repo.save(repo.save(new Perfil(1l, nome)));

		assertThat(perfil.getNome()).isEqualTo(nome);
	}
}