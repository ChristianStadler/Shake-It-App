package de.dhbw.shake_it_app.data;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class KeyValue  {

private static KeyValue keyValue;
public static long userID;
public static boolean amShaken = false;

public static KeyValue getInstance(){
	if(keyValue == null){
		keyValue = new KeyValue();
	}
	return keyValue;
}


private Context ctx;

public void setApplicationContext(Context ctx){
	this.ctx = ctx;
}


public void setUser(long id){
	SharedPreferences sharedPref = ctx.getSharedPreferences("shakeit", 0);
	  SharedPreferences.Editor editor = sharedPref.edit();
	  editor.putLong("userid", id);
	  editor.commit();
}

public int getUser(){
	SharedPreferences sharedPref = ctx.getSharedPreferences("shakeit", 0);
	return sharedPref.getInt("userid", -1);
}
public void setAmShaken(boolean amShaken){
	SharedPreferences sharedPref = ctx.getSharedPreferences("amShaken", 0);
	  SharedPreferences.Editor editor = sharedPref.edit();
	  editor.putBoolean("amShaken", amShaken);
	  editor.commit();
}

public boolean getAmShaken(){
	SharedPreferences sharedPref = ctx.getSharedPreferences("amShaken", 0);
	return sharedPref.getBoolean("amShaken", amShaken);
}


}