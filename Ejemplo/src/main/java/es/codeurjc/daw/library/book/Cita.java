package es.codeurjc.daw.library.book;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Cita {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id = -1;
	
	@OneToOne(mappedBy="cita")
	private Autor autor;
	
	private String contenido;
	
	public Cita() {
		
	}
	
	public Cita (String contenido) {
		this.contenido=contenido;
	}
	
	public String getContenido() {
		return this.contenido;
	}

}
