package pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionpedidosrapidos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
//@RefreshScope
@SpringBootApplication
public class MsCoreGestionPedidosRapidosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsCoreGestionPedidosRapidosApplication.class, args);
	}

}
