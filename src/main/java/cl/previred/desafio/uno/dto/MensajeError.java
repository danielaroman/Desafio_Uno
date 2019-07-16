package cl.previred.desafio.uno.dto;

import java.io.Serializable;

public class MensajeError implements Serializable {

	private static final long serialVersionUID = 1L;

	public MensajeError(Exception e) {
		this.clase = e.getClass().getSimpleName();
		this.mensaje = e.getMessage();
	}

	private String clase;
	private String mensaje;

	public String getClase() {
		return clase;
	}

	public void setClase(String clase) {
		this.clase = clase;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

}
