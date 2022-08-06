package pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionproductos.services.service;

import java.util.List;

import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionproductos.domain.ProductoEntity;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionproductos.services.exception.ServiceException;

public interface ProductoService extends GenericService<ProductoEntity>{
	
	List<ProductoEntity> findByLikeNombre( String descripcion ) throws ServiceException;

}