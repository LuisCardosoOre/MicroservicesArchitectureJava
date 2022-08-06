package pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionguiasredis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class MsCoreGestionGuiasRedisApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsCoreGestionGuiasRedisApplication.class, args);
	}

}
