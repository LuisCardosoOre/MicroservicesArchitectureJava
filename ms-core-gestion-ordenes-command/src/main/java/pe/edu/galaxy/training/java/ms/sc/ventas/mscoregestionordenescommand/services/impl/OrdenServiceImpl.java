package pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenescommand.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenescommand.document.Orden;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenescommand.repository.OrdenRepository;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenescommand.services.exception.ServiceException;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenescommand.services.service.OrdenService;
import reactor.core.publisher.Mono;

@Service
@Data
@EqualsAndHashCode(callSuper = false)
public class OrdenServiceImpl implements OrdenService{
	
	@Autowired
	private OrdenRepository ordenRepository;


	@Override
	public Mono<Orden> save(Orden t) throws ServiceException {
		try {
			return this.getOrdenRepository().save(t);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

}
