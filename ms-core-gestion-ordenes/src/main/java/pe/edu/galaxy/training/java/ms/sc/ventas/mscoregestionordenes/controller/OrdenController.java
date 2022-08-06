package pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenes.controller;

import static pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenes.utils.Constantes.API_ORDEN;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenes.controller.commons.MensajeResponse;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenes.domain.OrdenDetalleEntity;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenes.domain.OrdenEntity;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenes.dto.OrdenDTO;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenes.services.exception.ServiceException;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenes.services.service.OrdenDetalleService;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenes.services.service.OrdenService;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenes.utils.Constantes;

@Slf4j
@RestController
@RequestMapping(API_ORDEN)
@Data
@EqualsAndHashCode(callSuper = false)
@Tag(name = "Ordenes", description = "Ordenes API")
public class OrdenController extends GenericController{
	
	@Autowired
	private OrdenService ordenService;
	@Autowired
	private OrdenDetalleService ordenDetalleService;
	
	@Operation(summary = "Obtener todos los datos de las ordenes de compra pendientes")
	@GetMapping("/procesar")
	public ResponseEntity<MensajeResponse> getAll() {

		try {	
			List<OrdenEntity> ordenes = this.getOrdenService().getAll();
		

			if (ordenes == null || ordenes.isEmpty()) {
				return super.getNotContentResponseEntity();
			}
			
			MensajeResponse response= 	MensajeResponse
										.builder()
										.codigo(Constantes.CODIGO_EXITO)
										.mensaje(Constantes.MSG_EXITO_CONS+ " - " +ordenes.size()+ " registros")
										.data(ordenes)
										.build();
										
			return ResponseEntity.ok(response);

		} catch (ServiceException e) {
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
/*	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") Long id){
		
		if (id <= 0) {

			MensajeResponse msg = MensajeResponse.builder().codigo(0).mensaje("El id debe ser positivo").build();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msg);
		}

		try {
			OrdenEntity producto = this.getOrdenService().findById(OrdenEntity.builder().id(id).build()).orElse(null);
			if (producto == null) {
				return ResponseEntity.noContent().build();
			}
			return ResponseEntity.ok(producto);

		} catch (ServiceException e) {
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
*/
	
	@Operation(summary = "Obtener todos los datos de las ordenes de compra del cliente")
	@GetMapping
	public ResponseEntity<MensajeResponse> getAllOrdenes() {

		try {	
			List<OrdenDTO> ordenes = this.getOrdenService().getAllOrdenes();
		

			if (ordenes == null || ordenes.isEmpty()) {
				return super.getNotContentResponseEntity();
			}
			
			MensajeResponse response= 	MensajeResponse
										.builder()
										.codigo(Constantes.CODIGO_EXITO)
										.mensaje(Constantes.MSG_EXITO_CONS+ " - " +ordenes.size()+ " registros")
										.data(ordenes)
										.build();
										
			return ResponseEntity.ok(response);

		} catch (ServiceException e) {
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@Operation(summary = "Obtener información de una orden por su identificador")
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") Long id){
		
		if (id <= 0) {

			MensajeResponse msg = MensajeResponse.builder().codigo(0).mensaje("El id debe ser positivo").build();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msg);
		}

		try {
			OrdenDTO orden = this.getOrdenService().findByOrden(OrdenEntity.builder().id(id).build()).orElse(null);
			if (orden == null) {
				return ResponseEntity.noContent().build();
			}
			return ResponseEntity.ok(orden);

		} catch (ServiceException e) {
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@Operation(summary = "Registrar una orden de compra")
	@PostMapping
	public ResponseEntity<Object> insert(@RequestBody @Validated OrdenEntity type, BindingResult result) {

		if (result.hasErrors()) {
			String msgErr = super.formatMapMessage(result);
			log.info("msgErr " + msgErr);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, msgErr);
		}


		try {

			OrdenEntity orden = this.getOrdenService().insert(type);

			if (orden == null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(orden);
			}else {
				System.out.println("ID ORDEN : " + orden.getId());
			}

			return ResponseEntity.status(HttpStatus.CREATED).body(orden);

		} catch (ServiceException e) {
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}
	
	@Operation(summary = "Actualizar una orden de compra")
	@PutMapping("/{id}")
	public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody @Valid OrdenEntity type, BindingResult result) {
		
		type.setId(id);
		if (result.hasErrors()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, super.formatMapMessage(result));
		}
		try {
			OrdenEntity oOrden = this.getOrdenService().update(type);
			if (oOrden == null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(oOrden);
			}
			return ResponseEntity.ok(oOrden);

		} catch (ServiceException e) {
			log.error(e.getMessage());
			String argMsg[] = e.getMessage().split(":");
			MensajeResponse msg = MensajeResponse.builder().codigo(0).mensaje(argMsg[1]).build();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(msg);
		}
	}
	
	@Operation(summary = "Eliminar una orden de compra")
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(@PathVariable Long id) {
		try {
			OrdenEntity oOrden = this.getOrdenService().delete(OrdenEntity.builder().id(id).build());
			if (oOrden == null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(oOrden);
			}
			
			return ResponseEntity.ok(oOrden);
		} catch (ServiceException e) {
			String argMsg[] =e.getMessage().split(":");
			MensajeResponse msg = MensajeResponse.builder().codigo(0).mensaje(argMsg[1]).build();
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(msg);
		}
	}	
	
	@Operation(summary = "Generar una orden de compra con su detalle")
	@PostMapping("/comprar")
	public ResponseEntity<Object> insertCompra(@RequestBody @Validated OrdenEntity type, BindingResult result) {

		if (result.hasErrors()) {
			String msgErr = super.formatMapMessage(result);
			log.info("msgErr " + msgErr);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, msgErr);
		}


		try {

			OrdenEntity oOrden = this.getOrdenService().insert(type);
			log.info("ORDEN : " + oOrden );
			if (oOrden == null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(oOrden);
			}else {				
				for (OrdenDetalleEntity det : oOrden.getItems()) {					
					det.setOrden(OrdenEntity.builder().id(oOrden.getId()).build());
					this.getOrdenDetalleService().insert(det);
				}							
			}

			return ResponseEntity.status(HttpStatus.CREATED).body(oOrden);

		} catch (ServiceException e) {
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@PostMapping("/aprobar")
	public ResponseEntity<?> aprobarOrden(@RequestBody @Validated OrdenDTO type, BindingResult result){
		
		if (result.hasErrors()) {
			String msgErr = super.formatMapMessage(result);
			log.info("msgErr " + msgErr);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, msgErr);
		}
		
		try {

			Integer orden = this.getOrdenService().aprobacion(type);
			log.info("Orden" + orden);
			if (orden == 0) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ERROR");
			}else {				
				return ResponseEntity.status(HttpStatus.CREATED).body("EXITO");						
			}

			

		} catch (ServiceException e) {
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}