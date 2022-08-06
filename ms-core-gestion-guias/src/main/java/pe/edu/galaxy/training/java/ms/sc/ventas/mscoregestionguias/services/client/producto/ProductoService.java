package pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionguias.services.client.producto;

import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionguias.services.client.ClientException;

public interface ProductoService {

	ProductoDTO findById(Long id) throws ClientException;; 
}
