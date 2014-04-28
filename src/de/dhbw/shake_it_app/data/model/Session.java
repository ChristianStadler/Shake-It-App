package de.dhbw.shake_it_app.data.model;

public class Session extends Object {

	private long id, locationID, userID;
	private double overallShakeIndex, currentShakeIndex;
	private boolean isActive;
	
	public Session(long id, long locationID, long userID, double overallShakeIndex, double currentShakeIndex, boolean isActive) {
		setID(id);
		setLocationID(locationID);
		setUserID(userID);
		setOverallShakeIndex(overallShakeIndex);
		setCurrentShakeIndex(currentShakeIndex);
		setIsActive(isActive);
	}
	
	/* GETTER */
	public long getID() {
		return this.id;
	}
	
	public long getLocationID() {
		return this.locationID;
	}
	
	public long getUserID() {
		return this.userID;
	}
	
	public double getOverallShakeIndex() {
		return this.overallShakeIndex;
	}
	
	public double getCurrentShakeIndex() {
		return this.currentShakeIndex;
	}
	
	public boolean getIsActive() {
		return this.isActive;
	}
	
	/* SETTER */
	public void setID(long id) {
		this.id = id;
	}
	
	public void setLocationID(long locationID) {
		this.locationID = locationID;
	}
	
	public void setUserID(long userID) {
		this.userID = userID;
	}
	
	public void setOverallShakeIndex(double overallShakeIndex) {
		this.overallShakeIndex = overallShakeIndex;
	}
	
	public void setCurrentShakeIndex(double currentShakeIndex) {
		this.currentShakeIndex = currentShakeIndex;
	}
	
	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}
	
}