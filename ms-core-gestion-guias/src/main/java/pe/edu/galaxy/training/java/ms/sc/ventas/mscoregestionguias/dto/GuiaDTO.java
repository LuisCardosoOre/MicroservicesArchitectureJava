package pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionguias.dto;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionguias.services.client.cliente.ClienteDTO;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
public class GuiaDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String fechaEmision;
	
	private String fechaTraslado;
	
	private Long idOrden; 

	private Long idMotivo; 
	
	private Long idCliente; 

	private Double total;

	private String estado;

    private List<GuiaDetalleDTO> items;
    
    private ClienteDTO cliente;
}
