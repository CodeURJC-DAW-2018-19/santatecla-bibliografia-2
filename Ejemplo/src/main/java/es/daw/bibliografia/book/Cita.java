package es.daw.bibliografia.book;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Cita {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String contenido;

	public Cita() {
		super();
	}

	public Cita(String contenido) {
		super();
		this.contenido = contenido;
	}

	public String toString() {
		return this.contenido;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
