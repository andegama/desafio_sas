package br.com.sulamerica.desafio_sas.service;

import java.util.List;
import java.util.Optional;

import br.com.sulamerica.desafio_sas.entity.GenericEntity;
import br.com.sulamerica.desafio_sas.exceptions.NegocioException;

/**
 * Padronização de assinatura para os métodos principais que todo Service deve ter.
 * 
 * @author ander
 *
 * @param <T>
 */
public interface GenericService<T extends GenericEntity>{

	public T save(T t) throws NegocioException;
	public T update(T t) throws NegocioException;
	public void delete(T t) throws NegocioException;
	public Optional<T> findBydId(Number id) throws NegocioException;
	public List<T> findAll();
}