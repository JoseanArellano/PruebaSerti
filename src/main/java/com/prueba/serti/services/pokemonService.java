package com.prueba.serti.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.prueba.serti.entities.accesos;
import com.prueba.serti.entities.pokemon;
import com.prueba.serti.repositories.pokemonRepository;

import jakarta.transaction.Transactional;

@Service
public class pokemonService implements BaseService<pokemon>{
	
	@Autowired
	private pokemonRepository pokemonRepo;
	
	@Autowired
	private accesosService accesosService;
	
	@Autowired
	private ConsumoAPIService apiService;
	
	@Override
	@Transactional
	public List<pokemon> findAll() throws Exception {
		
		try {
			
			List<pokemon> entities = pokemonRepo.findAll();
			return entities;
			
		} catch (Exception e) {

			throw new Exception(e.getMessage());	
		}
	}

	@Override
	@Transactional
	public pokemon findById(Integer id) throws Exception {
		
		try {
			
			Optional<pokemon> entityOptional = pokemonRepo.findById(id);
			
			if (entityOptional.isEmpty()) {
				
				pokemon newPokemon = apiService.consultarPokemon(entityOptional, id);
				
		        accesos newAcceso = new accesos();
		        newAcceso.setPokemon_name(newPokemon.getName());
		        LocalDateTime ahora = LocalDateTime.now();
		        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		        String fechaFormateada = ahora.format(formatter);
		        newAcceso.setTime(fechaFormateada);
		        accesosService.save(newAcceso);
		        
		        return save(newPokemon);
			}
			
			accesos newAcceso = new accesos();
	        newAcceso.setPokemon_name(entityOptional.get().getName());
	        LocalDateTime ahora = LocalDateTime.now();
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	        String fechaFormateada = ahora.format(formatter);
	        newAcceso.setTime(fechaFormateada);
	        accesosService.save(newAcceso);
			
			return entityOptional.get();
			
		} catch (Exception e) {

			throw new Exception(e.getMessage());
		}
	}

	@Override
	@Transactional
	public pokemon save(pokemon entity) throws Exception {

		try {
			
			return pokemonRepo.save(entity);
			
		} catch (Exception e) {

			throw new Exception(e.getMessage());
		}
	}

	@Override
	@Transactional
	public pokemon update(Integer id, pokemon entity) throws Exception {
	
		try {
			
			Optional<pokemon> entityOptional = pokemonRepo.findById(id);
			pokemon newpokemon = entityOptional.get();
			return pokemonRepo.save(newpokemon);
			
		} catch (Exception e) {

			throw new Exception(e.getMessage());
		}
	}

	@Override
	@Transactional
	public boolean delete(Integer id) throws Exception {
	
		try {
			
			if (pokemonRepo.existsById(id)) {
				
				pokemonRepo.deleteById(id);
				return true;
				
			}
			else {
			
				throw new Exception();
			}
			
		} catch (Exception e) {

			throw new Exception(e.getMessage());
		}
	}

}
