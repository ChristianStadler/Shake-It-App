package de.dhbw.shake_it_app;

import java.util.Timer;
import java.util.concurrent.CountDownLatch;

import android.app.Activity;
import android.app.Fragment;

public class Refresher extends Thread {
	
	private static Refresher refresher;
	private static Fragment fragment;
	
	private Refresher(){
	}
	
	public static Refresher get(Fragment nfragment){
		fragment = nfragment;
		if(refresher == null){	
			refresher = new Refresher();
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
				
			}
	}

}
