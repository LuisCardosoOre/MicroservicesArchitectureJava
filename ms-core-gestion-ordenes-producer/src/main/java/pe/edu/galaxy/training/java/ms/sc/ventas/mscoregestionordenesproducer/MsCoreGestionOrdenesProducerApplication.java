package pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenesproducer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@RefreshScope
@SpringBootApplication
public class MsCoreGestionOrdenesProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsCoreGestionOrdenesProducerApplication.class, args);
	}

}