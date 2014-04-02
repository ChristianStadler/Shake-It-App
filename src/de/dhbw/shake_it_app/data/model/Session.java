package de.dhbw.shake_it_app.data.model;

public class Session extends Object {

	private int id, locationID, userID;
	private double overallShakeIndex, currentShakeIndex;

	public Session() {}
	
	public Session(int id, int locationID, int userID, double overallShakeIndex, double currentShakeIndex) {
		setID(id);
		setLocationID(locationID);
		setUserID(userID);
		setOverallShakeIndex(overallShakeIndex);
		setCurrentShakeIndex(currentShakeIndex);
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
	
	public double getOverallShakeIndex() {
		return overallShakeIndex;
	}
	
	public double getCurrentShakeIndex() {
		return currentShakeIndex;
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
	
	public void setOverallShakeIndex(double overallShakeIndex) {
		this.overallShakeIndex = overallShakeIndex;
	}
	
	public void setCurrentShakeIndex(double currentShakeIndex) {
		this.currentShakeIndex = currentShakeIndex;
	}
	
}