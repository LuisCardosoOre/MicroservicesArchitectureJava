package pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenesconsumer.consumer.client;

import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenesconsumer.document.Orden;

public interface OrderCommand {

	void enviar(Orden orden);
}
