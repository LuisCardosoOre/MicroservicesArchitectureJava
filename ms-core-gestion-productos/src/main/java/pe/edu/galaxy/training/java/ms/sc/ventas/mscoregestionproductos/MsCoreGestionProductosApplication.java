package pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionproductos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MsCoreGestionProductosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsCoreGestionProductosApplication.class, args);
	}

}
