package pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionpedidosrapidos.configuration;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionpedidosrapidos.dto.Orden;


@Configuration
public class KafkaConsumerConfiguration {

	@Value("${custom.kafka.bootstrap-servers}")
	private String bootstrapServers;

	@Value("${custom.kafka.group-id}")
	private String groupId;

	public ConsumerFactory<String, Orden> pedidoConsumerFactory() {
	
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);

		return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(),new JsonDeserializer<>(Orden.class,false));
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, Orden> pedidoKafkaListenerContainerFactory() {
		
		ConcurrentKafkaListenerContainerFactory<String, Orden> factory = new ConcurrentKafkaListenerContainerFactory<>();		
		factory.setConsumerFactory(pedidoConsumerFactory());		
		return factory;
	}	
}
