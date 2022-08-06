package pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionguias.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionguias.domain.GuiaDetalleEntity;

@Repository
public interface GuiaDetalleRepository extends JpaRepository<GuiaDetalleEntity, Long>{

	@Query("select d from GuiaDetalle d")
	List<GuiaDetalleEntity> getAll();
}
