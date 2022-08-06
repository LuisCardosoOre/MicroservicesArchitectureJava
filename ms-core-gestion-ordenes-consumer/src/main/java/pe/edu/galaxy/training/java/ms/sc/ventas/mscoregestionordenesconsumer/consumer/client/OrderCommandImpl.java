package pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenesconsumer.consumer.client;

import java.util.List;
import java.util.Objects;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.extern.slf4j.Slf4j;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenesconsumer.document.Orden;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenesconsumer.utils.Constantes;

@Slf4j
@Component
public class OrderCommandImpl implements OrderCommand{

	private DiscoveryClient discoveryClient;

	private WebClient rest;

	public OrderCommandImpl(WebClient.Builder builder, DiscoveryClient discoveryClient) {
		this.discoveryClient = discoveryClient;
		this.rest = builder.baseUrl(this.getURI() + Constantes.PATH_COMMAND).build();
	}

	@Override
	public void enviar(Orden orden) {
		try {
			
			log.info("Pre post enviar command [{}]",orden);
			this.getURI();
			log.info("rest  [{}]",rest);
			rest.post()
	        .uri("")
	        .body(BodyInserters.fromValue(orden))
	        .retrieve()
	        .bodyToMono(Orden.class)
	        .subscribe(System.out::println);
			
			log.info("Post enviar -> command");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private String getURI() {
		if (Objects.isNull(discoveryClient)) {
			log.info(">> DiscoveryClient Command is null");
			return "";
		}
		List<ServiceInstance> instances = discoveryClient.getInstances(Constantes.URL_COMMAND);
		
		if (Objects.isNull(instances) || instances.isEmpty()) {
			return Constantes.ERROR_NOT_FOUND;
		}
		String uri = instances.get(0).getUri().toString();
		log.info("uri ->" + uri);
		return uri;
	}
}
