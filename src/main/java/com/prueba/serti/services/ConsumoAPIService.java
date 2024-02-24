package com.prueba.serti.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.prueba.serti.entities.accesos;
import com.prueba.serti.entities.pokemon;

@Service
public class ConsumoAPIService {
	
	@Autowired
	private RestTemplate restTemplate;

	private final String EXTERNAL_API_SPECIES_URL = "https://pokeapi.co/api/v2/pokemon-species/";
	
	@SuppressWarnings("unchecked")
	public pokemon consultarPokemon(Optional<pokemon> poke, Integer id) {
		
	
			String species_url = EXTERNAL_API_SPECIES_URL + id;
			
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
	        
	        return newPokemon;
	}
}
