package pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionpedidosautorizacion.producer;

import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionpedidosautorizacion.dto.Orden;

public interface PedidoProducer {

	public void sendMessage(Orden orden);

}