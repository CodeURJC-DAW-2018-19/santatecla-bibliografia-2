package es.daw.bibliografia.book;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Autor {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id = -1;
	
	@OneToMany(mappedBy="autor")
	private List<Cita> citas;
	
	private String nombre,url_foto,fecha_nac,fecha_def;
	
	public Autor() {
		super();
	}

	public Autor(String nombre) {
		super();
		this.nombre = nombre;
	}
	
	public Autor(String nombre, String url_foto, String fecha_nac, String fecha_def) {
		super();
		this.nombre = nombre;
		this.url_foto = url_foto;
		this.fecha_nac = fecha_nac;
		this.fecha_def = fecha_def;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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

}
