package pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionclientes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MsCoreGestionClientesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsCoreGestionClientesApplication.class, args);
	}

}
