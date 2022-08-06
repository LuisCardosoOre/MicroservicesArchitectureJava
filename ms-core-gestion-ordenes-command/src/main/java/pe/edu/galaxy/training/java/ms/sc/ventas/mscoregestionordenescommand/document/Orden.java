package pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenescommand.document;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "Orden")
public class Orden implements Serializable {
	
	@Id
    private String id = UUID.randomUUID().toString();	

	@Field("idOrden")
	private Long idOrden;
	
	@Field(name="fecha")
	private String fecha;
	
	@Field(name="total")
	private Double total;
	
	@Field(name="idCliente")	
	private Long idCliente; 
	
	@Field(name="dni")
	private String dni;
	
	@Field(name="estado")
	private String estado;
	
	private List<OrdenDetalle> items;
}
