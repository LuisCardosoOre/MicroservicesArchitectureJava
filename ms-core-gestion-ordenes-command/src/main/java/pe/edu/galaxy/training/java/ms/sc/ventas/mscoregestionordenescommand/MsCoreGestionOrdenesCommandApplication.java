package pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenescommand;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenescommand.utils.Constantes;

@EnableEurekaClient
@RefreshScope
@SpringBootApplication
public class MsCoreGestionOrdenesCommandApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsCoreGestionOrdenesCommandApplication.class, args);
	}
	
	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI().info(new Info().title(Constantes.DOCUMENTACION_TITULO).description(Constantes.DOCUMENTACION_DESCRIPCION)
				.version(Constantes.DOCUMENTACION_VERSION).license(new License().name(Constantes.DOCUMENTACION_LICENCIA).url(Constantes.DOCUMENTACION_LICENCIA_URL))
				.contact(new Contact().email(Constantes.DOCUMENTACION_CREADOR_CORREO).url(Constantes.DOCUMENTACION_CREADOR_URL)));

	}

}
