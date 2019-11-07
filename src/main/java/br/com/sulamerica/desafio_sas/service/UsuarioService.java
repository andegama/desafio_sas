package br.com.sulamerica.desafio_sas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.sulamerica.desafio_sas.entity.Usuario;

@Service
public class UsuarioService implements GenericService<Usuario>{

	@Override
	public Usuario save(Usuario t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario update(Usuario t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Usuario t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Optional<Usuario> findBydId(Number id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Usuario> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}