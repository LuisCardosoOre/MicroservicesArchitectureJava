package pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenesquery.services.service;

import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenesquery.services.exception.ServiceException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface GenericService<T> {

	Flux<T> findLike(T t) throws ServiceException;

	Mono<T> findById(T t) throws ServiceException;
}
