package pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionpedidosrapidos.services.client.guia;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
public class Guia implements Serializable{

	private Long id;

	private String fechaEmision;

	private String fechaTraslado;

	private Long idOrden; 

	private Long idMotivo; 

	private Long idCliente; 

	private Double total;

	private String estado;
	
	private List<GuiaDetalle> items;
}
