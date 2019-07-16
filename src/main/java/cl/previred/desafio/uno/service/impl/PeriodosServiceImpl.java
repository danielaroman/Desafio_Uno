package cl.previred.desafio.uno.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import cl.previred.desafio.uno.dto.PeriodosEntradaDTO;
import cl.previred.desafio.uno.dto.PeriodosSalidaDTO;
import cl.previred.desafio.uno.service.PeriodosService;
import cl.previred.util.FechasUtil;

@Service
public class PeriodosServiceImpl implements PeriodosService {
	
	@Value("${rest.service.generador.datos}")
	private String endpoint;

	@Override
	public PeriodosSalidaDTO obtenerPeriodosFaltantes() {
		PeriodosEntradaDTO periodosEntrada = obtenerPeriodosEntrada();
        List<LocalDate> listaPeriodos = FechasUtil.getDatesBetween(periodosEntrada);
        PeriodosSalidaDTO periodosFaltantes = new PeriodosSalidaDTO();
        periodosFaltantes.setFechaCreacion(periodosEntrada.getFechaCreacion().toString());
        periodosFaltantes.setFechaFin(periodosEntrada.getFechaFin().toString());
        periodosFaltantes.setFechas(getFechaString(periodosEntrada.getFechas()));
        periodosFaltantes.setFechasFaltantes(getFechaString(listaPeriodos));
        periodosFaltantes.setId(periodosEntrada.getId());

        return periodosFaltantes;
	}
	
	public List<String> getFechaString(List<LocalDate> fechas) {
		List<String> fechasFaltantes = new ArrayList<>();
		for(int i = 0; i< fechas.size(); i++) {
			fechasFaltantes.add(fechas.get(i).toString());
		}
		return fechasFaltantes;
	}
	
	public PeriodosEntradaDTO obtenerPeriodosEntrada(){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<String> httpEntity = new HttpEntity<>("body", headers);
        //String endpoint = env.getProperty("rest.service.generador.datos");
        System.out.println(endpoint);
        ResponseEntity<PeriodosEntradaDTO> response = restTemplate.exchange(
                endpoint, HttpMethod.GET, httpEntity, PeriodosEntradaDTO.class);
        return response.getBody();
    }

}
