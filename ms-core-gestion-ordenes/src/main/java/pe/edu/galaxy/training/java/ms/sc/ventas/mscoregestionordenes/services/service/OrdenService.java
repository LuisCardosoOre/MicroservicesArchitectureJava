package pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenes.services.service;

import java.util.List;
import java.util.Optional;

import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenes.domain.OrdenEntity;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenes.dto.OrdenDTO;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenes.services.exception.ServiceException;

public interface OrdenService extends GenericService<OrdenEntity> {

	Optional<OrdenDTO> findByOrden( OrdenEntity t ) throws ServiceException;
	List<OrdenDTO> getAllOrdenes() throws ServiceException;
	Integer aprobacion(OrdenDTO t) throws ServiceException;
	
}
