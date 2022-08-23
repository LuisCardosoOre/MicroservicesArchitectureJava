package pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenes.domain.OrdenEntity;

@Repository
public interface OrdenRepository extends JpaRepository<OrdenEntity, Long>{

	@Query("select o from Orden o where o.estado='3'")
	List<OrdenEntity> getAllOrdenes();

	@Query("select o from Orden o where o.estado='2'")
	List<OrdenEntity> getAll();
/*		
	@Query("select o from Orden o where t.idCliente = :codigo ")
	List<OrdenEntity> findByCliente(@Param("codigo") Long codigo );
*/
}