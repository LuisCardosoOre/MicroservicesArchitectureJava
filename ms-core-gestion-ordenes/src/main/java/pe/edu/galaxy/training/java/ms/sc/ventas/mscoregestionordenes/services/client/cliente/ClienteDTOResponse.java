package pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenes.services.client.cliente;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTOResponse {

	private Integer codigo;
	
	private String mensaje;
	
	private Date fecha;
	
	private ClienteDTO data;	
	
}
