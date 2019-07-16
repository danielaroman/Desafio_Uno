package cl.previred.util;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import cl.previred.desafio.uno.dto.PeriodosEntradaDTO;

public class FechasUtil {

	public static List<LocalDate> getDatesBetween(PeriodosEntradaDTO periodosEntrada) {

		LocalDate startDate = periodosEntrada.getFechaCreacion();
		LocalDate endDate = periodosEntrada.getFechaFin();
		List<LocalDate> exceptionDate = periodosEntrada.getFechas();

		long numOfDaysBetween = ChronoUnit.DAYS.between(startDate, endDate);
		return IntStream.iterate(0, i -> i + 1)
				.limit(numOfDaysBetween)
				.mapToObj(i -> startDate.plusDays(i))
				.filter(i -> !exceptionDate.contains(i))
				.collect(Collectors.toList());
	}
}
