package pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenes.services.client.kafka;

import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenes.services.client.ClientException;

public interface OrdenProducerService {

	Integer sendMessage(Orden orden) throws ClientException;
}
