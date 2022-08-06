package pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionguias.services.service;

import java.util.Optional;

import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionguias.domain.GuiaEntity;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionguias.dto.GuiaDTO;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionguias.services.exception.ServiceException;

public interface GuiaService extends GenericService<GuiaEntity>{
	Optional<GuiaDTO> findByGuia( GuiaEntity t ) throws ServiceException;
}
