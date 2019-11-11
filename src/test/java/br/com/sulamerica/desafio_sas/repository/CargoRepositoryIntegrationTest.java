package br.com.sulamerica.desafio_sas.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.sulamerica.desafio_sas.entity.Cargo;
import br.com.sulamerica.desafio_sas.exceptions.NegocioException;

/**
 * @author ander
 */
@DataJpaTest
@RunWith(SpringRunner.class)
public class CargoRepositoryIntegrationTest {

    @Autowired
    private CargoRepository repo;

    /**
     * @author ander
     * @throws NegocioException
     */
    @Test
    public void testInclusao() throws NegocioException {
    	Cargo novoCargo = new Cargo("Novo Cargo");
    	novoCargo = repo.save(novoCargo);

    	assertThat(novoCargo.getId()).isNotNull();

    	List<Cargo> cargos = repo.findAll();
    	assertThat(cargos.contains(novoCargo)).isTrue();
    }

    /**
     * @author ander
     */
    @Test
    public void testFindAll() {
    	List<Cargo> cargos = repo.findAll();
    	assertThat(cargos).isNotNull();
    	assertThat(cargos.size()).isGreaterThanOrEqualTo(1);
    }

    /**
     * @author ander
     */
    @Test
    public void testUpdate() {
    	Cargo novoCargo = new Cargo("Novo Cargo");
    	novoCargo = repo.save(novoCargo);

    	novoCargo.setNome("Teste Update");
    	repo.save(novoCargo);

    	Optional<Cargo> cargoAtualizado = repo.findById(novoCargo.getId());
    	assertThat(cargoAtualizado.get()).isNotNull();
    	assertThat(cargoAtualizado.get().getNome()).isEqualTo("Teste Update");
    }

    /**
     * @author ander
     */
    @Test
    public void testDelete() {
    	Cargo novoCargo = new Cargo("Novo Cargo");
    	novoCargo = repo.save(novoCargo);

    	assertThat(novoCargo).isNotNull();

    	Long id = novoCargo.getId();
    	repo.delete(novoCargo);

    	Optional<Cargo> cargoDeletado = repo.findById(id);
    	assertThat(cargoDeletado.isEmpty()).isTrue();
    }
}