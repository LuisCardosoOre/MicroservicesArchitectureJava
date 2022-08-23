package pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionpedidosrapidos.services.client.kakfa;

import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionpedidosrapidos.dto.Orden;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionpedidosrapidos.services.client.ClientException;

public interface OrdenProducerService {

	Integer sendMessage(Orden orden) throws ClientException;
}
