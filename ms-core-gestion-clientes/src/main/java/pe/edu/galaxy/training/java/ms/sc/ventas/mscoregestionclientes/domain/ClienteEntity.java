package pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionclientes.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
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
@Table(name="TB_CLIENTE")
@Entity(name="Cliente")
@NoArgsConstructor
@AllArgsConstructor		
public class ClienteEntity extends GenericEntity{

	private static final long serialVersionUID = 359831101846339957L;


	@Id
	@Column(name="ID_CLIENTE")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqCliente")
	@SequenceGenerator(name = "seqCliente", allocationSize = 1, sequenceName = "SEQ_TB_CLIENTE")
	private Long id;

	
	@NotNull(message = "DNI es Requerido")
	@Size(min = 7, max = 7, message = "DNI debe ser de 7 numeros")
	@Column(name="DNI")
	private String dni;
	
	@Column(name="NOMBRE")
	private String nombre;

	@Column(name="APELLIDOS")
	private String apellidos;
	/*
	@Column(name="CORREO")
	private String correo;
	
	@Column(name="TELEFONO")
	private Integer telefono;*/
	
	@NotNull(message = "Usuario es Requerido")
	@Column(name="USUARIO")
	private String usuario;
	
	@NotNull(message = "Contrasena es Requerido")
	@Column(name="CLAVE")
	private String clave;
	
}
