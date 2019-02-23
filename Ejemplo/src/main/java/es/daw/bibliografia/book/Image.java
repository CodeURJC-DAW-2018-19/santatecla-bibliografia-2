package es.daw.bibliografia.book;

public class Image {

	private String title;

	public Image(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}



	@Override
	public String toString() {
		return "title=" + title + "]";
	}

}
