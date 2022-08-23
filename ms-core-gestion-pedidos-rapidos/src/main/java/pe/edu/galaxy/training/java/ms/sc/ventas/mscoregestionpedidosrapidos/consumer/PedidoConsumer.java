package pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionpedidosrapidos.consumer;

import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionpedidosrapidos.dto.Orden;

public interface PedidoConsumer {

	public void consumeMessage(Orden type);

}
