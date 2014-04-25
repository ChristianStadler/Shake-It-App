package de.dhbw.shake_it_app.data;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class KeyValue  {

private static KeyValue keyValue;
private static Context ctx;
public static long userID;
public static boolean amShaken = false;
public static boolean datenUserSpeichern = false;
public static String clubname;
public static long clubid;
public static String password;

public static KeyValue getInstance(){
	if(keyValue == null){
		keyValue = new KeyValue();
	}
	return keyValue;
}


public void setApplicationContext(Context ctx){
	this.ctx = ctx;
}


public void setUser(long id){
	SharedPreferences sharedPref = ctx.getSharedPreferences("shakeit", 0);
	  SharedPreferences.Editor editor = sharedPref.edit();
	  editor.putLong("userid", id);
	  editor.commit();
}

public long getUser(){
	SharedPreferences sharedPref = ctx.getSharedPreferences("shakeit", 0);
	return sharedPref.getLong("userid", userID);
}

public void setPassword(String password) {
	SharedPreferences sharedPref = ctx.getSharedPreferences("shakeit", 0);
	  SharedPreferences.Editor editor = sharedPref.edit();
	  editor.putString("password", password);
	  editor.commit();
}

public String getPassword(){
	SharedPreferences sharedPref = ctx.getSharedPreferences("shakeit", 0);
	return sharedPref.getString("password", password);
}

public void setClubName(String clubname) {
	SharedPreferences sharedPref = ctx.getSharedPreferences("shakeit", 0);
	  SharedPreferences.Editor editor = sharedPref.edit();
	  editor.putString("clubname", clubname);
	  editor.commit();
}

public String getClubName(){
	SharedPreferences sharedPref = ctx.getSharedPreferences("shakeit", 0);
	return sharedPref.getString("clubname", clubname);
}

public void setAmShaken(boolean amShaken){
	SharedPreferences sharedPref = ctx.getSharedPreferences("shakeit", 0);
	  SharedPreferences.Editor editor = sharedPref.edit();
	  editor.putBoolean("amShaken", amShaken);
	  editor.commit();
}

public boolean getAmShaken(){
	SharedPreferences sharedPref = ctx.getSharedPreferences("shakeit", 0);
//	System.out.println("Ich gebe amShaken "+ amShaken);
	return sharedPref.getBoolean("amShaken", amShaken);
}

public void setDatenUserSpeichern(boolean datenUserSpeichern){
	SharedPreferences sharedPref = ctx.getSharedPreferences("shakeit", 0);
	  SharedPreferences.Editor editor = sharedPref.edit();
	  editor.putBoolean("datenuserspeichern", datenUserSpeichern);
	  editor.commit();
		System.out.println("Ich speicher die Daten "+ datenUserSpeichern);
}

public boolean getDatenUserSpeichern(){
	SharedPreferences sharedPref = ctx.getSharedPreferences("shakeit", 0);
	System.out.println("Ich gebe Daten "+ datenUserSpeichern);
	return sharedPref.getBoolean("datenuserspeichern", datenUserSpeichern);
}




public void setClubId(long clubId) {
	SharedPreferences sharedPref = ctx.getSharedPreferences("shakeit", 0);
	  SharedPreferences.Editor editor = sharedPref.edit();
	  editor.putLong("clubid", clubId);
	  editor.commit();
	
}
public long getClubId(){
	SharedPreferences sharedPref = ctx.getSharedPreferences("shakeit", 0);
	return sharedPref.getLong("clubid", clubid);
}


public void clear() {
	setUser(0);
	setAmShaken(false);
	setDatenUserSpeichern(false);
	setClubId(0);
	setClubName(null);
	setPassword(null);
}






}