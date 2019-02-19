package es.daw.bibliografia.book;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Autor {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@ManyToMany(mappedBy = "autores")
	private List<Obra> obras;

	private String nombre, url_foto, fecha_nac, fecha_def;

	/**
	 * This method is the basic constructor for the class
	 */
	public Autor() {
		super();
	}

	/**
	 * @param nombre Author's name
	 * @param url_foto Photo's URL
	 * @param fecha_nac Birthdate
	 * @param fecha_def Date of death
	 */
	public Autor(String nombre, String url_foto, String fecha_nac, String fecha_def) {
		super();
		this.nombre = nombre;
		this.url_foto = url_foto;
		this.fecha_nac = fecha_nac;
		this.fecha_def = fecha_def;
	}

	/**
	 * @return Author's name
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Change the author's name
	 * @param nombre Author's name
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return Photo's URL
	 */
	public String getUrl_foto() {
		return url_foto;
	}

	/**
	 * Change the photo's URL
	 * @param url_foto Photo's URL
	 */
	public void setUrl_foto(String url_foto) {
		this.url_foto = url_foto;
	}

	/**
	 * @return Birthdate
	 */
	public String getFecha_nac() {
		return fecha_nac;
	}

	/**
	 * Change the birthdate
	 * @param fecha_nac Birthdate
	 */
	public void setFecha_nac(String fecha_nac) {
		this.fecha_nac = fecha_nac;
	}

	/**
	 * @return Date of death
	 */
	public String getFecha_def() {
		return fecha_def;
	}

	/**
	 * Change the date of death 
	 * @param fecha_def Date of death
	 */
	public void setFecha_def(String fecha_def) {
		this.fecha_def = fecha_def;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Autor [id=" + id + ", nombre=" + nombre + "]";
	}

	/**
	 * @return ID of the object
	 */
	public long getId() {
		return id;
	}

	/**
	 * Set new ID for the object
	 * @param id
	 */
	public void setId(long id) {
		this.id = id;
	}

}
