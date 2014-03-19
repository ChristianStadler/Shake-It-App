package de.dhbw.shake_it_app.model;

import java.sql.Timestamp;

public class Session {

	int ID, locationID, userID;
	Timestamp checkInTime;

	public Session() {}
	
	public Session(int ID, int locationID, int userID, Timestamp checkInTime) {
		setID(ID);
		setLocationID(locationID);
		setUserID(userID);
		setCheckInTime(checkInTime);
	}
	
	/* GETTER */
	public int getID() {
		return ID;
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
	public void setID(int ID) {
		this.ID = ID;
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