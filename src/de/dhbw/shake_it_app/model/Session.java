package de.dhbw.shake_it_app.model;

import java.sql.Timestamp;

public class Session extends Object {

	private int id, locationID, userID;
	private Timestamp checkInTime;

	public Session() {}
	
	public Session(int id, int locationID, int userID, Timestamp checkInTime) {
		setID(id);
		setLocationID(locationID);
		setUserID(userID);
		setCheckInTime(checkInTime);
	}
	
	/* GETTER */
	public int getID() {
		return id;
	}
	
	public int getLocationID() {
		return locationID;
	}
	
	public int getUserID() {
		return userID;
	}
	
	public Timestamp getCheckInTime() {
		return checkInTime;
	}
	
	/* SETTER */
	public void setID(int id) {
		this.id = id;
	}
	
	public void setLocationID(int locationID) {
		this.locationID = locationID;
	}
	
	public void setUserID(int userID) {
		this.userID = userID;
	}
	
	public void setCheckInTime(Timestamp checkInTime) {
		this.checkInTime = checkInTime;
	}
	
}