package pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenes.services.client.producto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

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
@JsonPropertyOrder(value = {"id","codigo","descripcion","precio","tipo","url"/*,"correo"*/})
public class ProductoDTO {

	private Long id;

	private String codigo;
	
	private String descripcion;

	private Double precio;
	
	private Integer stock;
	
	private String tipo;
	
	private String url;
}
