package pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionpedidosrapidos.services.client.producto;

import java.util.List;
import java.util.Objects;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.extern.slf4j.Slf4j;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionpedidosrapidos.dto.Orden;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionpedidosrapidos.services.client.ClientException;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionpedidosrapidos.utils.Constantes;
import reactor.core.Disposable;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class ProductoServiceImpl implements ProductoService {

	private DiscoveryClient discoveryClient;

	//private RestTemplate restTemplate;

	private CircuitBreaker circuitBreaker;
	
	private WebClient rest;

	public ProductoServiceImpl(WebClient.Builder builder,/*RestTemplate restTemplate,*/ DiscoveryClient discoveryClient,
			CircuitBreakerFactory<?, ?> circuitBreakerFactory) {
		this.discoveryClient = discoveryClient;
		//this.restTemplate = restTemplate;
		this.rest = builder.baseUrl(this.getURI() + Constantes.PATH_PRODUCTO).build();
		this.circuitBreaker = circuitBreakerFactory.create(Constantes.URL_PRODUCTO);
	}

	@Override
	public ProductoDTO findById(Long id) throws ClientException {

		return circuitBreaker.run(() -> {
/*			ResponseEntity<ProductoDTO> rpta = restTemplate.getForEntity(this.getURI() + Constantes.PATH_PRODUCTO + id,
					ProductoDTO.class);
*//*
			Mono<ProductoDTO> prd = rest.get()
					  .uri("{id}", id)
					  .retrieve()
					  .bodyToMono(ProductoDTO.class);
			prd.subscribe(System.out::println);
			prd.block();
			*/
			this.getURI();
			ProductoDTO prd = rest.get()
					  .uri("{id}", id)
					  .retrieve()
					  .bodyToMono(ProductoDTO.class).block();
/*			
			if (!Objects.isNull(rpta)) {
				return rpta.getBody();
			}
			return null;*/
			log.info("RPTA PROD : " + prd);
			if (!Objects.isNull(prd)) {
				log.info("HAY PROD : " + prd);
				return prd;
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

	@Override
	public void update(ProductoDTO t) throws ClientException {
			//restTemplate.put(this.getURI() + Constantes.PATH_PRODUCTO + t.getId(),t);
		
		this.getURI();
		log.info("rest  [{}]",rest);
		rest.put()
        .uri("{id}", t.getId())
        .body(BodyInserters.fromValue(t))
        .retrieve()
        .bodyToMono(ProductoDTO.class)
        .subscribe(System.out::println);
		
	}
}
