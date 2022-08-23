package pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionpedidosautorizacion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionpedidosautorizacion.entity.OrdenEntity;

@Repository
public interface OrdenRepository extends JpaRepository<OrdenEntity, Long>{

	@Transactional
	@Modifying
	@Query(value =  "UPDATE TB_ORDEN SET ESTADO =:estado WHERE ID_ORDEN=:id",nativeQuery = true)
	void actualizarEstado(@Param("id") Long id, @Param("estado")  String estado);
	
}
