package pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenescommand.document;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
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
