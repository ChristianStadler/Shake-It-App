package de.dhbw.shake_it_app;

import de.dhbw.shake_it_app.data.operator.ShakeAnalyser;

public class Refresher extends Thread {
	
	private static Refresher refresher;
	private ClubShake fragment;
	
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
			while(true){
				try {
					refresher.sleep(10000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				fragment.setData();
			}
	}

}
