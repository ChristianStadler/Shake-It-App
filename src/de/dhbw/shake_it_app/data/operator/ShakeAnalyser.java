package de.dhbw.shake_it_app.data.operator;

import de.dhbw.shake_it_app.Main;
import de.dhbw.shake_it_app.data.DataProvider;
import de.dhbw.shake_it_app.data.model.Session;
import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class ShakeAnalyser implements SensorEventListener{
	
	private static ShakeAnalyser shakeAnalyser;
	private long timeStart, sessionID, locationID, userID;
	private SensorManager sensorManager;
	private Sensor sensor;
	private double sessionIndex = 0;
	private int convertedValue, arrayCounter = 0, convertedSessionIndex, amountValues, counter;
	private final int maxIndex = 30;
	private boolean indexTooHigh;
	private double[] last10Values = new double[10];
		
	private ShakeAnalyser(Activity activity){
		Main main = (Main) activity;
		this.sensorManager = main.getSensorManager();
		sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
	}
	
	public static ShakeAnalyser getShakeAnalyser(Activity activity){
		if(shakeAnalyser == null) shakeAnalyser = new ShakeAnalyser(activity);
		return shakeAnalyser;
	}
	
	public void startShakeAnalyser(long clubID, long userID){
		locationID = clubID;
		this.userID = userID;
		sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
		timeStart = System.currentTimeMillis();
	}
	
	public void stopShakeAnalyser(){
		sensorManager.unregisterListener(this);
	}
	
	public int returnCurrentIndex(){
		double cummulatedValues = 0;
		for(int i = 0; i<10; i++){
			cummulatedValues += last10Values[i];
		}
		if (amountValues <10){
			 return convertToIndex(cummulatedValues/amountValues);
		 }
		return convertToIndex(cummulatedValues / 10);
	}
	
	private int convertToIndex(double unconvertedValue){
		convertedValue = (int)((unconvertedValue / maxIndex) * 100);
		return convertedValue;
	}
	
	public int[] calcTimeDanced(){
		long millis = System.currentTimeMillis() - timeStart;
		int[] dancedTime = new int[3];
		dancedTime[0] = (int) (millis / 3600000);
		dancedTime[1] = (int) ((millis - 3600000 * dancedTime[0])/60000);
		dancedTime[2] = (int) ((millis - (3600000 * dancedTime[0] + 60000 * dancedTime[1]))/1000);
		
		DataProvider.get().updateModel(DataProvider.Session, new Session(sessionID, locationID, userID, getConvertedSessionIndex(), returnCurrentIndex(), false));
		return dancedTime;
	}
	
	private void calcSessionIndex(double lastValue){
		sessionIndex =(sessionIndex - (sessionIndex - lastValue) / amountValues);
		convertedSessionIndex = convertToIndex(sessionIndex);
		counter++;
		if(counter == 10){
			pushToDatabase();
			counter = 0;
			}
		}
	
	private void pushToDatabase(){
		if(sessionID == 0){
			sessionID = DataProvider.get().createModel(DataProvider.Session, new Session(sessionID, locationID, userID, getConvertedSessionIndex(), returnCurrentIndex(), true));
		}
		else{
			DataProvider.get().updateModel(DataProvider.Session, new Session(sessionID, locationID, userID, getConvertedSessionIndex(), returnCurrentIndex(), true));
		}
	}
	
	public int getConvertedSessionIndex(){
		return convertedSessionIndex;	
	}
	
	public boolean getIndexTooHigh(){
		boolean localIndexTooHigh = indexTooHigh;
		indexTooHigh = false;
		return localIndexTooHigh;
	}
	
	public void onAccuracyChanged(Sensor arg0, int arg1) {
		
	}

	public void onSensorChanged(SensorEvent event) {
		if(Math.abs(event.values[0]) + Math.abs(event.values[1]) + Math.abs(event.values[2]) < maxIndex){
			last10Values[arrayCounter] = Math.abs(event.values[0]) + Math.abs(event.values[1]) + Math.abs(event.values[2]);
			amountValues++;
			calcSessionIndex(last10Values[arrayCounter]);
			arrayCounter++;
			if(arrayCounter == 10)arrayCounter = 0;		
		}
		else indexTooHigh = true;
	}
	
}
