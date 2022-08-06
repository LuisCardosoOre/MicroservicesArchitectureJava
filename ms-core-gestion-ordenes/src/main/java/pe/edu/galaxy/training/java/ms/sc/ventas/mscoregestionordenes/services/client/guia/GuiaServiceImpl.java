package pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenes.services.client.guia;

import java.util.List;
import java.util.Objects;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenes.services.client.ClientException;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenes.services.client.kafka.Orden;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenes.utils.Constantes;

@Slf4j
@Component
public class GuiaServiceImpl implements GuiaService{
	
	private DiscoveryClient discoveryClient;

	private RestTemplate restTemplate;

	public GuiaServiceImpl(RestTemplate restTemplate, DiscoveryClient discoveryClient) {
		this.discoveryClient = discoveryClient;
		this.restTemplate = restTemplate;
	}

	@Override
	public Integer insertarGuia(Orden t) throws ClientException {
			Guia type = Guia.builder().fechaEmision("06/08/2022").idOrden(t.getIdOrden()).idMotivo(1L).idCliente(t.getIdCliente()).total(t.getTotal()).estado("1").build();
			log.info("TYPE : " + type);
			ResponseEntity<Guia> rpta = restTemplate.postForEntity(this.getURI() + Constantes.PATH_GUIA,type,
					Guia.class);

			if (!Objects.isNull(rpta)) {
				log.info("BODY : " + rpta.getBody());
				return 1;
			}else {
				log.info("ERROR");
				return 0;
			}
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

}
