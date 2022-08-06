package pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenes.services.client.cliente;

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
public class ClienteServiceImpl implements ClienteService {

	private DiscoveryClient discoveryClient;

	private RestTemplate restTemplate;

	private CircuitBreaker circuitBreaker;

	public ClienteServiceImpl(RestTemplate restTemplate, DiscoveryClient discoveryClient,
			CircuitBreakerFactory<?, ?> circuitBreakerFactory) {
		this.discoveryClient = discoveryClient;
		this.restTemplate = restTemplate;
		this.circuitBreaker = circuitBreakerFactory.create(Constantes.URL_CLIENTE);
	}

	@Override
	public ClienteDTO findById(Long id) throws ClientException {

		return circuitBreaker.run(() -> {
			ResponseEntity<ClienteDTO> rpta = restTemplate.getForEntity(this.getURI() + Constantes.PATH_CLIENTE + id,
					ClienteDTO.class);

			if (!Objects.isNull(rpta)) {
				return rpta.getBody();
			}
			return null;
		}, throwable -> getClienteDTO());
	}

	private ClienteDTO getClienteDTO() {
		return ClienteDTO.builder().id(0L).dni("99999999").nombre("SIN NOMBRE").apellidos("SIN APELLIDOS").build();
	}

	private String getURI() {
		if (Objects.isNull(discoveryClient)) {
			log.info(">> DiscoveryClient Cliente is null");
			return "";
		}
		List<ServiceInstance> instances = discoveryClient.getInstances(Constantes.URL_CLIENTE);

		if (Objects.isNull(instances) || instances.isEmpty())
			return Constantes.ERROR_NOT_FOUND;
		System.out.println(instances.get(0).getHost());
		String uri = instances.get(0).getUri().toString();
		log.info("uri cliente : " + uri);
		return uri;
	}
}
