package pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionpedidosrapidos.services.client.producto;

import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionpedidosrapidos.services.client.ClientException;

public interface ProductoService {

	ProductoDTO findById(Long id) throws ClientException; 
	void update(ProductoDTO t) throws ClientException; 
}
