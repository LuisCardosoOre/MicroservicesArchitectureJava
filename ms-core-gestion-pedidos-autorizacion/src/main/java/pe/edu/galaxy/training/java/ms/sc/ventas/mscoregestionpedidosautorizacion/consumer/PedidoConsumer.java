package pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionpedidosautorizacion.consumer;

import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionpedidosautorizacion.dto.Orden;

public interface PedidoConsumer {

	public void consumeMessage(Orden type);

}