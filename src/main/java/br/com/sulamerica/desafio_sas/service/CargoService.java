package br.com.sulamerica.desafio_sas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.sulamerica.desafio_sas.entity.Cargo;
import br.com.sulamerica.desafio_sas.repository.CargoRepository;

@Service
public class CargoService implements GenericService<Cargo>{

	@Autowired
	private CargoRepository repo;

	@Override
	public Cargo save(Cargo cargo) {
		return repo.save(cargo);
	}

	@Override
	public Cargo update(Cargo cargo) {
		return repo.save(cargo);
	}

	@Override
	public void delete(Cargo cargo) {
		repo.delete(cargo);
	}

	@Override
	public Optional<Cargo> findBydId(Number id) {
		return repo.findById(id.longValue());
	}

	@Override
	public List<Cargo> findAll() {
		return repo.findAll();
	}
}