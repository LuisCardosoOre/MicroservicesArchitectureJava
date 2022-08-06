package pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionguias.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionguias.domain.GuiaEntity;

@Repository
public interface GuiaRepository extends JpaRepository<GuiaEntity, Long>{

	@Query("select g from Guia g where g.estado='1'")
	List<GuiaEntity> getAll();
}
