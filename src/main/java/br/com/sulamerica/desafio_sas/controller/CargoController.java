package br.com.sulamerica.desafio_sas.controller;

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
			return new ResponseEntity<Object>(service.save(cargo), HttpStatus.CREATED);

		} catch(NegocioException e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);

		} catch(Exception e) {
			return new ResponseEntity<Object>("Ops! Erro Inesperado", HttpStatus.INTERNAL_SERVER_ERROR);
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
			return new ResponseEntity<Object>(service.update(cargo), HttpStatus.OK);

		} catch(NegocioException e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);

		} catch(Exception e) {
			return new ResponseEntity<Object>("Ops! Erro Inesperado", HttpStatus.INTERNAL_SERVER_ERROR);
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
			return new ResponseEntity<Object>(HttpStatus.OK);

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