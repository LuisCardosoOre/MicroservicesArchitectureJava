package pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionproductos.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.jackson.databind.json.JsonMapper;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionproductos.utils.Constantes;

@Configuration
public class ServiceProductoConfiguration {

	public ServiceProductoConfiguration() {

	}

	@Bean
	public JsonMapper getJsonMapper() {
		return new JsonMapper();
	}

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI().info(new Info().title(Constantes.DOCUMENTACION_TITULO)
				.description(Constantes.DOCUMENTACION_DESCRIPCION).version(Constantes.DOCUMENTACION_VERSION)
				.license(new License().name(Constantes.DOCUMENTACION_LICENCIA)
						.url(Constantes.DOCUMENTACION_LICENCIA_URL))
				.contact(new Contact().email(Constantes.DOCUMENTACION_CREADOR_CORREO)
						.url(Constantes.DOCUMENTACION_CREADOR_URL)));

	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/v1/**").allowedOrigins("http://localhost:4200").allowedMethods("*").allowedHeaders("*");
			}
		};
	}

}