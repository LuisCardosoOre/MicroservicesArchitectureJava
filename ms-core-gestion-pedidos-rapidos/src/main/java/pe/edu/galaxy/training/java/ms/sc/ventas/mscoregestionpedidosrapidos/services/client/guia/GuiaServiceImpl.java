package pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionpedidosrapidos.services.client.guia;

import java.util.ArrayList;
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
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionpedidosrapidos.dto.OrdenDetalle;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionpedidosrapidos.services.client.ClientException;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionpedidosrapidos.utils.Constantes;
import reactor.core.Disposable;

@Slf4j
@Component
public class GuiaServiceImpl implements GuiaService{
	
	private DiscoveryClient discoveryClient;

	//private RestTemplate restTemplate;
	
	private CircuitBreaker circuitBreaker;
	
	private WebClient rest;

	public GuiaServiceImpl(WebClient.Builder builder,/*RestTemplate restTemplate,*/ DiscoveryClient discoveryClient, CircuitBreakerFactory<?, ?> circuitBreakerFactory) {
		this.discoveryClient = discoveryClient;
		//this.restTemplate = restTemplate;
		this.rest = builder.baseUrl(this.getURI() + Constantes.PATH_GUIA).build();
		this.circuitBreaker = circuitBreakerFactory.create(Constantes.URL_GUIA);
	}

	@Override
	public Integer insertarGuia(Orden t) throws ClientException {
			Guia type = Guia.builder().fechaEmision("19/08/2022").idOrden(t.getIdOrden()).idMotivo(1L).idCliente(t.getIdCliente()).total(t.getTotal()).estado("1").build();
			ArrayList<GuiaDetalle> lst = new ArrayList<>();
			for(OrdenDetalle d : t.getItems()) {
				lst.add(GuiaDetalle.builder().idProducto(d.getIdProducto()).costo(0.0).build());
			}
			type.setItems(lst);
			log.info("Guia : " + type);
			boolean rslt = false;
			return circuitBreaker.run(() -> {
				/*ResponseEntity<Guia> rpta = restTemplate.postForEntity(this.getURI() + Constantes.PATH_GUIA,type,
						Guia.class);*/
				
				this.getURI();
				log.info("rest Guia Service URI [{}]",rest);
				System.out.println("Guia Enviar : " + type);
				Guia guia = rest.post()
		        .uri("generar")
		        .body(BodyInserters.fromValue(type))
		        .retrieve()
		        .bodyToMono(Guia.class).block();
		        //.subscribe(System.out::println);
				System.out.println("REEE" + guia);
				log.info("Guia Service Client response",guia);
				
				if (!Objects.isNull(guia)) {
					System.out.println("Entrando a Guia Rpta");
					return 1;
				}
				return null;
/*
				if (!Objects.isNull(rpta)) {
					log.info("BODY : " + rpta.getBody());
					return 1;
				}
				return null;*/
			}, throwable -> getGuiasRedis(type));
	}

	private Integer getGuiasRedis(Guia t) {
		System.out.println("ENTRANDO A REDIS");
/*
		ResponseEntity<Guia> rpta = restTemplate.postForEntity(this.getURI_REDIS() + Constantes.PATH_GUIA_REDIS,t,
				Guia.class);

		if (!Objects.isNull(rpta)) {
			log.info("BODY : " + rpta.getBody());
			return 1;
		}else {
			return 0;
		}
*/
		this.rest = WebClient.builder().baseUrl(this.getURI_REDIS() + Constantes.PATH_GUIA_REDIS).build(); //WebClient.create(this.getURI_REDIS() + Constantes.PATH_GUIA_REDIS);
		this.getURI_REDIS();
		log.info("rest  [{}]",rest);
		Guia guia = rest.post()
        .uri("")
        .body(BodyInserters.fromValue(t))
        .retrieve()
        .bodyToMono(Guia.class).block();
        //.subscribe(System.out::println);

		
		if (!Objects.isNull(guia)) {
			return 1;
		}
		return 0;
}
	
	private String getURI() {
		if (Objects.isNull(discoveryClient)) {
			log.info(">> DiscoveryClient Guia is null");
			return "";
		}
		List<ServiceInstance> instances = discoveryClient.getInstances(Constantes.URL_GUIA);

		if (Objects.isNull(instances) || instances.isEmpty())
			return Constantes.ERROR_NOT_FOUND;
		System.out.println(instances.get(0).getHost());
		String uri = instances.get(0).getUri().toString();
		log.info("uri Guia : " + uri);
		return uri;
	}
	
	private String getURI_REDIS() {
		if (Objects.isNull(discoveryClient)) {
			log.info(">> DiscoveryClient Guia Redis is null");
			return "";
		}
		List<ServiceInstance> instances = discoveryClient.getInstances(Constantes.URL_GUIA_REDIS);

		if (Objects.isNull(instances) || instances.isEmpty())
			return Constantes.ERROR_NOT_FOUND;
		System.out.println(instances.get(0).getHost());
		String uri = instances.get(0).getUri().toString();
		log.info("uri Guia Redis : " + uri);
		return uri;
	}

}
