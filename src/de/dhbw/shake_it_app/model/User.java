package de.dhbw.shake_it_app.model;

public class User {

	int ID;
	String name, eMail;
	
	public User() {}
	
	public User(int ID, String name, String eMail) {
		setID(ID);
		setName(name);
		setEMail(eMail);
	}
	
	/* GETTER */
	public int getID() {
		return ID;
	}
	
	public String getName() {
		return name;
	}
	
	public String getEMail() {
		return eMail;
	}
	
	/* SETTER */
	public void setID(int ID) {
		this.ID = ID;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setEMail(String eMail) {
		this.eMail = eMail;
	}
	
}