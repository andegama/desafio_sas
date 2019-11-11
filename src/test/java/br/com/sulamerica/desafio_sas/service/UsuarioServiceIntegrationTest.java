package br.com.sulamerica.desafio_sas.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.sulamerica.desafio_sas.entity.Cargo;
import br.com.sulamerica.desafio_sas.entity.Perfil;
import br.com.sulamerica.desafio_sas.entity.Usuario;
import br.com.sulamerica.desafio_sas.exceptions.NegocioException;
import br.com.sulamerica.desafio_sas.repository.UsuarioRepository;
import br.com.sulamerica.desafio_sas.repository.UsuarioRepositoryJdbc;

/**
 * @author ander
 */
@RunWith(SpringRunner.class)
public class UsuarioServiceIntegrationTest {

	@TestConfiguration
    static class UsuarioServiceTestContextConfiguration {

		@Bean
        public UsuarioService UsuarioService() {
            return new UsuarioService();
        }
    }

	@Autowired
	private UsuarioService service;

	@MockBean
	private UsuarioRepository repo;

	@MockBean
	private UsuarioRepositoryJdbc repoJdbc;

	@MockBean
	private PasswordEncoder encoder;

	/**
	 * @author ander
	 * @return
	 */
	private Usuario montaUsuario() {
    	return new Usuario(1l
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
	 */
	@Before
	public void setUp() {
	    Optional<Usuario> op = Optional.of(new Usuario(1l));

	    when(repo.findById(op.get().getId()))
	      .thenReturn(op);

	    List<Usuario> usuarios = new ArrayList<>();
	    usuarios.add(this.montaUsuario());
	    usuarios.add(this.montaUsuario());
	    usuarios.add(this.montaUsuario());

	    when(repo.findAll())
	    	.thenReturn(usuarios);

	    when(repo.save(this.montaUsuario()))
	    	.thenReturn(this.montaUsuario());

	    Usuario user = this.montaUsuario();
	    user.setNome("Teste2");
	    when(repo.save(user))
    		.thenReturn(user);

	    doNothing().when(repo).delete(isA(Usuario.class));
	}

	/**
	 * @author ander
	 * @throws NegocioException
	 */
	@Test
	public void testFindById() throws NegocioException {
	    Long id = 1l;
	    Optional<Usuario> found = service.findById(id);

	    assertThat(found.get().getId()).isEqualTo(id);
	 }

	/**
	 * @author ander
	 */
	@Test
	public void testListAll() {
		List<Usuario> usuarios = new ArrayList<>();
	    usuarios.add(this.montaUsuario());
	    usuarios.add(this.montaUsuario());
	    usuarios.add(this.montaUsuario());

	    List<Usuario> lista = service.findAll();

	    assertThat(lista).containsAll(usuarios);
	}

	/**
	 * @author ander
	 */
	@Test
	public void testSave() {
		Usuario usuario = repo.save(this.montaUsuario());
		assertThat(usuario.getId()).isEqualTo(1l);
	}

	/**
	 * @author ander
	 */
	@Test
	public void testUpdate() {
		final String nome = "Teste2";

		Usuario user = this.montaUsuario();
		user.setNome(nome);

		Usuario usuarioAtualizado = repo.save(repo.save(user));

		assertThat(usuarioAtualizado.getNome()).isEqualTo(nome);
	}
}