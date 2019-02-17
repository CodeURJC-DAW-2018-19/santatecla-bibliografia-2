package dataBase.obra;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Obra {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id; 
	
	private String title; 
	private String URL; 
	private String date;  //CHANGE TO CALENDAR 
	private String editorial; 
	private String URLEditorial; 
	
	public Obra(){}
	
	public Obra(String title, String uRL, String date, String editorial, String uRLEditorial) {
		super();
		this.title = title;
		URL = uRL;
		this.date = date;
		this.editorial = editorial;
		URLEditorial = uRLEditorial;
	}

	@Override
	public String toString() {
		return "Obra [id=" + id + ", title=" + title + ", URL=" + URL + ", date=" + date + ", editorial=" + editorial
				+ ", URLEditorial=" + URLEditorial + "]";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getEditorial() {
		return editorial;
	}

	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}

	public String getURLEditorial() {
		return URLEditorial;
	}

	public void setURLEditorial(String uRLEditorial) {
		URLEditorial = uRLEditorial;
	}

	
	
}

	