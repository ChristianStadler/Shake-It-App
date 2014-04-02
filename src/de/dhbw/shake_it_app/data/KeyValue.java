package de.dhbw.shake_it_app.data;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class KeyValue  {

private static KeyValue keyValue;
private static Context ctx;
public static long userID;
public static boolean amShaken;

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

public int getUser(){
	SharedPreferences sharedPref = ctx.getSharedPreferences("shakeit", 0);
	return sharedPref.getInt("userid", -1);
}
public void setAmShaken(boolean amShaken){
	SharedPreferences sharedPref = ctx.getSharedPreferences("shakeit", 0);
	  SharedPreferences.Editor editor = sharedPref.edit();
	  editor.putBoolean("amShaken", amShaken);
	  editor.commit();
}

public boolean getAmShaken(){
	SharedPreferences sharedPref = ctx.getSharedPreferences("shakeit", 0);
	return sharedPref.getBoolean("amShaken", false);
}




public void setPassword(String password) {
	SharedPreferences sharedPref = ctx.getSharedPreferences("shakeit", 0);
	  SharedPreferences.Editor editor = sharedPref.edit();
	  editor.putString("password", password);
	  editor.commit();
}

public String getPassword(){
	SharedPreferences sharedPref = ctx.getSharedPreferences("shakeit", 0);
	return sharedPref.getString("password", "-1");
}

}