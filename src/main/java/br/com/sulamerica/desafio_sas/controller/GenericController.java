package br.com.sulamerica.desafio_sas.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.sulamerica.desafio_sas.response.ObjectResponse;

/**
 * Controller genérico para facilitar e padronizar a implementação do Response.
 * 
 * @author ander
 */
public class GenericController {
	
	public ResponseEntity<ObjectResponse> build(Object obj, HttpStatus status){
		ObjectResponse objectResponse = new ObjectResponse();
		objectResponse.setResponse(obj);
		return new ResponseEntity<ObjectResponse>(objectResponse, status);
	}

	public ResponseEntity<ObjectResponse> build(HttpStatus status){
		return new ResponseEntity<ObjectResponse>(new ObjectResponse(), status);
	}

	public ResponseEntity<ObjectResponse> build(Object obj, String msg, HttpStatus status){
		ObjectResponse objectResponse = new ObjectResponse();
		objectResponse.setResponse(obj);
		objectResponse.setMessage(msg);
		return new ResponseEntity<ObjectResponse>(objectResponse, status);
	}

	public ResponseEntity<ObjectResponse> build(String msg, HttpStatus status, Boolean temErro){
		ObjectResponse objectResponse = new ObjectResponse();
		objectResponse.setMessage(msg);
		objectResponse.setTemErro(temErro);
		return new ResponseEntity<ObjectResponse>(objectResponse, status);
	}
}