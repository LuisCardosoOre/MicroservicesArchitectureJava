package pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenes.services.client.producto.ProductoDTO;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
//@JsonPropertyOrder(value = {"id","idProducto","cantidad","precio","subtotal"})
public class OrdenDetalleDTO extends GenericDTO{
	
	private Long id;
	
	private Integer cantidad;

	private Double precio;	
	
	private Double subtotal;	
	
	private Long idProducto; 
	
	private ProductoDTO producto;

}
