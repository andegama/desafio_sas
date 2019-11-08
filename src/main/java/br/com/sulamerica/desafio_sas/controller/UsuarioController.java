package br.com.sulamerica.desafio_sas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.sulamerica.desafio_sas.entity.Usuario;
import br.com.sulamerica.desafio_sas.exceptions.NegocioException;
import br.com.sulamerica.desafio_sas.response.ObjectResponse;
import br.com.sulamerica.desafio_sas.service.UsuarioService;

@RestController
public class UsuarioController extends GenericController{

	@Autowired
	private UsuarioService service;

	/**
	 * @author ander
	 * @param usuario
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseEntity<ObjectResponse> save(@RequestBody Usuario usuario) {

		try {
			return build(service.save(usuario), HttpStatus.CREATED);

		} catch(NegocioException e) {
			return build(e.getMessage(), HttpStatus.BAD_REQUEST, true);

		} catch(Exception e) {
			return build("Ops! Erro Inesperado", HttpStatus.BAD_REQUEST, true);
		}
	}

	/**
	 * @author ander
	 * @param usuario
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ResponseEntity<ObjectResponse> update(@RequestBody Usuario usuario) {

		try {
			return build(service.update(usuario), HttpStatus.CREATED);

		} catch(NegocioException e) {
			return build(e.getMessage(), HttpStatus.BAD_REQUEST, true);

		} catch(Exception e) {
			return build("Ops! Erro Inesperado", HttpStatus.BAD_REQUEST, true);
		}
	}

	/**
	 * @author ander
	 * @param usuario
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public ResponseEntity<ObjectResponse> delete(@RequestBody Usuario usuario) {

		try {
			service.delete(usuario);
			return build("Removido com sucesso!", HttpStatus.OK);

		} catch(NegocioException e) {
			return build(e.getMessage(), HttpStatus.BAD_REQUEST, true);

		} catch(Exception e) {
			return build("Ops! Erro Inesperado", HttpStatus.BAD_REQUEST, true);
		}
	}

	/**
	 * @author ander
	 * @return
	 */
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public ResponseEntity<ObjectResponse> listAll(){

		try {
			return build(service.findAll(), HttpStatus.OK);

		} catch(Exception e) {
			return build("Ops! Erro Inesperado", HttpStatus.BAD_REQUEST, true);
		}
	}
}