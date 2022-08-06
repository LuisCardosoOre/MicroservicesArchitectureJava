package pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionclientes.services.service;

import java.util.List;
import java.util.Optional;

import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionclientes.services.exception.ServiceException;

public interface GenericService<T> {

	List<T> getAll() throws ServiceException;
	
	List<T> findByLike(T t) throws ServiceException;
	
	Optional<T> findById(T t) throws ServiceException;
	
	T insert(T t) throws ServiceException;
	
	T update(T t) throws ServiceException;
	
	T delete(T t) throws ServiceException;
	
}
