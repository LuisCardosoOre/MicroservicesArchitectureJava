package pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionproductos.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionproductos.domain.ProductoEntity;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionproductos.repository.ProductoRepository;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionproductos.services.exception.ServiceException;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionproductos.services.service.ProductoService;

@Service
@Data
@EqualsAndHashCode(callSuper = false)
public class ProductoServiceImpl extends GenericServiceImpl implements ProductoService {

	private ProductoRepository productoRepository;

	public ProductoServiceImpl(ProductoRepository productoRepository) {
		super();
		this.productoRepository = productoRepository;
	}

	@Override	
	public List<ProductoEntity> getAll() throws ServiceException {
		try {
			return this.productoRepository.getAll();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<ProductoEntity> findByLike(ProductoEntity t) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<ProductoEntity> findById(ProductoEntity t) throws ServiceException {
		try {
			return this.productoRepository.findById(t.getId());
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public ProductoEntity insert(ProductoEntity t) throws ServiceException {
		try {
			return this.productoRepository.save(t);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public ProductoEntity update(ProductoEntity t) throws ServiceException {
		try {

			ProductoEntity oProducto = this.findById(t).orElse(null);
			if (oProducto == null) {
				return null;
			}
			BeanUtils.copyProperties(t, oProducto);

			return this.productoRepository.save(oProducto);
			
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public ProductoEntity delete(ProductoEntity t) throws ServiceException {
		try {
			ProductoEntity oProducto = this.findById(t).orElse(null);
			if (oProducto == null) {
				throw new ServiceException("Id no valido");
			}
			oProducto.setEstado("0");
			return this.productoRepository.save(oProducto);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<ProductoEntity> findByLikeNombre(String descripcion) throws ServiceException {
		try {

			descripcion = (descripcion == null) ? "" : descripcion;
			return this.productoRepository.findByLikeNombre("%" + descripcion.toUpperCase() + "%");

		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

}
