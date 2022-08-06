package pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenesproducer.controller;

import static pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenesproducer.utils.Constantes.API_ORDEN_PRODUCER;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenesproducer.document.Orden;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenesproducer.producer.OrdenProducer;

@Slf4j
@RestController
@RequestMapping(API_ORDEN_PRODUCER)
public class OrdenController {

	@Autowired
	private OrdenProducer ordenProducer;
	
	@PostMapping
	public ResponseEntity<?> send(@RequestBody Orden orden) {

		try {
			ordenProducer.sendMessage(orden);
			return ResponseEntity.ok(orden);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return ResponseEntity.internalServerError().build();
		}
		
	};
}
