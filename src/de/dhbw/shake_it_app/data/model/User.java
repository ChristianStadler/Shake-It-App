package de.dhbw.shake_it_app.data.model;

public class User extends Object {

	private long id;
	private String name, email, password;
	private double overallShakeIndex;
	
	public User(long id, String name, String email, String password, double overallShakeIndex) {
		setID(id);
		setName(name);
		setEmail(email);
		setPassword(password);
		setOverallShakeIndex(overallShakeIndex);
	}
	
	/* GETTER */
	public long getID() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public double getOverallShakeIndex() {
		return this.overallShakeIndex;
	}
	
	/* SETTER */
	public void setID(long id) {
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
	
	public void setOverallShakeIndex(double overallShakeIndex) {
		this.overallShakeIndex = overallShakeIndex;
	}
	
}