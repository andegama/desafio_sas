package br.com.sulamerica.desafio_sas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sulamerica.desafio_sas.entity.Usuario;
import br.com.sulamerica.desafio_sas.exceptions.NegocioException;
import br.com.sulamerica.desafio_sas.repository.UsuarioRepository;

@Service
public class UsuarioService implements GenericService<Usuario>{

	@Autowired
	private UsuarioRepository repo;

	/**
	 * @author ander
	 */
	@Override
	public Usuario save(Usuario usuario) throws NegocioException{
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
}