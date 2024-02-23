package com.prueba.serti.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prueba.serti.entities.accesos;

@Repository
public interface accesosRepository extends JpaRepository<accesos, Integer>{

}
