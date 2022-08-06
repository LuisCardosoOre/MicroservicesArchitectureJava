package pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenes.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@Builder
@Table(name="TB_ORDEN_DETALLE")
@Entity(name="OrdenDetalle")
@NoArgsConstructor
@AllArgsConstructor
public class OrdenDetalleEntity extends GenericEntity{

	private static final long serialVersionUID = 1178071027282849253L;

	@Id
	@Column(name="ID_ORD_DET")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqOrdenDetalle")
	@SequenceGenerator(name = "seqOrdenDetalle", allocationSize = 1, sequenceName = "SEQ_TB_ORDEN_DETALLE")
	private Long id;
	
	@Column(name="CANTIDAD")
	private Integer cantidad;

	@Column(name="PRECIO")
	private Double precio;	
	
	@Column(name="SUBTOTAL")
	private Double subtotal;	
	
	@Column(name="ID_PRODUCTO")
	private Long idProducto; 
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ID_ORDEN", nullable=false)
	@JsonIgnore
	private OrdenEntity orden; 

	
}
