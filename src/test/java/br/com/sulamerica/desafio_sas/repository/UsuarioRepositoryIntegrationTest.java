package br.com.sulamerica.desafio_sas.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.sulamerica.desafio_sas.entity.Cargo;
import br.com.sulamerica.desafio_sas.entity.Perfil;
import br.com.sulamerica.desafio_sas.entity.Usuario;
import br.com.sulamerica.desafio_sas.exceptions.NegocioException;

/**
 * @author ander
 */
@DataJpaTest
@RunWith(SpringRunner.class)
public class UsuarioRepositoryIntegrationTest {

    @Autowired
    private UsuarioRepository repo;

    /**
     * Constroi um objeto de usuario.
     * @author ander
     * @return
     */
    private Usuario montaUsuario() {
    	return new Usuario(null
    			,"Novo Usuario"
    			,"22222222222"
    			,"M"
    			,new Date()
    			,true
    			,new Cargo(1l)
    			,new Perfil(1l)
    			,"userNameTeste"
    			,new BCryptPasswordEncoder().encode("anypassword"));
    }

    /**
     * @author ander
     * @throws NegocioException
     */
    @Test
    public void testInclusao() throws NegocioException {
    	Usuario novoUsuario = this.montaUsuario();

    	novoUsuario = repo.save(novoUsuario);

    	assertThat(novoUsuario.getId()).isNotNull();

    	List<Usuario> usuarios = repo.findAll();
    	assertThat(usuarios.contains(novoUsuario)).isTrue();
    }

    /**
     * @author ander
     */
    @Test
    public void testFindAll() {
    	List<Usuario> usuarios = repo.findAll();
    	assertThat(usuarios).isNotNull();
    	assertThat(usuarios.size()).isGreaterThanOrEqualTo(1);
    }

    /**
     * @author ander
     */
    @Test
    public void testUpdate() {
    	Usuario novoUsuario = this.montaUsuario();
    	novoUsuario = repo.save(novoUsuario);

    	novoUsuario.setNome("Teste Update");
    	repo.save(novoUsuario);

    	Optional<Usuario> usuarioAtualizado = repo.findById(novoUsuario.getId());
    	assertThat(usuarioAtualizado.get()).isNotNull();
    	assertThat(usuarioAtualizado.get().getNome()).isEqualTo("Teste Update");
    }

    /**
     * @author ander
     */
    @Test
    public void testDelete() {
    	Usuario novoUsuario = this.montaUsuario();
    	novoUsuario = repo.save(novoUsuario);

    	assertThat(novoUsuario).isNotNull();

    	Long id = novoUsuario.getId();
    	repo.delete(novoUsuario);

    	Optional<Usuario> usuarioDeletado = repo.findById(id);
    	assertThat(usuarioDeletado.isEmpty()).isTrue();
    }
}