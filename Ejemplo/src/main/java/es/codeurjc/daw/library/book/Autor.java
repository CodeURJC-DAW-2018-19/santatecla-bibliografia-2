package es.codeurjc.daw.library.book;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;




@Entity
public class Autor {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id = -1;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Cita cita;
	
	private String nombre;
	
	public Autor() {}

	public Autor(String nombre) {
		super();
		this.nombre = nombre;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "Autor [id=" + id + ", nombre=" + nombre + "]";
	}
	
	public void setCita(Cita cita) {
		this.cita=cita;
	}
	
	public String getCita() {
		return cita.getContenido();
	}
}
