package pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenescommand.controller;

import static pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenescommand.utils.Constantes.API_ORDEN_COMMAND;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenescommand.document.Orden;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenescommand.services.exception.ServiceException;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenescommand.services.service.OrdenService;

@Slf4j
@RestController
@RequestMapping(API_ORDEN_COMMAND)
@Tag(name = "Orden", description = "Orden Command API")
public class OrdenController {
	
	@Autowired
	private OrdenService ordenService;
	
	@Operation(summary = "Registrar una Orden")
	@PostMapping
	public ResponseEntity<?> post(@RequestBody Orden orden){
		
		log.info("orden comando [{}]",orden);
		
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(this.ordenService.save(orden));
		} catch (ServiceException e) {
			log.error(e.getMessage(),e);
			return ResponseEntity.internalServerError().build();
		}
	}

}
