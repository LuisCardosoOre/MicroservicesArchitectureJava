package pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionclientes.controller;

import static pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionclientes.utils.Constantes.API_CLIENTE;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import javax.validation.Valid;

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
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionclientes.controller.commons.MensajeResponse;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionclientes.domain.ClienteEntity;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionclientes.services.exception.ServiceException;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionclientes.services.service.ClienteService;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionclientes.utils.Constantes;

@Slf4j
@RestController
@RequestMapping(API_CLIENTE)
@Data
@EqualsAndHashCode(callSuper = false)
@Tag(name = "Cliente", description = "Cliente API")
public class ClienteController extends GenericController {
	
	private ClienteService clienteService;
	
	public ClienteController(ClienteService clienteService) {
		super();
		this.clienteService = clienteService;
	}

	@Operation(summary = "Obtener todos los datos de los clientes")
	@GetMapping
	public ResponseEntity<MensajeResponse> getAll() {
		try {	
			List<ClienteEntity> clientes = this.clienteService.getAll();
	
			if (clientes == null || clientes.isEmpty()) {
				return ResponseEntity.noContent().build();
			}

			MensajeResponse response= 	MensajeResponse
										.builder()
										.codigo(Constantes.CODIGO_EXITO)
										.mensaje(Constantes.MSG_EXITO_CONS+ " - " +clientes.size()+ " registros")
										.data(clientes)
										.build();
										
			return ResponseEntity.ok(response);

		} catch (ServiceException e) {
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}		
	}
	
	@Operation(summary = "Obtener información de un cliente por su identificador")
	@GetMapping("/{id}")
	public ResponseEntity<Object> findById(@PathVariable Long id) {
		if (id <= 0) {

			MensajeResponse msg = MensajeResponse.builder().codigo(0).mensaje("El id debe ser positivo").build();

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msg);
		}

		try {
			ClienteEntity cliente = this.clienteService.findById(ClienteEntity.builder().id(id).build()).orElse(null);
			if (cliente == null) {
				return ResponseEntity.noContent().build();
			}
			return ResponseEntity.ok(cliente);

		} catch (ServiceException e) {
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

	@Operation(summary = "Registrar un cliente")
	@PostMapping
	public ResponseEntity<Object> insert(@RequestBody @Validated ClienteEntity type, BindingResult result) {

		if (result.hasErrors()) {
			String msgErr = super.formatMapMessage(result);
			log.info("[CLIENTE] msg-Error : " + msgErr);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, msgErr);
		}

		try {

			ClienteEntity oCliente = this.clienteService.insert(type);

			if (oCliente == null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(oCliente);
			}

			return ResponseEntity.status(HttpStatus.CREATED).body(oCliente);

		} catch (ServiceException e) {
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

	@Operation(summary = "Actualizar un cliente")
	@PutMapping("/{id}")
	public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody @Valid ClienteEntity type,
			BindingResult result) {
		type.setId(id);
		if (result.hasErrors()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, super.formatMapMessage(result));
		}
		try {
			ClienteEntity oCliente = this.clienteService.update(type);
			if (oCliente == null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(oCliente);
			}
			return ResponseEntity.ok(oCliente);

		} catch (ServiceException e) {
			log.error(e.getMessage());
			String argMsg[] =e.getMessage().split(":");
			MensajeResponse msg = MensajeResponse.builder().codigo(0).mensaje(argMsg[1]).build();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(msg);
		}
	}

	@Operation(summary = "Eliminar un cliente")
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(@PathVariable Long id) {
		try {
			ClienteEntity oCliente = this.clienteService.delete(ClienteEntity.builder().id(id).build());
			if (oCliente == null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(oCliente);
			}
			
			return ResponseEntity.ok(oCliente);
		} catch (ServiceException e) {
			String argMsg[] =e.getMessage().split(":");
			MensajeResponse msg = MensajeResponse.builder().codigo(0).mensaje(argMsg[1]).build();
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(msg);
		}

	}
	
	@GetMapping(value = "/excep")
	public String exception() {
		String response = "";
		try {
			throw new Exception("Exception 1 ...");
		} catch (Exception e) {

			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			
			String stackTrace = sw.toString();
			log.error("Exception - " + stackTrace);
			response = stackTrace;
		}

		return response;
	}

}
