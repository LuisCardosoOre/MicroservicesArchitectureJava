package pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionproductos.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@Builder
@Entity(name="Producto")
@Table(name="TB_PRODUCTO")
@NoArgsConstructor
@AllArgsConstructor
public class ProductoEntity extends GenericEntity{

	private static final long serialVersionUID = 7954392342666640152L;


	@Id
	@Column(name="ID_PRODUCTO")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	
	@Column(name="CODIGO")
	@NotNull(message = "Código es Requerido")
	@Size(min = 8, max = 10, message = "El codigo de producto debe tener 8 a 10 caracteres")
	private String codigo;
	
	@Column(name="DESCRIPCION")
	@NotNull(message = "Descripción es Requerido")
	private String descripcion;

	@Column(name="PRECIO")
	private Double precio;
	
	@Column(name="STOCK")
	private Integer stock;
	
	@Column(name="TIPO")
	private String tipo;
	
	@Column(name="URL")
	private String url;

}
