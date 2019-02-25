package es.daw.bibliografia.book;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Autor {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String nombre, url_foto, fecha_nac, fecha_def, url_mapa, lugar;

	public Autor() {
		super();
	}

	public Autor(String nombre) {
		super();
		this.nombre = nombre;
	}

	public Autor(String nombre, String url_foto, String url_mapa, String fecha_nac, String fecha_def, String lugar) {
		super();
		this.nombre = nombre;
		this.url_foto = url_foto;
		this.fecha_nac = fecha_nac;
		this.fecha_def = fecha_def;
		this.url_mapa = url_mapa;
		this.lugar = lugar;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	public String getUrl_foto() {
		return url_foto;
	}

	public void setUrl_foto(String url_foto) {
		this.url_foto = url_foto;
	}

	public String getFecha_nac() {
		return fecha_nac;
	}

	public void setUrl_mapa(String url_mapa) {
		this.url_mapa = url_mapa;
	}

	public String getUrl_mapa() {
		return url_mapa;
	}

	public void setFecha_nac(String fecha_nac) {
		this.fecha_nac = fecha_nac;
	}

	public String getFecha_def() {
		return fecha_def;
	}

	public void setFecha_def(String fecha_def) {
		this.fecha_def = fecha_def;
	}

	@Override
	public String toString() {
		return "Autor [id=" + id + ", nombre=" + nombre + "]";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
