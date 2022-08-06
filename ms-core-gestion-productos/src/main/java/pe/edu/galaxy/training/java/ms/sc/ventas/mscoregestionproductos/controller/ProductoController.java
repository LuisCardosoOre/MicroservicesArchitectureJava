package pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionproductos.controller;

import static pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionproductos.utils.Constantes.API_PRODUCTO;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionproductos.controller.commons.MensajeResponse;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionproductos.domain.ProductoEntity;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionproductos.services.exception.ServiceException;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionproductos.services.service.ProductoService;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionproductos.utils.Constantes;

@Slf4j
@RestController
@RequestMapping(API_PRODUCTO)
@Data
@EqualsAndHashCode(callSuper = false)
@Tag(name = "Producto", description = "Producto API")
public class ProductoController extends GenericController {
	
	private ProductoService productoService;
	
	public ProductoController(ProductoService productoService) {
		super();
		this.productoService = productoService;
	}
	
	@Operation(summary = "Obtener todos los datos de los productos")
	@GetMapping
	public ResponseEntity<MensajeResponse> getAll() {

		try {	
			List<ProductoEntity> productos = this.productoService.getAll();
		

			if (productos == null || productos.isEmpty()) {
				return super.getNotContentResponseEntity();
			}
			
			MensajeResponse response= 	MensajeResponse
										.builder()
										.codigo(Constantes.CODIGO_EXITO)
										.mensaje(Constantes.MSG_EXITO_CONS+ " - " +productos.size()+ " registros")
										.data(productos)
										.build();
										
			return ResponseEntity.ok(response);

		} catch (ServiceException e) {
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@Operation(summary = "Obtener información de un producto por su identificador")
	@GetMapping("/{id}")
	public ResponseEntity<Object> findById(@PathVariable Long id) {
		if (id <= 0) {

			MensajeResponse msg = MensajeResponse.builder().codigo(0).mensaje("El id debe ser positivo").build();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msg);
		}

		try {
			ProductoEntity producto = this.productoService.findById(ProductoEntity.builder().id(id).build()).orElse(null);
			if (producto == null) {
				return ResponseEntity.noContent().build();
			}
			return ResponseEntity.ok(producto);

		} catch (ServiceException e) {
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

	@Operation(summary = "Obtiene todo los producto que coincidan por el nombre")
	@GetMapping("/by-nombre")
	public ResponseEntity<List<ProductoEntity>> findLikeNombre(@RequestParam String descripcion) {
		try {
			List<ProductoEntity> productos = this.productoService.findByLikeNombre(descripcion);
			if (productos == null || productos.isEmpty()) {
				return ResponseEntity.noContent().build();
			}
			return ResponseEntity.ok(productos);

		} catch (ServiceException e) {
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@Operation(summary = "Registrar un producto")
	@PostMapping
	public ResponseEntity<Object> insert(@RequestBody @Validated ProductoEntity type, BindingResult result) {

		if (result.hasErrors()) {
			String msgErr = super.formatMapMessage(result);
			log.info("msgErr " + msgErr);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, msgErr);
		}

		try {

			ProductoEntity oProducto = this.productoService.insert(type);

			if (oProducto == null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(oProducto);
			}

			return ResponseEntity.status(HttpStatus.CREATED).body(oProducto);

		} catch (ServiceException e) {
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@Operation(summary = "Actualizar un producto")
	@PutMapping("/{id}")
	public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody @Valid ProductoEntity type,
			BindingResult result) {
		
		type.setId(id);
		if (result.hasErrors()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, super.formatMapMessage(result));
		}
		try {
			ProductoEntity oProducto = this.productoService.update(type);
			if (oProducto == null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(oProducto);
			}
			return ResponseEntity.ok(oProducto);

		} catch (ServiceException e) {
			log.error(e.getMessage());
			String argMsg[] =e.getMessage().split(":");
			MensajeResponse msg = MensajeResponse.builder().codigo(0).mensaje(argMsg[1]).build();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(msg);
		}
	}
	
	@Operation(summary = "Eliminar un producto")
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(@PathVariable Long id) {
		try {
			ProductoEntity oProducto = this.productoService.delete(ProductoEntity.builder().id(id).build());
			if (oProducto == null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(oProducto);
			}
			
			return ResponseEntity.ok(oProducto);
		} catch (ServiceException e) {
			String argMsg[] =e.getMessage().split(":");
			MensajeResponse msg = MensajeResponse.builder().codigo(0).mensaje(argMsg[1]).build();
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(msg);
		}
	}
	

}
