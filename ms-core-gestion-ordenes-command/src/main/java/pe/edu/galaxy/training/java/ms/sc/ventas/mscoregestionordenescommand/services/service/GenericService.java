package pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenescommand.services.service;

import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenescommand.services.exception.ServiceException;
import reactor.core.publisher.Mono;

public interface GenericService<T> {

	Mono<T> save(T t) throws ServiceException;
}
