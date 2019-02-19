package es.daw.bibliografia.book;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Cita {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@ManyToOne
	private Autor autor;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Obra obra;
	
	private String contenido;
	
	public Cita() {
		super();
	}
	
	public Cita (String contenido) {
		super();
		this.contenido=contenido;
	}
	
	public String toString() {
		return this.contenido;
	}
	
	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	public void setObra(Obra obra) {
		this.obra = obra;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
