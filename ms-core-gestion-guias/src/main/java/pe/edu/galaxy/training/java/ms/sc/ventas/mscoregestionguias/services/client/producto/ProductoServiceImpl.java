package pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionguias.services.client.producto;

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
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionguias.services.client.ClientException;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionguias.utils.Constantes;

@Slf4j
@Component
public class ProductoServiceImpl implements ProductoService {

	private DiscoveryClient discoveryClient;

	private RestTemplate restTemplate;

	private CircuitBreaker circuitBreaker;

	public ProductoServiceImpl(RestTemplate restTemplate, DiscoveryClient discoveryClient,
			CircuitBreakerFactory<?, ?> circuitBreakerFactory) {
		this.discoveryClient = discoveryClient;
		this.restTemplate = restTemplate;
		this.circuitBreaker = circuitBreakerFactory.create(Constantes.URL_PRODUCTO);
	}

	@Override
	public ProductoDTO findById(Long id) throws ClientException {

		return circuitBreaker.run(() -> {
			ResponseEntity<ProductoDTO> rpta = restTemplate.getForEntity(this.getURI() + Constantes.PATH_PRODUCTO + id,
					ProductoDTO.class);

			if (!Objects.isNull(rpta)) {
				return rpta.getBody();
			}
			return null;
		}, throwable -> getProductoDTO());
	}

	private ProductoDTO getProductoDTO() {
		return ProductoDTO.builder().id(0L).codigo("9999999999").descripcion("SIN DESCRIPCION").stock(0).precio(0.0)
				.tipo("").url("notfound.jpg").build();
	}

	private String getURI() {
		if (Objects.isNull(discoveryClient)) {
			log.info(">> DiscoveryClient Producto is null");
			return "";
		}
		List<ServiceInstance> instances = discoveryClient.getInstances(Constantes.URL_PRODUCTO);

		if (Objects.isNull(instances) || instances.isEmpty())
			return Constantes.ERROR_NOT_FOUND;
		System.out.println(instances.get(0).getHost());
		String uri = instances.get(0).getUri().toString();
		log.info("uri producto : " + uri);
		return uri;
	}
}
