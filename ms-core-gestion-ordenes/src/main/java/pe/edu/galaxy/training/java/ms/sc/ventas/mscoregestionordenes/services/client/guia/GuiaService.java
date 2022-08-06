package pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenes.services.client.guia;

import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenes.services.client.ClientException;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenes.services.client.kafka.Orden;

public interface GuiaService {

	Integer insertarGuia(Orden t) throws ClientException;
}
