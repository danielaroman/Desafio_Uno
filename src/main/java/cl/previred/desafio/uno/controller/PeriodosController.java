package cl.previred.desafio.uno.controller;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cl.previred.desafio.uno.dto.Mensaje;
import cl.previred.desafio.uno.dto.MensajeError;
import cl.previred.desafio.uno.dto.PeriodosSalidaDTO;
import cl.previred.desafio.uno.service.PeriodosService;

@RequestMapping("/api/v1")
@RestController
public class PeriodosController {
	
	@Autowired
	private PeriodosService periodosService;
	
	Logger log = LoggerFactory.getLogger(PeriodosController.class);
	
	@RequestMapping(value="/periodosPerdidos", method=RequestMethod.GET)
	public ResponseEntity<?> obtenerPeriodosPerdidos(){
		
		log.info("obtenerPeriodosPerdidos");
		
		HashMap<String,PeriodosSalidaDTO> response =  new HashMap<>();
		PeriodosSalidaDTO periodosSalida;
		try {
			periodosSalida = periodosService.obtenerPeriodosFaltantes();
			if(periodosSalida==null || periodosSalida.getFechasFaltantes().isEmpty()){
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Mensaje("No se encontraron datos de obtenerPeriodosPerdidos"));
			}
			response.put("periodosFaltantes", periodosSalida);
			
		}catch(Exception e){
			log.error("obtenerPeriodosPerdidos ", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MensajeError(e));
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(response);
		
	}
	

}
