package pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenes.services.client.cliente;

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
@JsonPropertyOrder(value = {"id","dni","nombre","apellidos"/*,"telefono","correo"*/})
public class ClienteDTO {

	private Long id;

	private String dni;
	
	private String nombre;

	private String apellidos;
	/*
	private String correo;
	
	private Integer telefono;*/
}
