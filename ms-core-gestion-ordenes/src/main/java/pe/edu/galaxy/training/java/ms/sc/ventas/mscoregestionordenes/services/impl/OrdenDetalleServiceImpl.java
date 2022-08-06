package pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenes.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenes.domain.OrdenDetalleEntity;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenes.repository.OrdenDetalleRepository;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenes.services.exception.ServiceException;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenes.services.service.OrdenDetalleService;

@Service
@Data
@EqualsAndHashCode(callSuper = false)
public class OrdenDetalleServiceImpl extends GenericServiceImpl implements OrdenDetalleService{
	
	@Autowired
	private OrdenDetalleRepository ordenDetalleRepository;
	
	@Override
	public List<OrdenDetalleEntity> getAll() throws ServiceException {
		try {
			return this.getOrdenDetalleRepository().getAll();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<OrdenDetalleEntity> findByLike(OrdenDetalleEntity t) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<OrdenDetalleEntity> findById(OrdenDetalleEntity t) throws ServiceException {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public OrdenDetalleEntity insert(OrdenDetalleEntity t) throws ServiceException {
		try {
			return this.getOrdenDetalleRepository().save(t);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public OrdenDetalleEntity update(OrdenDetalleEntity t) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrdenDetalleEntity delete(OrdenDetalleEntity t) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}
	


}
