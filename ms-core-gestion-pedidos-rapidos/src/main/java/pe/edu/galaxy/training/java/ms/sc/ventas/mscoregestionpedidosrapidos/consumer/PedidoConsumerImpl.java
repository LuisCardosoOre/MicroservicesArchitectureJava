package pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionpedidosrapidos.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionpedidosrapidos.dto.Orden;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionpedidosrapidos.producer.PedidoProducer;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionpedidosrapidos.services.service.AutorizacionService;

@Slf4j
@Service
public class PedidoConsumerImpl implements PedidoConsumer {

	@Autowired
	private AutorizacionService autorizacionService;
	@Autowired
	private PedidoProducer pedidoProducer;
/*	
	public PedidoConsumerImpl(AutorizacionService autorizacionService, PedidoProducer pedidoProducer) {
		this.autorizacionService=autorizacionService;
		this.pedidoProducer= pedidoProducer;
	}
*/	
	@KafkaListener(
			topics = "${custom.kafka.topic-name}",
			groupId = "${custom.kafka.group-id}",
			containerFactory = "pedidoKafkaListenerContainerFactory")
	@Override
	public void consumeMessage(Orden type) {
		log.info("Consumer message [{}]", type);
		
		Orden autorizadoOrden = autorizacionService.autorizar(type);
		System.out.println("RET : " + autorizadoOrden);
		//if(autorizadoOrden != null)
		pedidoProducer.sendMessage(autorizadoOrden);
		
	}

}
