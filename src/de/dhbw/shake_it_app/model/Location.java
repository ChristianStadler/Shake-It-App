package de.dhbw.shake_it_app.model;

public class Location extends Object {

	private int id;
	private String name, description;
	private double longitude, latitude;
	
	public Location() {}
	
	public Location(int id, String name, String description, double longitude, double latitude) {
		setID(id);
		setName(name);
		setDescription(description);
		setLongitude(longitude);
		setLatitude(latitude);
	}
	
	/* GETTER */
	public int getID() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public double getLongitude() {
		return longitude;
	}
	
	public double getLatitude() {
		return latitude;
	}
	
	/* SETTER */
	public void setID(int id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	
}