package pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionpedidosautorizacion.controller;

import static pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionpedidosautorizacion.utils.Constantes.API_PEDIDOS_PRODUCER;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionpedidosautorizacion.dto.Orden;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionpedidosautorizacion.producer.PedidoProducer;

@Slf4j
@RestController
@RequestMapping(API_PEDIDOS_PRODUCER)
public class OrdenController {
	
	@Autowired
	private PedidoProducer pedidoProducer;
	
	@PostMapping
	public ResponseEntity<?> send(@RequestBody Orden type) {

		try {
			pedidoProducer.sendMessage(type);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return ResponseEntity.internalServerError().build();
		}
	};

}
