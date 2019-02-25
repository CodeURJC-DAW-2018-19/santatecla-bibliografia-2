package es.daw.bibliografia.user;

public class Tabs {
	private String url;
	private boolean active;
	private String name;

	public Tabs(String url, String name, boolean active) {
		this.url = url;
		this.active = active;
		this.name = name;
	}

	public boolean isActive() {
		return active;
	}

	public void activeTab() {
		this.active = true;
	}

	public void inactiveTab() {
		this.active = false;
	}

	public String getName() {
		return this.name;
	}

	public String getUrl() {
		return this.url;
	}

}
