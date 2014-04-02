package de.dhbw.shake_it_app.data;

import android.app.Activity;

public class KeyValue extends Activity {

private static KeyValue keyValue;
public static int userID;

public static KeyValue get(){
	if(keyValue == null){
		keyValue = new KeyValue();
	}
	return keyValue;
}

}