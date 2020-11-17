package com.stoom.demo.api.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stoom.demo.api.model.Adress;
import com.stoom.demo.api.service.AdressService;

@RestController
@RequestMapping("/adress")
public class AdressController {
	
	private AdressService service;

	@Autowired
	public AdressController(AdressService service) {
		this.service = service;
	}

	@GetMapping
	public Iterable<Adress> findAllAdresses() { 
		return service.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Adress> findById(@PathVariable Long id) {
		return new ResponseEntity<Adress>(service.findById(id), HttpStatus.OK);
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Adress> createAdress(@Valid @RequestBody Adress ad) {
		return new ResponseEntity<Adress>(service.saveAdress(ad), HttpStatus.OK);
	}
	
	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Adress> updateAdress(@PathVariable Long id, @Valid @RequestBody Adress ad) {
		return new ResponseEntity<Adress>(service.updateAdress(id, ad), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteAdress(@PathVariable Long id) {
		service.deleteAdress(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
