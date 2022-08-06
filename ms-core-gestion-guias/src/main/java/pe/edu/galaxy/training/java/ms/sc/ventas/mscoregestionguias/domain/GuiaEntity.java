package pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionguias.domain;

import java.io.Serializable;
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
@Table(name="TB_GUIA")
@Entity(name="Guia")
@NoArgsConstructor
@AllArgsConstructor
public class GuiaEntity implements Serializable {
	
	private static final long serialVersionUID = 7745878306256264931L;
	
	@Id
	@Column(name="ID_GUIA")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqGuia")
	@SequenceGenerator(name = "seqGuia", allocationSize = 1, sequenceName = "SEQ_TB_GUIA")
	@Positive(message="El id debe ser positivo")
	private Long id;
	
	@Column(name="FECHA_EMISION")
	private String fechaEmision;
	
	@Column(name="FECHA_TRASLADO")
	private String fechaTraslado;
	
	@Column(name="ID_ORDEN")
	private Long idOrden; 
	
	@Column(name="ID_MOTIVO")
	private Long idMotivo; 
	
	@Column(name="ID_CLIENTE")
	private Long idCliente; 
	
	@Column(name="TOTAL")
	private Double total;
	
	@Column(name="ESTADO")
	private String estado="1";
	
	@OneToMany(mappedBy="guia", fetch=FetchType.LAZY, cascade=CascadeType.MERGE)
    private List<GuiaDetalleEntity> items;

}