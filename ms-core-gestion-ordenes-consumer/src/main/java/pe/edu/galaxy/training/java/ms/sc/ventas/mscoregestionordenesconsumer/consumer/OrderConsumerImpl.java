package pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenesconsumer.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenesconsumer.consumer.client.OrderCommand;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenesconsumer.document.Orden;

@Slf4j
@Service
public class OrderConsumerImpl implements OrdenConsumer {

	@Autowired
	private OrderCommand orderCommand;

	@KafkaListener(topics = "${custom.kafka.topic-name}", groupId = "${custom.kafka.group-id}", containerFactory = "ordenKafkaListenerContainerFactory")
	@Override
	public void consumeMessage(Orden orden) {
		log.info("Consumer message [{}]", orden);
		orderCommand.enviar(orden);
	}
}
