package pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionclientes.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionclientes.controller.commons.MensajeResponse;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionclientes.utils.Constantes;

public class GenericController {

	protected String formatMapMessage( BindingResult result){
        List<Map<String,String>> errors = result.getFieldErrors().stream()
                .map(err ->{
                    Map<String,String>  error =  new HashMap<>();
                    error.put(err.getField(), err.getDefaultMessage());
                    return error;
                }).collect(Collectors.toList());
      return errors.toString();

	}
	
	protected MensajeResponse getNotContent() {
		
		return MensajeResponse
				.builder()
				.codigo(Constantes.CODIGO_ALERTA)
				.mensaje(Constantes.MSG_ALERTA_CONS )
				.data(null)
				.build();
	}
	
	protected ResponseEntity<MensajeResponse> getNotContentResponseEntity(){
		
		return ResponseEntity.status(HttpStatus.OK).body(this.getNotContent());
		
	}
}
