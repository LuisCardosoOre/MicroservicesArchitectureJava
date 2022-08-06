package pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MsCoreGestionOrdenesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsCoreGestionOrdenesApplication.class, args);
	}

}
