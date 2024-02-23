package com.prueba.serti.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.serti.entities.pokemon;
import com.prueba.serti.services.pokemonService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/pokemon")
public class pokemonController {

	@Autowired
	private pokemonService service;
	
	@GetMapping("")
	public ResponseEntity<?> getAll() {
		
		try {
			
			return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
			
		} catch (Exception e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente mas tarde.\"}");
		}
	}
	
	@GetMapping("find/{id}")
	public ResponseEntity<?> getPokemon(@PathVariable int id) {
		
		try {
			
			return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
			
		} catch (Exception e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. El pokemon especificado no existe.\"}");
		}
	}
	
	@PostMapping("/save")
	public ResponseEntity<?> save(@RequestBody pokemon newpoke) {
		
		try {
			
			return ResponseEntity.status(HttpStatus.OK).body(service.save(newpoke));
			
		} catch (Exception e) {

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Por favor intente mas tarde.\"}");
		}
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> update(@PathVariable int id, @RequestBody pokemon entity) {
		
		try {
			
			return ResponseEntity.status(HttpStatus.OK).body(service.update(id, entity));
			
		} catch (Exception e) {

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Por favor intente mas tarde.\"}");
		}
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable int id) {
		
		try {
			
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(service.delete(id));
			
		} catch (Exception e) {

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Por favor intente mas tarde.\"}");
		}
	}
}
