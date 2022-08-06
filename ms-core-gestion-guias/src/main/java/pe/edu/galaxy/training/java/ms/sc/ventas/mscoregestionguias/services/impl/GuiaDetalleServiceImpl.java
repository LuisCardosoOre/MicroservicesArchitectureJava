package pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionguias.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionguias.domain.GuiaDetalleEntity;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionguias.repository.GuiaDetalleRepository;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionguias.services.exception.ServiceException;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionguias.services.service.GuiaDetalleService;

@Service
@Data
@EqualsAndHashCode(callSuper = false)
public class GuiaDetalleServiceImpl extends GenericServiceImpl implements GuiaDetalleService {
	
	@Autowired
	private GuiaDetalleRepository guiaDetalleRepository;
	
	@Override
	public List<GuiaDetalleEntity> getAll() throws ServiceException {
		try {
			return this.getGuiaDetalleRepository().getAll();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<GuiaDetalleEntity> findByLike(GuiaDetalleEntity t) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<GuiaDetalleEntity> findById(GuiaDetalleEntity t) throws ServiceException {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public GuiaDetalleEntity insert(GuiaDetalleEntity t) throws ServiceException {
		try {
			return this.getGuiaDetalleRepository().save(t);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public GuiaDetalleEntity update(GuiaDetalleEntity t) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GuiaDetalleEntity delete(GuiaDetalleEntity t) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

}
