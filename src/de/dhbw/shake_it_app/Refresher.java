package de.dhbw.shake_it_app;

import android.app.Activity;
import android.app.Fragment;

public class Refresher implements Runnable {
	
	private static Refresher refresher;
	
	private Refresher(){
	}
	
	public static Refresher get(){
		if(refresher == null){
			refresher = new Refresher();
		}
		return refresher;		
	}
	
	public void go(Fragment fragment){
		
	}
	@Override
	public void run() {
		
		
	}

}
