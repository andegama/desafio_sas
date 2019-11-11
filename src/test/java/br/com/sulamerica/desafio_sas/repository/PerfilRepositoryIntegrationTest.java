package br.com.sulamerica.desafio_sas.repository;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.sulamerica.desafio_sas.entity.Perfil;
import br.com.sulamerica.desafio_sas.exceptions.NegocioException;

/**
 * @author ander
 */
@DataJpaTest
@RunWith(SpringRunner.class)
public class PerfilRepositoryIntegrationTest {

    @Autowired
    private PerfilRepository repo;

    /**
     * @author ander
     * @throws NegocioException
     */
    @Test
    public void testInclusao() throws NegocioException {
    	Perfil novoPerfil = new Perfil("Novo Perfil");
    	novoPerfil = repo.save(novoPerfil);

    	assertThat(novoPerfil.getId()).isNotNull();

    	List<Perfil> perfis = repo.findAll();
    	assertThat(perfis.contains(novoPerfil)).isTrue();
    }

    /**
     * @author ander
     */
    @Test
    public void testFindAll() {
    	List<Perfil> perfis = repo.findAll();
    	assertThat(perfis).isNotNull();
    	assertThat(perfis.size()).isGreaterThanOrEqualTo(1);
    }

    /**
     * @author ander
     */
    @Test
    public void testUpdate() {
    	Perfil novoPerfil = new Perfil("Novo Perfil");
    	novoPerfil = repo.save(novoPerfil);

    	novoPerfil.setNome("Teste Update");
    	repo.save(novoPerfil);

    	Optional<Perfil> perfilAtualizado = repo.findById(novoPerfil.getId());
    	assertThat(perfilAtualizado.get()).isNotNull();
    	assertThat(perfilAtualizado.get().getNome()).isEqualTo("Teste Update");
    }

    /**
     * @author ander
     */
    @Test
    public void testDelete() {
    	Perfil novoPerfil = new Perfil("Novo Perfil");
    	novoPerfil = repo.save(novoPerfil);

    	assertThat(novoPerfil).isNotNull();

    	Long id = novoPerfil.getId();
    	repo.delete(novoPerfil);

    	Optional<Perfil> perfilDeletado = repo.findById(id);
    	assertThat(perfilDeletado.isEmpty()).isTrue();
    }
}