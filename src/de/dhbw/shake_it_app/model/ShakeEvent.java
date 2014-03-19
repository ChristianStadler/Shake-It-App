package de.dhbw.shake_it_app.model;

public class ShakeEvent {

	int ID, sessionID, locationID, shakeIndex;
	double longitude, latitude;

	public ShakeEvent() {}
	
	public ShakeEvent(int ID, int sessionID, int locationID, int shakeIndex, double longitude, double latitude) {
		setID(ID);
		setSessionID(sessionID);
		setLocationID(locationID);
		setShakeIndex(shakeIndex);
		setLongitude(longitude);
		setLatitude(latitude);
	}
	
	/* GETTER */
	public int getID() {
		return ID;
	}
	
	public int getSessionID() {
		return sessionID;
	}
	
	public int getLocationID() {
		return locationID;
	}
	
	public int getShakeIndex() {
		return shakeIndex;
	}
	
	public double getLongitude() {
		return longitude;
	}
	
	public double getLatitude() {
		return latitude;
	}
	
	/* SETTER */
	public void setID(int ID) {
		this.ID = ID;
	}
	
	public void setSessionID(int sessionID) {
		this.sessionID = sessionID;
	}
	
	public void setLocationID(int locationID) {
		this.locationID = locationID;
	}
	
	public void setShakeIndex(int shakeIndex) {
		this.shakeIndex = shakeIndex;
	}
	
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	
}