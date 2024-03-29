package com.prueba.serti.services;

import java.util.List;

public interface BaseService<E> {

	public List<E> findAll() throws Exception;
	public E findById(Integer id) throws Exception;
	public E save(E entity) throws Exception;
	public E update(Integer id, E enttity) throws Exception;
	public boolean delete(Integer id) throws Exception;
}
