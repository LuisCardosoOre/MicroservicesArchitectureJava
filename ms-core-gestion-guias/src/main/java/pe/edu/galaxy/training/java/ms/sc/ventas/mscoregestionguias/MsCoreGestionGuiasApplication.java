package pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionguias;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MsCoreGestionGuiasApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsCoreGestionGuiasApplication.class, args);
	}

}
