package de.dhbw.shake_it_app.data.model;

public class Location extends Object {

	private long id;
	private String name, description;
	private double longitude, latitude;
	
	public Location(long id, String name, String description, double longitude, double latitude) {
		setID(id);
		setName(name);
		setDescription(description);
		setLongitude(longitude);
		setLatitude(latitude);
	}
	
	/* GETTER */
	public long getID() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public double getLongitude() {
		return this.longitude;
	}
	
	public double getLatitude() {
		return this.latitude;
	}
	
	/* SETTER */
	public void setID(long id) {
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