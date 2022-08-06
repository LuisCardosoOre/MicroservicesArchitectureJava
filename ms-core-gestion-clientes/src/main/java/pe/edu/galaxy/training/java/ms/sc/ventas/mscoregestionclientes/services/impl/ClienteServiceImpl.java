package pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionclientes.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;


import lombok.Data;
import lombok.EqualsAndHashCode;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionclientes.domain.ClienteEntity;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionclientes.repository.ClienteRepository;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionclientes.services.exception.ServiceException;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionclientes.services.service.ClienteService;

@Service
@Data
@EqualsAndHashCode(callSuper = false)
public class ClienteServiceImpl extends GenericServiceImpl implements ClienteService {

	private ClienteRepository clienteRepository;
	//private JsonMapper jsonMapper;
	
	public ClienteServiceImpl(ClienteRepository clienteRepository) {
		super();
		this.clienteRepository = clienteRepository;
		//this.jsonMapper = jsonMapper;
	}	

	@Override
	public List<ClienteEntity> getAll() throws ServiceException {
		try {
			return this.clienteRepository.getAll();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<ClienteEntity> findByLike(ClienteEntity t) throws ServiceException {
		/*List<ClienteEntity> lstClienteEntity = clienteRepository.findAll();
		return lstClienteEntity.stream().map(e -> this.getProductoDTO(e))
				.collect(Collectors.toList());*/
		
		return null;
	}

	@Override
	public Optional<ClienteEntity> findById(ClienteEntity t) throws ServiceException {
		try {
			return this.clienteRepository.findById(t.getId());
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public ClienteEntity insert(ClienteEntity t) throws ServiceException {
		try {
			return this.clienteRepository.save(t);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public ClienteEntity update(ClienteEntity t) throws ServiceException {
		try {

			ClienteEntity oCliente = this.findById(t).orElse(null);
			if (oCliente == null) {
				return null;
			}
			BeanUtils.copyProperties(t, oCliente);

			return this.clienteRepository.save(oCliente);
			
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public ClienteEntity delete(ClienteEntity t) throws ServiceException {
		try {
			ClienteEntity oCliente = this.findById(t).orElse(null);
			if (oCliente == null) {
				throw new ServiceException("Id no valido");
			}
			oCliente.setEstado("0");
			return this.clienteRepository.save(oCliente);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	/*
	// Mappers
	private ClienteDTO getClienteDTO(ClienteEntity e) {
		return jsonMapper.convertValue(e, ClienteDTO.class);
	}
	
	private ClienteEntity getClienteEntity(ClienteDTO d) {
		return jsonMapper.convertValue(d, ClienteEntity.class);
	}
	*/
}