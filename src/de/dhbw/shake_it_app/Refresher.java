package de.dhbw.shake_it_app;

import java.util.Timer;
import java.util.concurrent.CountDownLatch;

import de.dhbw.shake_it_app.data.operator.DataOperator;
import de.dhbw.shake_it_app.data.operator.ShakeAnalyser;

import android.app.Activity;
import android.app.Fragment;

public class Refresher extends Thread {
	
	private static Refresher refresher;
	private ClubShake fragment;
	private ShakeAnalyser shakeAnalyser;
	private long clubID, userID;
	
	private Refresher(ClubShake nfragment,long club, long user){
		fragment = nfragment;
		clubID = club;
		userID = user;
	}
	
	public static Refresher get(ClubShake nfragment,long club, long user){
		if(refresher == null){	
			refresher = new Refresher(nfragment, club, user);
		}
		return refresher;		
	}
	
	@Override
	public void run() {
			while(true){
				try {
					refresher.sleep(10000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				refreshData();
			}
	}

	private void refreshData() {
		int currentLocationIndex = DataOperator.get().returnCurrLocationIndex(clubID);
		int overallLocationIndex = DataOperator.get().returnOverallLocationIndex(clubID);
		int overallUserIndex = DataOperator.get().returnOverallUserIndex(userID);
		int currentUserIndex = shakeAnalyser.returnCurrentIndex();
		fragment.setData(overallLocationIndex, currentLocationIndex, overallUserIndex, currentUserIndex);
	}

}
