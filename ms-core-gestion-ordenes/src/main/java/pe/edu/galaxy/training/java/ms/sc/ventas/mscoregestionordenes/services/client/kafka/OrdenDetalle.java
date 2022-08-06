package pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenes.services.client.kafka;

import java.io.Serializable;

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
public class OrdenDetalle implements Serializable{
	
	private Long id;
	private Integer cantidad;
	private Double precio;	
	private Double subtotal;	
	private Long idProducto; 
	private String descripcion;
	private String estado;
}

