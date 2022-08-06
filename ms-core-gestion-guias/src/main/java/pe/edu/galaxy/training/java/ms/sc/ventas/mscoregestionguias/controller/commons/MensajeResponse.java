package pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionguias.controller.commons;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;
import lombok.Builder;

@Data
@Builder
@JsonPropertyOrder({ "codigo", "mensaje", "fecha", "data"})
@JsonInclude(JsonInclude.Include.NON_NULL)			
public class MensajeResponse implements Serializable{

	private static final long serialVersionUID = -6131706036153865748L;

	private Integer codigo;
	
	private String mensaje;
	
	private Object data;
	
	@Builder.Default
	private Date fecha= new Date();
}
