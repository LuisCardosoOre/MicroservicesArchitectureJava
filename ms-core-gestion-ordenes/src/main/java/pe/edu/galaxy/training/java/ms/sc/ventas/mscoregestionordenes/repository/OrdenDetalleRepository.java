package pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenes.domain.OrdenDetalleEntity;

@Repository
public interface OrdenDetalleRepository extends JpaRepository<OrdenDetalleEntity, Long>{

	@Query("select d from OrdenDetalle d where d.estado='1'")
	List<OrdenDetalleEntity> getAll();

}