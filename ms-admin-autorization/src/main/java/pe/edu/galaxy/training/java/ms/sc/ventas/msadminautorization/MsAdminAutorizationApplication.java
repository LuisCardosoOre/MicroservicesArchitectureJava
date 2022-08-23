package pe.edu.galaxy.training.java.ms.sc.ventas.msadminautorization;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@EnableResourceServer
@EnableAuthorizationServer
@SpringBootApplication
public class MsAdminAutorizationApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsAdminAutorizationApplication.class, args);
	}
}
