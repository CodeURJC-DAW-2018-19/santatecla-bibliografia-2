package es.daw.bibliografia.book;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Obra {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@OneToMany(mappedBy="obra")
	private List<Cita> citas;
	
	@ManyToOne
	private Tema tema;
	
	@ManyToMany
	private List<Autor> autores;
	
	private String titulo, url_foto, fecha, editorial, url_editorial;
	
	public Obra() {
		super();
	}

	public Obra(String titulo, String url_foto, String fecha, String editorial, String url_editorial) {
		super();
		this.titulo = titulo;
		this.url_foto = url_foto;
		this.fecha = fecha;
		this.editorial = editorial;
		this.url_editorial = url_editorial;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getUrl_foto() {
		return url_foto;
	}

	public void setUrl_foto(String url_foto) {
		this.url_foto = url_foto;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getEditorial() {
		return editorial;
	}

	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}

	public String getUrl_editorial() {
		return url_editorial;
	}

	public void setUrl_editorial(String url_editorial) {
		this.url_editorial = url_editorial;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	
}
