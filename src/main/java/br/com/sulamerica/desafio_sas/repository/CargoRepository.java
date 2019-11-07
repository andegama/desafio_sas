package br.com.sulamerica.desafio_sas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.sulamerica.desafio_sas.entity.Cargo;

public interface CargoRepository extends JpaRepository<Cargo, Long>{
}