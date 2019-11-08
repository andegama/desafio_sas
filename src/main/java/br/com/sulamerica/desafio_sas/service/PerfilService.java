package br.com.sulamerica.desafio_sas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sulamerica.desafio_sas.entity.Perfil;
import br.com.sulamerica.desafio_sas.exceptions.NegocioException;
import br.com.sulamerica.desafio_sas.repository.PerfilRepository;

@Service
public class PerfilService implements GenericService<Perfil>{

	@Autowired
	private PerfilRepository repo;

	/**
	 * @author ander
	 */
	@Override
	public Perfil save(Perfil perfil) throws NegocioException{
		return repo.save(perfil);
	}

	/**
	 * @author ander
	 */
	@Override
	public Perfil update(Perfil perfil) throws NegocioException{

		if (!exists(perfil)) {
			throw new NegocioException("Perfil não existe, favor escolher Perfil válido.");
		}

		return repo.save(perfil);
	}

	/**
	 * @author ander
	 */
	@Override
	public void delete(Perfil perfil) throws NegocioException {

		if (!exists(perfil)) {
			throw new NegocioException("Perfil não existe, favor escolher Perfil válido.");
		}
		
		repo.delete(perfil);
	}

	/**
	 * @author ander
	 */
	@Override
	public Optional<Perfil> findBydId(Number id) throws NegocioException{
		if (id == null) {
			throw new NegocioException("ID não pode ser NULL.");
		}
		return repo.findById(id.longValue());
	}

	/**
	 * @author ander
	 */
	@Override
	public List<Perfil> findAll() {
		return repo.findAll();
	}

	/**
	 * @author ander
	 * @param perfil
	 * @return
	 * @throws NegocioException
	 */
	public Boolean exists(Perfil perfil) throws NegocioException {
		if (perfil.getId() == null
				|| this.findBydId(perfil.getId()).isEmpty()){
			return false;
		}
		return true;
	}
}