package pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionproductos.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import lombok.Data;

@Data
@MappedSuperclass
public class GenericEntity implements Serializable{

	private static final long serialVersionUID = -7625049342362930838L;
	
	@Column(name="ESTADO")
	protected String estado="1";

	
}