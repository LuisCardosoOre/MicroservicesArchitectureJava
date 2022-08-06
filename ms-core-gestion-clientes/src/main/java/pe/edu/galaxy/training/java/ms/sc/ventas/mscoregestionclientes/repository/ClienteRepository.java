package pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionclientes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionclientes.domain.ClienteEntity;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, Long>{

	@Query("select c from Cliente c where c.estado='1'")
	List<ClienteEntity> getAll();

}
