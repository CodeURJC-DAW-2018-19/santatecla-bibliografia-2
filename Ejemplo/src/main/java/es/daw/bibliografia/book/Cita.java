package es.daw.bibliografia.book;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Cita {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@ManyToOne
	private Obra obra;

	private String contenido;

	/**
	 * This method is the basic constructor for the class
	 */
	public Cita() {
		super();
	}

	/**
	 * @param contenido quote content
	 */
	public Cita(String contenido) {
		super();
		this.contenido = contenido;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.contenido;
	}

	/**
	 * Set only one work
	 * @param obra work to which it belongs
	 */
	public void setObra(Obra obra) {
		this.obra = obra;
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
