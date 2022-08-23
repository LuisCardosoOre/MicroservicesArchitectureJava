package pe.edu.galaxy.training.java.ms.sc.ventas.msadminautorization.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class AutorizationConfiguration {

	  @Value("${signing.key}")
	  private String jwtSigningKey="";


	  public String getJwtSigningKey() {
	    return jwtSigningKey;
	  }
}
