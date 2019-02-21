package es.daw.bibliografia.user;

public class Tabs {
	private String url;
	private boolean active;
	
	public Tabs(String url, boolean active) {
		this.url = url;
		this.active=active;
	}
	public boolean isActive() {
		return active;
	}
	public void activeTab() {
		this.active=true;
	}
	
	public void inactiveTab() {
		this.active=false;
	}
	
}
