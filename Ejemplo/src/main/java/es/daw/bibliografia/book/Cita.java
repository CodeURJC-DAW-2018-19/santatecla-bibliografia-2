package es.daw.bibliografia.book;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonView;

import es.daw.bibliografia.book.Autor.Basic;

@Entity
public class Cita {

	public interface Basic{}
	
	@JsonView(Basic.class)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@JsonView(Basic.class)
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
