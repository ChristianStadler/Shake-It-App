package de.dhbw.shake_it_app.data.model;

public class ConnectivityObject extends Object {
	
	private boolean connectivityState;
	
	public ConnectivityObject() {
		this.setConnectivityState(false);
	}

	/* GETTER */
	public boolean isConnectivityState() {
		return connectivityState;
	}

	/* SETTER */
	public void setConnectivityState(boolean connectivityState) {
		this.connectivityState = connectivityState;
	}
	
}