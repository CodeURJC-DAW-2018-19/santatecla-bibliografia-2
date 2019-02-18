package es.daw.bibliografia.book;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;




@Entity
public class Autor {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id = -1;
	
	@OneToMany(mappedBy="autor")
	private List<Cita> citas;
	
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
	
}
