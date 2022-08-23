package pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenes.services.client.autorizacion;

import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenes.services.client.ClientException;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenes.services.client.kafka.Orden;

public interface OrdenAutorizacionService {

	Integer sendPedido(Orden orden) throws ClientException;
}
