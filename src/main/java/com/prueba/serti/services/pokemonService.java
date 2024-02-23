package com.prueba.serti.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.prueba.serti.entities.accesos;
import com.prueba.serti.entities.pokemon;
import com.prueba.serti.repositories.pokemonRepository;

import jakarta.transaction.Transactional;

@Service
public class pokemonService implements BaseService<pokemon>{

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private pokemonRepository pokemonRepo;
	
	@Autowired
	private accesosService accesosService;
	
	private final String EXTERNAL_API_SPECIES_URL = "https://pokeapi.co/api/v2/pokemon-species/";
	
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
	@SuppressWarnings("unchecked")
	public pokemon findById(Integer id) throws Exception {

		
		try {
			
			String species_url = EXTERNAL_API_SPECIES_URL + id;
			
			Optional<pokemon> entityOptional = pokemonRepo.findById(id);
			
			if (entityOptional.isEmpty()) {
				
				ResponseEntity<LinkedHashMap<String, Object>> responseEntity = restTemplate.exchange(
					species_url,               											// URL de la API
		            HttpMethod.GET,            											// Método HTTP GET
		            null,          														// Cuerpo de la solicitud (vacío en este caso)
		            new ParameterizedTypeReference<LinkedHashMap<String, Object>>() {}  // Tipo de respuesta esperada
				);	               
			
				LinkedHashMap<String, Object> pokeMap = responseEntity.getBody();
		        pokemon newPokemon = new pokemon();
		        
		        newPokemon.setId((Integer) pokeMap.get("id"));
		        newPokemon.setName((String) pokeMap.get("name"));
		        newPokemon.setCapture_rate((Integer) pokeMap.get("capture_rate"));

		       
				LinkedHashMap<String, Object> habitatMap = (LinkedHashMap<String, Object>) pokeMap.get("habitat");
		        newPokemon.setHabitat_name((String) habitatMap.get("name"));
		      
		        LinkedHashMap<String, Object> evolution_chain_Map = (LinkedHashMap<String, Object>) pokeMap.get("evolution_chain");
		        newPokemon.setEvolution_chain_url((String) evolution_chain_Map.get("url"));
		        
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
