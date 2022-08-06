package pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionguias.controller;

import static pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionguias.utils.Constantes.API_GUIA;

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
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionguias.controller.commons.MensajeResponse;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionguias.domain.GuiaDetalleEntity;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionguias.domain.GuiaEntity;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionguias.dto.GuiaDTO;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionguias.services.exception.ServiceException;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionguias.services.service.GuiaDetalleService;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionguias.services.service.GuiaService;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionguias.utils.Constantes;

@Slf4j
@RestController
@RequestMapping(API_GUIA)
@Data
@EqualsAndHashCode(callSuper = false)
@Tag(name = "Guias", description = "Guias API")
public class GuiaController extends GenericController{

	@Autowired
	private GuiaService guiaService;
	@Autowired
	private GuiaDetalleService guiaDetalleService;
	
	@Operation(summary = "Obtener todos los datos de las guias de salida")
	@GetMapping
	public ResponseEntity<MensajeResponse> getAll() {

		try {	
			List<GuiaEntity> guias = this.getGuiaService().getAll();
		

			if (guias == null || guias.isEmpty()) {
				return super.getNotContentResponseEntity();
			}
			
			MensajeResponse response= 	MensajeResponse
										.builder()
										.codigo(Constantes.CODIGO_EXITO)
										.mensaje(Constantes.MSG_EXITO_CONS+ " - " + guias.size() + " registros")
										.data(guias)
										.build();
										
			return ResponseEntity.ok(response);

		} catch (ServiceException e) {
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
/*	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") Long id){
		
		if (id <= 0) {

			MensajeResponse msg = MensajeResponse.builder().codigo(0).mensaje("El id debe ser positivo").build();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msg);
		}

		try {
			GuiaEntity guia = this.getGuiaService().findById(GuiaEntity.builder().id(id).build()).orElse(null);
			if (guia == null) {
				return ResponseEntity.noContent().build();
			}
			return ResponseEntity.ok(guia);

		} catch (ServiceException e) {
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
*/	
	@Operation(summary = "Obtener información de una guia por su identificador")
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") Long id){
		
		if (id <= 0) {

			MensajeResponse msg = MensajeResponse.builder().codigo(0).mensaje("El id debe ser positivo").build();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msg);
		}

		try {
			GuiaDTO guia = this.getGuiaService().findByGuia(GuiaEntity.builder().id(id).build()).orElse(null);
			if (guia == null) {
				return ResponseEntity.noContent().build();
			}
			return ResponseEntity.ok(guia);

		} catch (ServiceException e) {
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@Operation(summary = "Registrar una guia de salida")
	@PostMapping
	public ResponseEntity<Object> insert(@RequestBody @Validated GuiaEntity type, BindingResult result) {

		if (result.hasErrors()) {
			String msgErr = super.formatMapMessage(result);
			log.info("msgErr " + msgErr);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, msgErr);
		}


		try {

			GuiaEntity guia = this.getGuiaService().insert(type);

			if (guia == null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(guia);
			}else {
				System.out.println("ID GUIA : " + guia.getId());
			}

			return ResponseEntity.status(HttpStatus.CREATED).body(guia);

		} catch (ServiceException e) {
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@Operation(summary = "Actualizar una guia de salida")
	@PutMapping("/{id}")
	public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody @Valid GuiaEntity type, BindingResult result) {
		
		type.setId(id);
		if (result.hasErrors()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, super.formatMapMessage(result));
		}
		try {
			GuiaEntity oGuia = this.getGuiaService().update(type);
			if (oGuia == null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(oGuia);
			}
			return ResponseEntity.ok(oGuia);

		} catch (ServiceException e) {
			log.error(e.getMessage());
			String argMsg[] = e.getMessage().split(":");
			MensajeResponse msg = MensajeResponse.builder().codigo(0).mensaje(argMsg[1]).build();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(msg);
		}
	}
	
	@Operation(summary = "Eliminar una guia de salida")
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(@PathVariable Long id) {
		try {
			GuiaEntity oGuia = this.getGuiaService().delete(GuiaEntity.builder().id(id).build());
			if (oGuia == null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(oGuia);
			}
			
			return ResponseEntity.ok(oGuia);
		} catch (ServiceException e) {
			String argMsg[] =e.getMessage().split(":");
			MensajeResponse msg = MensajeResponse.builder().codigo(0).mensaje(argMsg[1]).build();
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(msg);
		}
	}
	
	@Operation(summary = "Generar una guia de salida con su detalle")
	@PostMapping("/generar")
	public ResponseEntity<Object> insertCompra(@RequestBody @Validated GuiaEntity type, BindingResult result) {

		if (result.hasErrors()) {
			String msgErr = super.formatMapMessage(result);
			log.info("msgErr " + msgErr);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, msgErr);
		}


		try {

			GuiaEntity oGuia = this.getGuiaService().insert(type);

			if (oGuia == null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(oGuia);
			}else {				
				for (GuiaDetalleEntity det : oGuia.getItems()) {					
					det.setGuia(GuiaEntity.builder().id(oGuia.getId()).build());
					this.getGuiaDetalleService().insert(det);
				}							
			}

			return ResponseEntity.status(HttpStatus.CREATED).body(oGuia);

		} catch (ServiceException e) {
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
}
