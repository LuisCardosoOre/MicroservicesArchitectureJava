package pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionguiasredis.domain;

import java.io.Serializable;

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
public class GuiaDetalle implements Serializable {

	private Long id;
	
	private Long idProducto; 
	
	private Double costo;
}
