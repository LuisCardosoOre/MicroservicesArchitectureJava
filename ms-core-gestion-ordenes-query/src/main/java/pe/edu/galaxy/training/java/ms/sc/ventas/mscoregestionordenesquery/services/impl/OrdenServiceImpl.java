package pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenesquery.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenesquery.document.Orden;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenesquery.repository.OrdenRepository;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenesquery.services.exception.ServiceException;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenesquery.services.service.OrdenService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Data
@EqualsAndHashCode(callSuper = false)
public class OrdenServiceImpl implements OrdenService{
	
	@Autowired
	private OrdenRepository ordenRepository;

	@Override
	public Flux<Orden> findLike(Orden t) throws ServiceException {
		try {
			return this.getOrdenRepository().findAll();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public Mono<Orden> findById(Orden t) throws ServiceException {
		try {
			return this.getOrdenRepository().findById(t.getId());
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}




}
