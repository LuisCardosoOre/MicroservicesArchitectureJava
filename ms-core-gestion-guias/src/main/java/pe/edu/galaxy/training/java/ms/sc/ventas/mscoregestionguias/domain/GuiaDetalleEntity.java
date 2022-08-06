package pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionguias.domain;

import java.io.Serializable;

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
@Table(name="TB_GUIA_DETALLE")
@Entity(name="GuiaDetalle")
@NoArgsConstructor
@AllArgsConstructor
public class GuiaDetalleEntity implements Serializable{
	
	private static final long serialVersionUID = 255162599208690179L;
	
	@Id
	@Column(name="ID_GUIA_DET")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqGuiaDetalle")
	@SequenceGenerator(name = "seqGuiaDetalle", allocationSize = 1, sequenceName = "SEQ_TB_GUIA_DETALLE")
	private Long id;
	
	@Column(name="ID_PRODUCTO")
	private Long idProducto; 
	
	@Column(name="COSTO")
	private Double costo;	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ID_GUIA", nullable=false)
	@JsonIgnore
	private GuiaEntity guia; 

}
