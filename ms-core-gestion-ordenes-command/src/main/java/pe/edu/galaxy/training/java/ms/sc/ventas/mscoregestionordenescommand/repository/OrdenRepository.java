package pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenescommand.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenescommand.document.Orden;

@Repository
public interface OrdenRepository extends ReactiveMongoRepository<Orden, String>{

}
