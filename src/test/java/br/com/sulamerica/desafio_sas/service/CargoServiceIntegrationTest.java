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

import br.com.sulamerica.desafio_sas.entity.Cargo;
import br.com.sulamerica.desafio_sas.exceptions.NegocioException;
import br.com.sulamerica.desafio_sas.repository.CargoRepository;
import br.com.sulamerica.desafio_sas.repository.CargoRepositoryJdbc;

/**
 * @author ander
 */
@RunWith(SpringRunner.class)
public class CargoServiceIntegrationTest {

	@TestConfiguration
    static class CargoServiceTestContextConfiguration {

		@Bean
        public CargoService cargoService() {
            return new CargoService();
        }
    }

	@Autowired
	private CargoService service;

	@MockBean
	private CargoRepository repo;

	@MockBean
	private CargoRepositoryJdbc repoJdbc;

	/**
	 * @author ander
	 */
	@Before
	public void setUp() {
	    Optional<Cargo> op = Optional.of(new Cargo(1l, "Teste"));

	    when(repo.findById(op.get().getId()))
	      .thenReturn(op);

	    List<Cargo> cargos = new ArrayList<>();
	    cargos.add(new Cargo(1l, "Teste"));
	    cargos.add(new Cargo(2l, "Teste2"));
	    cargos.add(new Cargo(3l, "Teste3"));

	    when(repo.findAll())
	    	.thenReturn(cargos);

	    when(repo.save(new Cargo("Teste")))
	    	.thenReturn(new Cargo(1l, "Teste"));

	    when(repo.save(new Cargo(1l, "Teste2")))
    		.thenReturn(new Cargo(1l, "Teste2"));

	    doNothing().when(repo).delete(isA(Cargo.class));
	}

	/**
	 * @author ander
	 * @throws NegocioException
	 */
	@Test
	public void testFindById() throws NegocioException {
	    Long id = 1l;
	    Optional<Cargo> found = service.findById(id);

	    assertThat(found.get().getId()).isEqualTo(id);
	 }

	/**
	 * @author ander
	 */
	@Test
	public void testListAll() {
		List<Cargo> cargos = new ArrayList<>();
	    cargos.add(new Cargo(1l, "Teste"));
	    cargos.add(new Cargo(2l, "Teste2"));
	    cargos.add(new Cargo(3l, "Teste3"));

	    List<Cargo> lista = service.findAll();

	    assertThat(lista).containsAll(cargos);
	}

	/**
	 * @author ander
	 */
	@Test
	public void testSave() {
		Cargo cargo = repo.save(new Cargo("Teste"));
		assertThat(cargo.getId()).isEqualTo(1l);
	}

	/**
	 * @author ander
	 */
	@Test
	public void testUpdate() {
		final String nome = "Teste2";
		Cargo cargo = repo.save(repo.save(new Cargo(1l, nome)));

		assertThat(cargo.getNome()).isEqualTo(nome);
	}
}