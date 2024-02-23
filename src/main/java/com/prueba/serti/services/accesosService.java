package com.prueba.serti.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prueba.serti.entities.accesos;
import com.prueba.serti.repositories.accesosRepository;

@Service
public class accesosService implements BaseService<accesos>{

	@Autowired
	private accesosRepository accesosRepo;
	
	@Override
	public List<accesos> findAll() throws Exception {
		
		try {
			
			List<accesos> entities = accesosRepo.findAll();
			return entities;
			
		} catch (Exception e) {

			throw new Exception(e.getMessage());	
		}
	}

	@Override
	public accesos findById(Integer id) throws Exception {
		
		try {
			
			Optional<accesos> entityOptional = accesosRepo.findById(id);
			return entityOptional.get();
			
		} catch (Exception e) {

			throw new Exception(e.getMessage());	
		}
	}

	@Override
	public accesos save(accesos entity) throws Exception {

		try {
			
			return accesosRepo.save(entity);
			
		} catch (Exception e) {

			throw new Exception(e.getMessage());	
		}
	}

	@Override
	public accesos update(Integer id, accesos enttity) throws Exception {

		try {
			
			Optional<accesos> entityOptional = accesosRepo.findById(id);
			accesos newacceso = entityOptional.get();
			return accesosRepo.save(newacceso);
			
		} catch (Exception e) {

			throw new Exception(e.getMessage());
		}
	}

	@Override
	public boolean delete(Integer id) throws Exception {
	
		try {
			
			if (accesosRepo.existsById(id)) {
				
				accesosRepo.deleteById(id);
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
