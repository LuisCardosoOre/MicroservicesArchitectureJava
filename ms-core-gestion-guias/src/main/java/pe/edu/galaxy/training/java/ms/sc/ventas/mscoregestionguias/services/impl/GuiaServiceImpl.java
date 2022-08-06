package pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionguias.services.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.json.JsonMapper;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionguias.domain.GuiaEntity;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionguias.dto.GuiaDTO;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionguias.dto.GuiaDetalleDTO;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionguias.repository.GuiaRepository;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionguias.services.client.cliente.ClienteService;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionguias.services.client.producto.ProductoService;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionguias.services.exception.ServiceException;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionguias.services.service.GuiaService;

@Service
@Data
@EqualsAndHashCode(callSuper = false)
public class GuiaServiceImpl extends GenericServiceImpl implements GuiaService{
	
	@Autowired
	private GuiaRepository guiaRepository;
	
	@Autowired
	private JsonMapper jsonMapper;

	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private ProductoService productoService;
	
	@Override
	public List<GuiaEntity> getAll() throws ServiceException {
		try {
			return this.getGuiaRepository().getAll();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<GuiaEntity> findByLike(GuiaEntity t) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<GuiaEntity> findById(GuiaEntity t) throws ServiceException {
		try {
			return this.getGuiaRepository().findById(t.getId());
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public GuiaEntity insert(GuiaEntity t) throws ServiceException {
		try {
			return this.getGuiaRepository().save(t);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public GuiaEntity update(GuiaEntity t) throws ServiceException {
		try {

			GuiaEntity oGuia = this.findById(t).orElse(null);
			if (oGuia == null) {
				return null;
			}
			BeanUtils.copyProperties(t, oGuia);

			return this.getGuiaRepository().save(oGuia);

		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public GuiaEntity delete(GuiaEntity t) throws ServiceException {
		try {
			GuiaEntity oGuia = this.findById(t).orElse(null);
			if (oGuia == null) {
				throw new ServiceException("Id no valido");
			}
			oGuia.setEstado("0");
			return this.getGuiaRepository().save(oGuia);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public Optional<GuiaDTO> findByGuia(GuiaEntity t) throws ServiceException {
		try {
			Optional<GuiaEntity> guia = this.findById(t);
			if (!guia.isEmpty()) {
				GuiaDTO oGuiaDTO = this.getGuiaDTO(guia.get());
				if (!Objects.isNull(oGuiaDTO)) {
					oGuiaDTO.setCliente(clienteService.findById(oGuiaDTO.getIdCliente()));					
					for(GuiaDetalleDTO detalle : oGuiaDTO.getItems()) {
						detalle.setProducto(productoService.findById(detalle.getIdProducto()));
					}
				}
				return Optional.of(oGuiaDTO);
			} else {
				return Optional.empty();
			}
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	private GuiaDTO getGuiaDTO(GuiaEntity guiaEntity) {
		return jsonMapper.convertValue(guiaEntity, GuiaDTO.class);
	}
}