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

import br.com.sulamerica.desafio_sas.entity.Cargo;
import br.com.sulamerica.desafio_sas.exceptions.NegocioException;
import br.com.sulamerica.desafio_sas.service.CargoService;

/**
 * @author ander
 */
@RestController
@RequestMapping("/cargo")
public class CargoController{

	@Autowired
	private CargoService service;

	/**
	 * @author ander
	 * @param cargo
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseEntity<Object> save(@RequestBody Cargo cargo) {

		try {
			return status(HttpStatus.CREATED).body(service.save(cargo));

		} catch(NegocioException e) {
			return badRequest().body(e.getMessage());

		} catch(Exception e) {
			return status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ops! Erro Inesperado");
		}
	}

	/**
	 * @author ander
	 * @param cargo
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ResponseEntity<Object> update(@RequestBody Cargo cargo) {

		try {
			return ok(service.update(cargo));

		} catch(NegocioException e) {
			return badRequest().body(e.getMessage());

		} catch(Exception e) {
			return status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ops! Erro Inesperado");
		}
	}

	/**
	 * @author ander
	 * @param cargo
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public ResponseEntity<Object> delete(@RequestBody Cargo cargo) {

		try {
			service.delete(cargo);
			return ok().build();

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
			return ok(service.findAll());

		} catch(Exception e) {
			return status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ops! Erro Inesperado");
		}
	}
}