package es.daw.bibliografia.book;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonView;

import es.daw.bibliografia.book.Obra.Basic;

@Entity
public class Tema {
	
	public interface Basic{}
	public interface Obras{}
	
	@JsonView(Basic.class)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@JsonView(Basic.class)
	@OneToMany
	private List<Obra> obras;

	@JsonView(Basic.class)
	private String contenido;
	
	private int numObras=0;

	public Tema() {
		super();
	}

	public int getNumObras() {
		return numObras;
	}

	public void setNumObras(int numObras) {
		this.numObras = numObras;
	}

	public Tema(String contenido) {
		super();
		this.contenido = contenido;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public void setObra(List<Obra> obras) {
		this.obras = obras;
		this.numObras=obras.size();
	}

	public List<Obra> getObras() {
		return this.obras;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
