package pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenes.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenes.services.client.cliente.ClienteDTO;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
//@JsonPropertyOrder(value = {"id","idCliente","fecha","total","items"})
public class OrdenDTO extends GenericDTO{

	private Long id;

	private String fecha;
	
	private Double total;
	
	private Long idCliente; 
	
    private List<OrdenDetalleDTO> items;
    
    private ClienteDTO cliente;
}
