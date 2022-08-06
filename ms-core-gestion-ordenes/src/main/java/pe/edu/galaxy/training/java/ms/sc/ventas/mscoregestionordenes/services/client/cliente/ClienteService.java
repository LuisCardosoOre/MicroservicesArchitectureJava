package pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenes.services.client.cliente;

import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenes.services.client.ClientException;

public interface ClienteService {

	ClienteDTO findById(Long id) throws ClientException; 
}
