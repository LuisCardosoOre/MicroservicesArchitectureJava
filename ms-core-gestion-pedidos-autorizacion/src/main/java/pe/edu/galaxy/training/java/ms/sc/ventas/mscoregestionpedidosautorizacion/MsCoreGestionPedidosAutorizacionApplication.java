package pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionpedidosautorizacion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@RefreshScope
@SpringBootApplication
public class MsCoreGestionPedidosAutorizacionApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsCoreGestionPedidosAutorizacionApplication.class, args);
	}

}
