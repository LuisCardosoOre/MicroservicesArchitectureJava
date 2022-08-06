package pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenesproducer.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


import lombok.extern.slf4j.Slf4j;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenesproducer.document.Orden;

@Slf4j
@Service
public class OrderProducerImpl implements OrdenProducer{

	@Value("${custom.kafka.topic-name}")
	private String topicName;

	@Autowired
	private KafkaTemplate<String, Orden> ordenKafkaTemplate;

	@Override
	public void sendMessage(Orden orden) {
		log.info("send taller", orden);
		log.info("topicName", topicName);
		ordenKafkaTemplate.send(topicName, orden);
	}
}
