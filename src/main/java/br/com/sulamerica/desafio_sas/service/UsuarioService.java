package br.com.sulamerica.desafio_sas.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.sulamerica.desafio_sas.entity.Usuario;
import br.com.sulamerica.desafio_sas.exceptions.NegocioException;
import br.com.sulamerica.desafio_sas.repository.UsuarioRepository;
import br.com.sulamerica.desafio_sas.repository.UsuarioRepositoryJdbc;
import br.com.sulamerica.desafio_sas.util.Util;

@Service
public class UsuarioService implements GenericService<Usuario>, UserDetailsService{

	@Autowired
	private UsuarioRepository repo;

	@Autowired
	private UsuarioRepositoryJdbc repoJdbc;

	/**
	 * @author ander
	 */
	@Override
	public Usuario save(Usuario usuario) throws NegocioException{

		if (!Util.isCpfValido(usuario.getCpf())) {
			throw new NegocioException("CPF Inválido!");
		}

		if (this.findByNome(usuario.getNome()) != null) {
			throw new NegocioException("Não é possível inserir homônimo.");
		}

		if (this.findByCpf(usuario.getCpf()) != null) {
			throw new NegocioException("Não é possível inserir, cpf já registrado.");
		}

		return repo.save(usuario);
	}

	/**
	 * @author ander
	 */
	@Override
	public Usuario update(Usuario usuario) throws NegocioException{

		if (!exists(usuario)) {
			throw new NegocioException("Usuario não existe, favor escolher Usuario válido.");
		}

		return repo.save(usuario);
	}

	/**
	 * @author ander
	 */
	@Override
	public void delete(Usuario usuario) throws NegocioException {

		if (!exists(usuario)) {
			throw new NegocioException("Usuario não existe, favor escolher Usuario válido.");
		}
		
		repo.delete(usuario);
	}

	/**
	 * @author ander
	 */
	@Override
	public Optional<Usuario> findBydId(Number id) throws NegocioException{
		if (id == null) {
			throw new NegocioException("ID não pode ser NULL.");
		}
		return repo.findById(id.longValue());
	}

	/**
	 * @author ander
	 */
	@Override
	public List<Usuario> findAll() {
		return repo.findAll();
	}

	/**
	 * @author ander
	 * @param usuario
	 * @return
	 * @throws NegocioException
	 */
	public Boolean exists(Usuario usuario) throws NegocioException {
		if (usuario.getId() == null
				|| this.findBydId(usuario.getId()).isEmpty()){
			return false;
		}
		return true;
	}

	/**
	 * @author ander
	 * @return
	 */
	public List<Usuario> listAllFetch() {
		return repo.listAllFetch();
	}

	/**
	 * @author ander
	 * @param id
	 * @return
	 */
	public Usuario findByIdFetch(Long id) {
		return repo.findByIdFetch(id);
	}

	/**
	 * @author ander
	 * @param usuario
	 * @return
	 * @throws NegocioException 
	 */
	public Usuario inativar(Usuario usuario) throws NegocioException {

		usuario = this.findByIdFetch(usuario.getId());

		if (usuario == null) {
			throw new NegocioException("Usuário inexistente!");
		} else if (!usuario.getAtivo()) {
			throw new NegocioException("Impossível inativar um usuário que já esteja inativo.");
		}

		usuario.setAtivo(false);
		return this.update(usuario);
	}

	/**
	 * @author ander
	 * @param usuario
	 * @return
	 */
	public List<Usuario> findByFilter(Usuario usuario){
		return repoJdbc.findByFilter(usuario);
	}

	/**
	 * @author ander
	 * @return
	 */
	public List<Usuario> findCpfStartsWithZero(){
		return repo.findCpfStartsWithZero();
	}

	/**
	 * @author ander
	 * @return
	 */
	public List<Usuario> findFeminoMaiorIdade(){
		LocalDate localDate = LocalDate.now().minusYears(18);
		Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		return repo.findFeminoMaiorIdade(date);
	}

	/**
	 * @author ander
	 * @return
	 */
	public Usuario findByCpfAndNome(String cpf, String nome) {
		return repo.findByCpfAndNome(cpf, nome);
	}

	/**
	 * @author ander
	 * @return
	 */
	public Usuario findByNome(String nome) {
		return repo.findByNome(nome);
	}

	/**
	 * @author ander
	 * @return
	 */
	public Usuario findByCpf(String cpf) {
		return repo.findByCpf(cpf);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 return this.repo.findByNome(username);
	}
}