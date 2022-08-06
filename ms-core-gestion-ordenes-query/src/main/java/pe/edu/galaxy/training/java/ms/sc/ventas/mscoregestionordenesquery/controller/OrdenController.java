package pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenesquery.controller;

import static pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenesquery.utils.Constantes.API_ORDEN_QUERY;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenesquery.document.Orden;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenesquery.services.exception.ServiceException;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenesquery.services.service.OrdenService;

@Slf4j
@RestController
@RequestMapping(API_ORDEN_QUERY)
@Tag(name = "Orden", description = "Orden Query API")
public class OrdenController {
	
	@Autowired
	private OrdenService ordenService;
	
	@Operation(summary = "Obtener todos los datos de las ordenes")
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") String id){
		
		try {
			return ResponseEntity.ok(this.ordenService.findById(Orden.builder().id(id).build()));
		} catch (ServiceException e) {
			log.error(e.getMessage(),e);
			return ResponseEntity.internalServerError().build();
		}
	}

}
