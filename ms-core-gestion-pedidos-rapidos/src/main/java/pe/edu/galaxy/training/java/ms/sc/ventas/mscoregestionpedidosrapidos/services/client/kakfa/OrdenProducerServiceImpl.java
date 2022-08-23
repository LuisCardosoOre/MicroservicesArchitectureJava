package pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionpedidosrapidos.services.client.kakfa;

import java.util.List;
import java.util.Objects;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.extern.slf4j.Slf4j;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionpedidosrapidos.dto.Orden;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionpedidosrapidos.services.client.ClientException;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionpedidosrapidos.utils.Constantes;
import reactor.core.Disposable;

@Slf4j
@Component
public class OrdenProducerServiceImpl implements OrdenProducerService {
	
	private DiscoveryClient discoveryClient;

	//private RestTemplate restTemplate;

	private CircuitBreaker circuitBreaker;
	
	private WebClient rest;
	
	public OrdenProducerServiceImpl(WebClient.Builder builder,/*RestTemplate restTemplate,*/ DiscoveryClient discoveryClient,
			CircuitBreakerFactory<?, ?> circuitBreakerFactory) {
		this.discoveryClient = discoveryClient;
		//this.restTemplate = restTemplate;
		this.rest = builder.baseUrl(this.getURI() + Constantes.PATH_PRODUCER).build();
		this.circuitBreaker = circuitBreakerFactory.create(Constantes.URL_PRODUCER);
	}
	
	@Override
	public Integer sendMessage(Orden orden) throws ClientException {		
		return circuitBreaker.run(() -> {
			/*ResponseEntity<Orden> rpta = restTemplate.postForEntity(this.getURI() + Constantes.PATH_PRODUCER,orden,
					Orden.class);

			if (!Objects.isNull(rpta)) {
				return 1;
			}
			return null;*/
			
			this.getURI();
			log.info("Orden Producer Service Client uri  [{}]",rest);
			Orden o = rest.post()
	        .uri("")
	        .body(BodyInserters.fromValue(orden))
	        .retrieve()
	        .bodyToMono(Orden.class).block();
	        //.subscribe(System.out::println);
			log.info("Orden Producer Service Client response",o);
			if (!Objects.isNull(o)) {
				System.out.println("ENTRE PRODUCER ORDEN");
				return 1;
			}
			return null;
			
		}, throwable -> getOrden());
		
	}
	
	private Integer getOrden() {
		return 0;
	}
	
	private String getURI() {
		if (Objects.isNull(discoveryClient)) {
			log.info(">> DiscoveryClient Producer Orden is null");
			return "";
		}
		List<ServiceInstance> instances = discoveryClient.getInstances(Constantes.URL_PRODUCER);

		if (Objects.isNull(instances) || instances.isEmpty())
			return Constantes.ERROR_NOT_FOUND;
		System.out.println(instances.get(0).getHost());
		String uri = instances.get(0).getUri().toString();
		log.info("uri order producer : " + uri);
		return uri;
	}

}
