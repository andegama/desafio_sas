package br.com.sulamerica.desafio_sas.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.sulamerica.desafio_sas.entity.Usuario;
import br.com.sulamerica.desafio_sas.exceptions.NegocioException;
import br.com.sulamerica.desafio_sas.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController{

	@Autowired
	private UsuarioService service;

	/**
	 * @author ander
	 * @param usuario
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseEntity<Object> save(@Valid @RequestBody Usuario usuario) {

		try {
			return new ResponseEntity<Object>(service.save(usuario), HttpStatus.CREATED);

		} catch(NegocioException e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);

		} catch(Exception e) {
			return new ResponseEntity<Object>("Ops! Erro Inesperado", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * @author ander
	 * @param usuario
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ResponseEntity<Object> update(@RequestBody Usuario usuario) {

		try {
			service.update(usuario);
			return new ResponseEntity<Object>(service.findByIdFetch(usuario.getId()), HttpStatus.OK);

		} catch(NegocioException e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);

		} catch(Exception e) {
			return new ResponseEntity<Object>("Ops! Erro Inesperado", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * @author ander
	 * @param usuario
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public ResponseEntity<Object> delete(@RequestBody Usuario usuario) {

		try {
			service.delete(usuario);
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
			return new ResponseEntity<Object>(service.listAllFetch(), HttpStatus.OK);

		} catch(Exception e) {
			return new ResponseEntity<Object>("Ops! Erro Inesperado", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * @author ander
	 * @param usuario
	 * @return
	 */
	@RequestMapping(value = "/inativar", method = RequestMethod.PUT)
	public ResponseEntity<Object> inativar(@RequestBody Usuario usuario) {

		try {
			service.inativar(usuario);
			return new ResponseEntity<Object>(service.findByIdFetch(usuario.getId()), HttpStatus.OK);

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
	@RequestMapping(value = "/findCpfStartsWithZero", method = RequestMethod.GET)
	public ResponseEntity<Object> findCpfStartsWithZero(){

		try {
			return new ResponseEntity<Object>(service.findCpfStartsWithZero(), HttpStatus.OK);

		} catch(Exception e) {
			return new ResponseEntity<Object>("Ops! Erro Inesperado", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * @author ander
	 * @return
	 */
	@RequestMapping(value = "/findFeminoMaiorIdade", method = RequestMethod.GET)
	public ResponseEntity<Object> findFeminoMaiorIdade(){

		try {
			return new ResponseEntity<Object>(service.findFeminoMaiorIdade(), HttpStatus.OK);

		} catch(Exception e) {
			return new ResponseEntity<Object>("Ops! Erro Inesperado", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * @author ander
	 * @param usuario
	 * @return
	 */
	@RequestMapping(value = "/findByFilter", method = RequestMethod.GET)
	public ResponseEntity<Object> findByFilter(@RequestBody Usuario usuario){

		try {
			return new ResponseEntity<Object>(service.findByFilter(usuario), HttpStatus.OK);

		} catch(Exception e) {
			return new ResponseEntity<Object>("Ops! Erro Inesperado", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}