package com.prueba.serti.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prueba.serti.entities.pokemon;

@Repository
public interface pokemonRepository extends JpaRepository<pokemon, Integer>{

}
