package de.dhbw.shake_it_app;

import java.lang.reflect.Array;
import java.util.Queue;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class ShakeAnalyser implements SensorEventListener{
	
	private static ShakeAnalyser shakeAnalyser;
	private SensorManager sensorManager;
	private Sensor sensor;
	private int currentIndex, convertedValue, arrayCounter = 0;
	private final int maxIndex = 30;
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
	
	public void startShakeAnalyser(){
		sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
	}
	
	public void stopShakeAnalyser(){
		sensorManager.unregisterListener(this);
	}
	
	public int getCurrentIndex(){
		double cummulatedValues = 0;
		for(int i = 0; i<10; i++){
			cummulatedValues += last10Values[i];
		}
		currentIndex = convertToIndex(cummulatedValues / 10);
		return currentIndex;
	}
	
	private int convertToIndex(double unconvertedValue){
		convertedValue = (int)((unconvertedValue / maxIndex) * 100);
		return convertedValue;
	}
	
	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		last10Values[arrayCounter] = event.values[0] + event.values[1] + event.values[2];
		arrayCounter++;
		if(arrayCounter == 10)arrayCounter = 0;			
	}
	
}
