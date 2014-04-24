package de.dhbw.shake_it_app;

import android.app.Activity;
import android.app.Fragment;

public class Refresher extends Thread {
	
	private static Refresher refresher;
	private Fragment fragment;
	
	private Refresher(){
	}
	
	public static Refresher get(){
		if(refresher == null){
			refresher = new Refresher();
		}
		return refresher;		
	}
	
	public void start(Fragment fragment){
		this.fragment = fragment;
		start();
	}
	@Override
	public void run() {
			while(true){
				try {
					wait(10000);
					System.out.println("ich laufe!");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		}
	}

}
