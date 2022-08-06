package pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenesconsumer.consumer;

import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenesconsumer.document.Orden;

public interface OrdenConsumer {

	public void consumeMessage(Orden orden);
}
