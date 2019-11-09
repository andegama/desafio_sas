package br.com.sulamerica.desafio_sas.repository;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.sulamerica.desafio_sas.entity.Perfil;
import br.com.sulamerica.desafio_sas.exceptions.NegocioException;

@DataJpaTest
@RunWith(SpringRunner.class)
public class PerfilRepositoryIntegrationTest {

    @Autowired
    private PerfilRepository repo;

    @Test
    public void testarInclusao() throws NegocioException {
    	Perfil novoPerfil = new Perfil("Novo Perfil");
    	novoPerfil = repo.save(novoPerfil);

    	List<Perfil> perfis = repo.findAll();
    	assertThat(perfis.contains(novoPerfil)).isTrue();
    }
}