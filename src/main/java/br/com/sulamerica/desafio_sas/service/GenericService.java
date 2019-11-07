package br.com.sulamerica.desafio_sas.service;

import java.util.List;
import java.util.Optional;

import br.com.sulamerica.desafio_sas.entity.GenericEntity;

public interface GenericService<T extends GenericEntity>{

	public T save(T t);
	public T update(T t);
	public void delete(T t);
	public Optional<T> findBydId(Number id);
	public List<T> findAll();
}