package de.dhbw.shake_it_app;

import de.dhbw.shake_it_app.data.KeyValue;
import de.dhbw.shake_it_app.data.operator.DataOperator;
import de.dhbw.shake_it_app.data.operator.ShakeAnalyser;

public class Refresher extends Thread {
	
	private static Refresher refresher;
	private ClubShake fragment;
	private volatile boolean go;
	private int currentLocationIndex, overallLocationIndex, overallUserIndex;
	
	private Refresher(ClubShake nfragment,long club, long user, ShakeAnalyser nshakeAnalyser){
		fragment = nfragment;
	}
	
	public static Refresher get(ClubShake nfragment,long club, long user, ShakeAnalyser shakeAnalyser){
		if(refresher == null){	
			refresher = new Refresher(nfragment, club, user, shakeAnalyser);
		}
		return refresher;		
	}
	
	@Override
	public void run() {
		go = true;
			while(go){
				try {
					refresher.sleep(5000);
					currentLocationIndex = DataOperator.get().returnCurrLocationIndex(KeyValue.getInstance().getClubId());
	        		overallLocationIndex = DataOperator.get().returnOverallLocationIndex(KeyValue.getInstance().getClubId());
	        		overallUserIndex = DataOperator.get().returnOverallUserIndex(KeyValue.getInstance().getUser());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				fragment.setData();
			}
	}
	
	public int getCurrentLocationIndex(){
		return currentLocationIndex;
	}
	
	public int getOverallLocationIndex(){
		return overallLocationIndex;
	}
	
	public int getOverallUserIndex(){
		return overallUserIndex;
	}

}
