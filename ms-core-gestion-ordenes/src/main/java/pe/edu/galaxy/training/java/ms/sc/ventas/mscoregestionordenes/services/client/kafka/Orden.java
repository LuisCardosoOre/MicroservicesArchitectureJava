package pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenes.services.client.kafka;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenes.services.client.cliente.ClienteDTO;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Data
public class Orden implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Long idOrden;	
	private String fecha;	
	private Double total;		
	private Long idCliente; 	
	private String dni;	
	private String estado;	
	private List<OrdenDetalle> items;
}
