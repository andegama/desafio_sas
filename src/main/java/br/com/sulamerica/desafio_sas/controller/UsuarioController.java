package br.com.sulamerica.desafio_sas.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.sulamerica.desafio_sas.dto.UsuarioDTO;
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
	public ResponseEntity<Object> save(@Valid @RequestBody UsuarioDTO usuario) {

		try {
			return new ResponseEntity<Object>(new UsuarioDTO(service.save(usuario.toEntity())), HttpStatus.CREATED);

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
	public ResponseEntity<Object> update(@RequestBody UsuarioDTO usuario) {

		try {
			service.update(usuario.toEntity());
			return new ResponseEntity<Object>(new UsuarioDTO(service.findByIdFetch(usuario.getId())), HttpStatus.OK);

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
	public ResponseEntity<Object> delete(@RequestBody UsuarioDTO usuario) {

		try {
			service.delete(usuario.toEntity());
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
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ResponseEntity<Object> listAll(){

		try {
			List<UsuarioDTO> lista = new ArrayList<>();
			ObjectMapper mapper = new ObjectMapper();
			mapper.setSerializationInclusion(Include.NON_NULL);

			for (Usuario usuario : service.listAllFetch()) {
				lista.add(new UsuarioDTO(usuario));
			}

			final String jsonList = mapper.writeValueAsString(lista);
			lista = mapper.readValue(jsonList, ArrayList.class);

			return new ResponseEntity<Object>(lista, HttpStatus.OK);

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
	public ResponseEntity<Object> inativar(@RequestBody UsuarioDTO usuario) {

		try {
			service.inativar(usuario.toEntity());
			return new ResponseEntity<Object>(new UsuarioDTO(service.findByIdFetch(usuario.getId())), HttpStatus.OK);

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
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/findCpfStartsWithZero", method = RequestMethod.GET)
	public ResponseEntity<Object> findCpfStartsWithZero(){

		try {
			List<UsuarioDTO> lista = new ArrayList<>();
			ObjectMapper mapper = new ObjectMapper();
			mapper.setSerializationInclusion(Include.NON_NULL);

			for (Usuario usuario : service.findCpfStartsWithZero()) {
				lista.add(new UsuarioDTO(usuario));
			}

			final String jsonList = mapper.writeValueAsString(lista);
			lista = mapper.readValue(jsonList, ArrayList.class);

			return new ResponseEntity<Object>(lista, HttpStatus.OK);

		} catch(Exception e) {
			return new ResponseEntity<Object>("Ops! Erro Inesperado", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * @author ander
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/findFeminoMaiorIdade", method = RequestMethod.GET)
	public ResponseEntity<Object> findFeminoMaiorIdade(){

		try {
			List<UsuarioDTO> lista = new ArrayList<>();
			ObjectMapper mapper = new ObjectMapper();
			mapper.setSerializationInclusion(Include.NON_NULL);

			for (Usuario usuario : service.findFeminoMaiorIdade()) {
				lista.add(new UsuarioDTO(usuario));
			}

			final String jsonList = mapper.writeValueAsString(lista);
			lista = mapper.readValue(jsonList, ArrayList.class);

			return new ResponseEntity<Object>(lista, HttpStatus.OK);

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
	public ResponseEntity<Object> findByFilter(@RequestBody UsuarioDTO usuario){

		try {
			return new ResponseEntity<Object>(service.findByFilter(usuario.toEntity()), HttpStatus.OK);

		} catch(Exception e) {
			return new ResponseEntity<Object>("Ops! Erro Inesperado", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}