package pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenesproducer.producer;

import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenesproducer.document.Orden;

public interface OrdenProducer {

	public void sendMessage(Orden orden);
}
