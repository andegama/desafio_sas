package br.com.sulamerica.desafio_sas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.sulamerica.desafio_sas.entity.Cargo;
import br.com.sulamerica.desafio_sas.service.CargoService;

@RestController
@RequestMapping("/cargo")
public class CargoController {

	@Autowired
	private CargoService service;

	@RequestMapping(value = "/cargo", method = RequestMethod.POST)
	public Cargo save(@RequestBody Cargo cargo) {
		return service.save(cargo); 
	}

	@RequestMapping(value = "list", method = RequestMethod.GET)
	public List<Cargo> listAll(){
		return service.findAll();
	}
}