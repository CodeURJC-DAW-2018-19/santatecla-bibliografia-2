package es.daw.bibliografia.book;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
public class Obra {

	public interface Basic{}
	public interface Authors{}
	public interface Quotes{}
	
	@JsonView(Basic.class)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@JsonView(Basic.class)
	private String title, URL, date, editorial, url_editorial;

	@JsonView(Basic.class)
	@OneToMany
	private List<Cita> citas;

	@JsonView(Basic.class)
	@ManyToMany
	private List<Autor> autores;
	
	public Obra() {
		super();
	}

	public Obra(String title) {
		this.title = title;
	}

	public Obra(String titulo, String url_foto, String fecha, String editorial, String url_editorial) {
		super();
		this.title = titulo;
		this.URL = url_foto;
		this.date = fecha;
		this.editorial = editorial;
		this.url_editorial = url_editorial;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String titulo) {
		this.title = titulo;
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String url_foto) {
		this.URL = url_foto;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String fecha) {
		this.date = fecha;
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

	public void setAutores(List<Autor> autores) {
		this.autores = autores;
	}

	public void setCitas(List<Cita> citas) {
		this.citas = citas;
	}

	public List<Cita> getCitas() {
		return this.citas;
	}

	public List<Autor> getAutores() {
		return this.autores;
	}

}
