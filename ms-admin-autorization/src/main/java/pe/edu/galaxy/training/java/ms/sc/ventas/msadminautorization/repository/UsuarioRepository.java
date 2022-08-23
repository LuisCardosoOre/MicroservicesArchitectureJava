package pe.edu.galaxy.training.java.ms.sc.ventas.msadminautorization.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.galaxy.training.java.ms.sc.ventas.msadminautorization.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	@Query("select p from Usuario p where upper(usuario) = :usuario and estado='1'")
	Usuario loadUserByUsername(@Param("usuario") String usuario);
	
	
}
