package pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionpedidosautorizacion.dto;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
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
