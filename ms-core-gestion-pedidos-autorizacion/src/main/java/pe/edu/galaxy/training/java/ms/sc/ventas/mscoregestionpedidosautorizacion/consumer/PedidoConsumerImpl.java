package pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionpedidosautorizacion.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionpedidosautorizacion.dto.Orden;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionpedidosautorizacion.service.OrdenService;

@Slf4j
@Service
public class PedidoConsumerImpl implements PedidoConsumer {

	@Autowired
	private OrdenService ordenService;  
	
	@KafkaListener(
			topics = "${custom.kafka.topic-name-ce}",
			groupId = "${custom.kafka.group-id-ce}",
			containerFactory = "ordenKafkaListenerContainerFactory")
	@Override
	public void consumeMessage(Orden type) {
		log.info("Consumer de Pedido de Rapido message [{}]", type);
		

		//if (type.getEstado().equals("3")) {
			this.ordenService.actualizarEstado(type.getIdOrden(), type.getEstado());
		//}
		

	}

}