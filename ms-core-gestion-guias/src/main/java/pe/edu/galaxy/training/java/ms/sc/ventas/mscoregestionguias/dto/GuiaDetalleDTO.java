package pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionguias.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionguias.services.client.producto.ProductoDTO;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
public class GuiaDetalleDTO implements Serializable {

	private Long id;
	
	private Double costo;	
	
	private Long idProducto; 
	
	private ProductoDTO producto;
}
