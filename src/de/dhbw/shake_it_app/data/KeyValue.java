package de.dhbw.shake_it_app.data;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class KeyValue  {

private static KeyValue keyValue;
public static int userID;

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


public void setUser(int id){
	SharedPreferences sharedPref = ctx.getSharedPreferences("shakeit", 0);
	  SharedPreferences.Editor editor = sharedPref.edit();
	  editor.putInt("userid", id);
	  editor.commit();
}

public int getUser(){
	SharedPreferences sharedPref = ctx.getSharedPreferences("shakeit", 0);
	return sharedPref.getInt("userid", -1);
}

}