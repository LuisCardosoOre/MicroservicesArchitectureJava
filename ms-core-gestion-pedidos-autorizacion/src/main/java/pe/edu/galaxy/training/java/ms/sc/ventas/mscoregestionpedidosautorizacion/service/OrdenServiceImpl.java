package pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionpedidosautorizacion.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionpedidosautorizacion.repository.OrdenRepository;

@Slf4j
@Service
public class OrdenServiceImpl implements OrdenService{
	
	@Autowired
	private OrdenRepository ordenRepository;
	
	@Override
	public void actualizarEstado(Long id, String estado) {
		try {
			this.ordenRepository.actualizarEstado(id, estado);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}		
	}

}