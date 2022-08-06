package pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenes.services.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.json.JsonMapper;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenes.domain.OrdenEntity;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenes.dto.OrdenDTO;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenes.dto.OrdenDetalleDTO;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenes.repository.OrdenRepository;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenes.services.client.cliente.ClienteService;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenes.services.client.guia.GuiaService;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenes.services.client.kafka.Orden;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenes.services.client.kafka.OrdenDetalle;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenes.services.client.kafka.OrdenProducerService;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenes.services.client.producto.ProductoDTO;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenes.services.client.producto.ProductoService;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenes.services.exception.ServiceException;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenes.services.service.OrdenService;

@Slf4j
@Service
@Data
@EqualsAndHashCode(callSuper = false)
public class OrdenServiceImpl extends GenericServiceImpl implements OrdenService {

	@Autowired
	private OrdenRepository ordenRepository;

	@Autowired
	private JsonMapper jsonMapper;

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private ProductoService productoService;

	@Autowired
	private OrdenProducerService ordenProducerService;

	@Autowired
	private GuiaService guiaService;

	@Override
	public List<OrdenEntity> getAll() throws ServiceException {
		try {
			return this.getOrdenRepository().getAllOrdenes();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<OrdenEntity> findByLike(OrdenEntity t) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<OrdenEntity> findById(OrdenEntity t) throws ServiceException {
		try {
			return this.getOrdenRepository().findById(t.getId());
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public OrdenEntity insert(OrdenEntity t) throws ServiceException {
		try {
			return this.getOrdenRepository().save(t);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public OrdenEntity update(OrdenEntity t) throws ServiceException {
		try {

			OrdenEntity oOrden = this.findById(t).orElse(null);
			if (oOrden == null) {
				return null;
			}
			BeanUtils.copyProperties(t, oOrden);

			return this.getOrdenRepository().save(oOrden);

		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public OrdenEntity delete(OrdenEntity t) throws ServiceException {
		try {
			OrdenEntity oOrden = this.findById(t).orElse(null);
			if (oOrden == null) {
				throw new ServiceException("Id no valido");
			}
			oOrden.setEstado("0");
			return this.getOrdenRepository().save(oOrden);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public Optional<OrdenDTO> findByOrden(OrdenEntity t) throws ServiceException {
		try {
			Optional<OrdenEntity> orden = this.findById(t);
			if (!orden.isEmpty()) {
				OrdenDTO oOrdenDTO = this.getOrdenDTO(orden.get());
				if (!Objects.isNull(oOrdenDTO)) {
					oOrdenDTO.setCliente(clienteService.findById(oOrdenDTO.getIdCliente()));
					for (OrdenDetalleDTO detalle : oOrdenDTO.getItems()) {
						detalle.setProducto(productoService.findById(detalle.getIdProducto()));
					}
				}
				return Optional.of(oOrdenDTO);
			} else {
				return Optional.empty();
			}
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<OrdenDTO> getAllOrdenes() throws ServiceException {
		try {
			List<OrdenEntity> lstOrden = this.getOrdenRepository().getAll();
			List<OrdenDTO> lst = new ArrayList<>();
			if (!lstOrden.isEmpty()) {
				for (OrdenEntity orden : lstOrden) {
					OrdenDTO oOrdenDTO = this.getOrdenDTO(orden);
					if (!Objects.isNull(oOrdenDTO)) {
						oOrdenDTO.setCliente(clienteService.findById(oOrdenDTO.getIdCliente()));
						for (OrdenDetalleDTO detalle : oOrdenDTO.getItems()) {
							detalle.setProducto(productoService.findById(detalle.getIdProducto()));
						}
					}
					lst.add(oOrdenDTO);
				}
				return lst;
			} else {
				return Collections.emptyList();
			}
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	private OrdenDTO getOrdenDTO(OrdenEntity ordenEntity) {
		return jsonMapper.convertValue(ordenEntity, OrdenDTO.class);
	}

	@Override
	public Integer aprobacion(OrdenDTO t) throws ServiceException {
		try {
			Orden type = Orden.builder().idOrden(t.getId()).fecha(t.getFecha()).total(t.getTotal())
					.idCliente(t.getIdCliente()).dni(t.getCliente().getDni()).estado("2").build();
			type.setItems(new ArrayList<>());
			for (OrdenDetalleDTO det : t.getItems()) {
				OrdenDetalle x = OrdenDetalle.builder().id(det.getId()).cantidad(det.getCantidad())
						.precio(det.getPrecio()).subtotal(det.getSubtotal()).idProducto(det.getIdProducto())
						.descripcion(det.getProducto().getDescripcion()).estado(det.getEstado()).build();
				type.getItems().add(x);
			}
			log.info("type : " + type);
			Integer rpta = ordenProducerService.sendMessage(type);
			log.info("RPTA : " + rpta);
			if (rpta == 1) {
				productoService.update(null);
				Integer rptaG = guiaService.insertarGuia(type);
				log.info("RPTA G : " + rptaG);
				if (rptaG == 1) {
					for (OrdenDetalleDTO det : t.getItems()) {
						ProductoDTO p = det.getProducto();
						p.setStock(p.getStock() - det.getCantidad());
						productoService.update(p);
					}
					return rptaG;
				} else {
					return 0;
				}
			} else {
				return 0;
			}
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
}
