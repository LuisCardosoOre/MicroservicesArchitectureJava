package pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionguiasredis.controller;

import static pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionguiasredis.utils.Constantes.API_GUIA;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionguiasredis.domain.Guia;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionguiasredis.services.GuiaService;

@Data
@RestController
@RequestMapping(API_GUIA)
@Tag(name = "Guias", description = "Guias Redis API")
public class GuiaController {

	@Autowired
	private GuiaService guiaService;

	@Operation(summary = "Registrar una guia de salida")
	@PostMapping
	public Guia save(@RequestBody Guia guia) {
		guiaService.save(guia);
		return guia;
	}

	@Operation(summary = "Obtener todos los datos de las guias de salida")
	@GetMapping
	public List<?> list() {
		return guiaService.findAll();
	}

	@Operation(summary = "Obtener información de una guia por su identificador")
	@GetMapping("/{id}")
	public Guia getTaller(@PathVariable String id) {
		return guiaService.findById(id);
	}

	@Operation(summary = "Actualizar una guia de salida")
	@PutMapping
	public Guia update(@RequestBody Guia guia) {
		guiaService.update(guia);
		return guia;
	}

	@Operation(summary = "Generar una guia de salida con su detalle")
	@DeleteMapping("/{id}")
	public String deleteTaller(@PathVariable String id) {
		guiaService.delete(id);
		return id;
	}
}
