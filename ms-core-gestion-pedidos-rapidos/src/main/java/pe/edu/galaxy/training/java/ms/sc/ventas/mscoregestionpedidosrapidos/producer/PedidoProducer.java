package pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionpedidosrapidos.producer;

import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionpedidosrapidos.dto.Orden;

public interface PedidoProducer {

	public void sendMessage(Orden type);

}