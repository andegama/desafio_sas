package br.com.sulamerica.desafio_sas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.sulamerica.desafio_sas.entity.Perfil;
import br.com.sulamerica.desafio_sas.exceptions.NegocioException;
import br.com.sulamerica.desafio_sas.service.PerfilService;

/**
 * @author ander
 */
@RestController()
@RequestMapping("/perfil")
public class PerfilController{

	@Autowired
	private PerfilService service;

	/**
	 * @author ander
	 * @param perfil
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseEntity<Object> save(@RequestBody Perfil perfil) {

		try {
			return new ResponseEntity<Object>(service.save(perfil), HttpStatus.CREATED);

		} catch(NegocioException e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);

		} catch(Exception e) {
			return new ResponseEntity<Object>("Ops! Erro Inesperado", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * @author ander
	 * @param perfil
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ResponseEntity<Object> update(@RequestBody Perfil perfil) {

		try {
			return new ResponseEntity<Object>(service.update(perfil), HttpStatus.OK);

		} catch(NegocioException e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);

		} catch(Exception e) {
			return new ResponseEntity<Object>("Ops! Erro Inesperado", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * @author ander
	 * @param perfil
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public ResponseEntity<Object> delete(@RequestBody Perfil perfil) {

		try {
			service.delete(perfil);
			return new ResponseEntity<Object>("Removido com sucesso!", HttpStatus.OK);

		} catch(NegocioException e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);

		} catch(Exception e) {
			return new ResponseEntity<Object>("Ops! Erro Inesperado", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * @author ander
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ResponseEntity<Object> listAll(){

		try {
			return new ResponseEntity<Object>(service.findAll(), HttpStatus.OK);

		} catch(Exception e) {
			return new ResponseEntity<Object>("Ops! Erro Inesperado", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}