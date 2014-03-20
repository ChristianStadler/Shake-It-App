package de.dhbw.shake_it_app.model;

public class User {

	private int id;
	private String name, email, password;
	
	public User() {}
	
	public User(int id, String name, String email, String password) {
		setID(id);
		setName(name);
		setEmail(email);
		setPassword(password);
	}
	
	/* GETTER */
	public int getID() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getPassword() {
		return password;
	}
	
	/* SETTER */
	public void setID(int id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
}