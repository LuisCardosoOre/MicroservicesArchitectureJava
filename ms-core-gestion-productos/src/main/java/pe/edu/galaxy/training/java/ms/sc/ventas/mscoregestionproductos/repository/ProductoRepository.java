package pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionproductos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionproductos.domain.ProductoEntity;

public interface ProductoRepository extends JpaRepository<ProductoEntity, Long>{
	
	@Query("select p from Producto p where p.estado='1'")
	List<ProductoEntity> getAll();

	@Query("select p from Producto p where upper(p.descripcion) like :descripcion and p.estado='1'")
	List<ProductoEntity> findByLikeNombre(@Param("descripcion") String descripcion );

}
