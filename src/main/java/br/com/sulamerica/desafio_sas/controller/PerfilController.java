package br.com.sulamerica.desafio_sas.controller;

import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

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
			return status(HttpStatus.CREATED).body(service.save(perfil));

		} catch(NegocioException e) {
			return badRequest().body(e.getMessage());

		} catch(Exception e) {
			return status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ops! Erro Inesperado");
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
			return ok(service.update(perfil));

		} catch(NegocioException e) {
			return badRequest().body(e.getMessage());

		} catch(Exception e) {
			return status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ops! Erro Inesperado");
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
			return ok("Removido com sucesso!");

		} catch(NegocioException e) {
			return badRequest().body(e.getMessage());

		} catch(Exception e) {
			return status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ops! Erro Inesperado");
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
			return status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ops! Erro Inesperado");
		}
	}
}