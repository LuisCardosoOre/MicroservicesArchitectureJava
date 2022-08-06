package pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionguiasredis.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionguiasredis.utils.Constantes;

@Configuration
public class ServiceGuiaConfiguration {
	
	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI().info(new Info().title(Constantes.DOCUMENTACION_TITULO).description(Constantes.DOCUMENTACION_DESCRIPCION)
				.version(Constantes.DOCUMENTACION_VERSION).license(new License().name(Constantes.DOCUMENTACION_LICENCIA).url(Constantes.DOCUMENTACION_LICENCIA_URL))
				.contact(new Contact().email(Constantes.DOCUMENTACION_CREADOR_CORREO).url(Constantes.DOCUMENTACION_CREADOR_URL)));

	}

}
