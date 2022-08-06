package pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenes.services.client.kafka;

import java.util.List;
import java.util.Objects;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenes.services.client.ClientException;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenes.utils.Constantes;

@Slf4j
@Component
public class OrdenProducerServiceImpl implements OrdenProducerService {
	
	private DiscoveryClient discoveryClient;

	private RestTemplate restTemplate;

	private CircuitBreaker circuitBreaker;
	
	public OrdenProducerServiceImpl(RestTemplate restTemplate, DiscoveryClient discoveryClient,
			CircuitBreakerFactory<?, ?> circuitBreakerFactory) {
		this.discoveryClient = discoveryClient;
		this.restTemplate = restTemplate;
		this.circuitBreaker = circuitBreakerFactory.create(Constantes.URL_PRODUCER);
	}
	
	@Override
	public Integer sendMessage(Orden orden) throws ClientException {		
		return circuitBreaker.run(() -> {
			ResponseEntity<Orden> rpta = restTemplate.postForEntity(this.getURI() + Constantes.PATH_PRODUCER,orden,
					Orden.class);

			if (!Objects.isNull(rpta)) {
				log.info("RPAT : " + rpta.getBody());
				return 1;
			}
			return 0;
		}, throwable -> getOrden());
		
	}
	
	private Integer getOrden() {
		return 0;
	}
	
	private String getURI() {
		if (Objects.isNull(discoveryClient)) {
			log.info(">> DiscoveryClient Producer is null");
			return "";
		}
		List<ServiceInstance> instances = discoveryClient.getInstances(Constantes.URL_PRODUCER);

		if (Objects.isNull(instances) || instances.isEmpty())
			return Constantes.ERROR_NOT_FOUND;
		System.out.println(instances.get(0).getHost());
		String uri = instances.get(0).getUri().toString();
		log.info("uri producer : " + uri);
		return uri;
	}

}
