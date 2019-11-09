package br.com.sulamerica.desafio_sas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sulamerica.desafio_sas.entity.Cargo;
import br.com.sulamerica.desafio_sas.exceptions.NegocioException;
import br.com.sulamerica.desafio_sas.repository.CargoRepository;
import br.com.sulamerica.desafio_sas.repository.CargoRepositoryImpl;

/**
 * @author ander
 */
@Service
public class CargoService implements GenericService<Cargo>{

	@Autowired
	private CargoRepository repo;

	@Autowired
	private CargoRepositoryImpl repoImpl;

	/**
	 * @author ander
	 */
	@Override
	public Cargo save(Cargo cargo) throws NegocioException{
		return repoImpl.save(cargo);
	}

	/**
	 * @author ander
	 */
	@Override
	public Cargo update(Cargo cargo) throws NegocioException{

		if (!exists(cargo)) {
			throw new NegocioException("Cargo não existe, favor escolher cargo válido.");
		}

		return repo.save(cargo);
	}

	/**
	 * @author ander
	 */
	@Override
	public void delete(Cargo cargo) throws NegocioException {

		if (!exists(cargo)) {
			throw new NegocioException("Cargo não existe, favor escolher cargo válido.");
		}
		
		repo.delete(cargo);
	}

	/**
	 * @author ander
	 */
	@Override
	public Optional<Cargo> findBydId(Number id) throws NegocioException{
		if (id == null) {
			throw new NegocioException("ID não pode ser NULL.");
		}
		return repo.findById(id.longValue());
	}

	/**
	 * @author ander
	 */
	@Override
	public List<Cargo> findAll() {
		return repo.findAll();
	}

	/**
	 * @author ander
	 * @param cargo
	 * @return
	 * @throws NegocioException
	 */
	public Boolean exists(Cargo cargo) throws NegocioException {
		if (cargo.getId() == null
				|| this.findBydId(cargo.getId()).isEmpty()){
			return false;
		}
		return true;
	}
}