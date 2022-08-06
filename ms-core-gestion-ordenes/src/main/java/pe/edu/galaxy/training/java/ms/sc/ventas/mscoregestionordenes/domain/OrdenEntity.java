package pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenes.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@Builder
@Table(name="TB_ORDEN")
@Entity(name="Orden")
@NoArgsConstructor
@AllArgsConstructor
public class OrdenEntity extends GenericEntity{


	private static final long serialVersionUID = -4326287630821331120L;

	@Id
	@Column(name="ID_ORDEN")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqOrden")
	@SequenceGenerator(name = "seqOrden", allocationSize = 1, sequenceName = "SEQ_TB_ORDEN")
	@Positive(message="El id debe ser positivo")
	private Long id;
	
	@Column(name="FECHA")
	private String fecha;
	
	@Column(name="TOTAL")
	private Double total;
	
	@Column(name="ID_CLIENTE")
	private Long idCliente; 
	
	@OneToMany(mappedBy="orden", fetch=FetchType.LAZY, cascade=CascadeType.MERGE)
    private List<OrdenDetalleEntity> items;
}
