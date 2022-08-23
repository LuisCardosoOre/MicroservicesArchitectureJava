package pe.edu.galaxy.training.java.ms.sc.ventas.msadminautorization.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="USUARIO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Usuario{

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqUsuario")
	@SequenceGenerator(name = "seqUsuario", allocationSize = 1, sequenceName = "SEQ_USUARIO")
	@Column(name="ID_USUARIO")
	private int codigo;
	
	@Column(name="NOMBRE")
	private String nombre;
	
	@Column(name="USUARIO")
	private String usuario;
	
	@Column(name="CLAVE")
	private String clave;	

}