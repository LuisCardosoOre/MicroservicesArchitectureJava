package pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionpedidosautorizacion.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionpedidosautorizacion.dto.Orden;

@Slf4j
@Service
public class PedidoProducerImpl implements PedidoProducer {

	@Value("${custom.kafka.topic-name}")
	private String topicName;

	@Autowired
	private KafkaTemplate<String, Orden> ordenKafkaTemplate;

	@Override
	public void sendMessage(Orden type) {
		log.info("send taller", type);
		log.info("topicName", topicName);
		ordenKafkaTemplate.send(topicName, type);
	}

}