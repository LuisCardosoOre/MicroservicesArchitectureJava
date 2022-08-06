package pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionclientes.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import lombok.Data;

@Data
@MappedSuperclass
public class GenericEntity implements Serializable{

	private static final long serialVersionUID = 7998494341782769976L;
	@Column(name="ESTADO")
	protected String estado="1";

	
}