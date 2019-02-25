package es.daw.bibliografia.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * This is the entity to store in database user information. It contains the
 * following basic information:
 * <ul>
 * <li>name: The name of the user. This name have to be used to logIn into the
 * service</li>
 * <li>passwordHash: The hash of the password. The password in never stored in
 * plain text to avoid information leak</li>
 * <li>roles: The roles of this user</li>
 * 
 * To check if a user can be logged into the service, this object is loaded from
 * database and password is verified. If user is authenticated, then this
 * database object is returned to the user.
 * 
 * NOTE: This class is intended to be extended by developer adding new
 * attributes. Current attributes can not be removed because they are used in
 * authentication procedures.
 */

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name; 
	private String email;

	private ArrayList<Tabs> tabs = new ArrayList<>();

	@JsonIgnore
	private String passwordHash;

	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> roles;

	public User() {

	}

	public User(String name, String email, String pass,  String... roles) {
		this.name = name;
		this.email = email; 
		this.passwordHash = new BCryptPasswordEncoder().encode(pass);
		this.roles = new ArrayList<>(Arrays.asList(roles));
	}

	public void addTab(Tabs tab) {
		this.tabs.add(tab);
	}

	public void removeTab(Tabs tab) {
		this.tabs.remove(tab);
	}

	public void deleteTabByName(String name) {
		tabs.removeIf(t -> t.getName().equalsIgnoreCase(name));
	}

	public void inactiveAllTabs() {
		for (int i = 0; i < tabs.size(); i++) {
			this.tabs.get(i).inactiveTab();
		}
	}

	public List<Tabs> getTabs() {
		return this.tabs;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}