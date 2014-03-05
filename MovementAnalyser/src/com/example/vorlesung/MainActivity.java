package com.example.vorlesung;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.achartengine.ChartFactory;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends Activity implements SensorEventListener, OnClickListener {

	private ArrayList<DataObject> dataSet;
	private Button btnStart, btnStop;
	private boolean started;
	private SensorManager sensorMan;
	private LinearLayout layout;
	private View mChart;
	private WritableSheet sheet;
	private final int X = 0, Y = 1, Z = 2;
	private int rowCount = 0;
	private WritableWorkbook workbook;
	private File f;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataSet = new ArrayList<DataObject>();
        btnStart = (Button) findViewById(R.id.btnStart);
        btnStop = (Button) findViewById(R.id.btnStop);
        btnStart.setOnClickListener(this);
        btnStop.setOnClickListener(this);
        btnStart.setEnabled(true);
        btnStop.setEnabled(false);
        sensorMan = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        layout = (LinearLayout) findViewById(R.id.chart_container);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
	@Override
	public void onStart(){
		super.onStart();
		try {
			Context context = getApplicationContext();
			String filePath = "mnt/sdcard/output.xls";
			System.out.println(filePath);
			f = new File(filePath);
			workbook = Workbook.createWorkbook(f);
			System.out.println("absolute path: "+f.getAbsolutePath());
			sheet = workbook.createSheet("First Sheet", 0);
			Label label = new Label(X, rowCount, "X-Wert");
			sheet.addCell(label);
			label = new Label(Y, rowCount, "Y-Wert");
			sheet.addCell(label);
			label = new Label(Z, rowCount, "Z-Wert");
			sheet.addCell(label);
			} catch (IOException e) {
				System.out.println("create failed");
				e.printStackTrace();
			} catch (RowsExceededException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (WriteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		
	}
	
	 @Override
	    protected void onPause() {
	        super.onPause();
	        if (started == true) {
	            sensorMan.unregisterListener(this);
	        }
	    }

	 @Override
	    public void onClick(View v) {
//	        switch (v.getId()) {
	        if(v.getId() == R.id.btnStart){
	        	btnStart.setEnabled(false);
	            btnStop.setEnabled(true);
	            dataSet = new ArrayList<DataObject>();
	            started = true;
	    		Sensor s1 = sensorMan.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
	    		sensorMan.registerListener(this, s1, SensorManager.SENSOR_DELAY_NORMAL);
	        }
	        if(v.getId() == R.id.btnStop){
	        	btnStart.setEnabled(true);
	            btnStop.setEnabled(false);
	            started = false;
	            sensorMan.unregisterListener(this);
	            showChart();
	            try {
	            	System.out.println("workbook write");
	            	workbook.write();
	            	System.out.println("workbook close");
					workbook.close();
				} catch (WriteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            Uri U = Uri.fromFile(f);
	    		Intent i = new Intent(Intent.ACTION_SEND);
	    		i.setType("text/Message");
	    		i.putExtra(Intent.EXTRA_STREAM, U);
	    		startActivity(Intent.createChooser(i,"Email:"));
	        }
	    }
	 
	@Override
	public void onSensorChanged(SensorEvent event) {
		if (started) {
		double x = event.values[0];
        double y = event.values[1];
        double z = event.values[2];
        long timestamp = System.currentTimeMillis();
        DataObject data = new DataObject(timestamp, x, y, z);
        dataSet.add(data);
		saveToExcel(data);
		}
	}
	
	private void saveToExcel(DataObject data) {
		rowCount++;
		Number valueX = new Number(X, rowCount, data.getX());
		Number valueY = new Number(Y, rowCount, data.getY());
		Number valueZ = new Number(Z, rowCount, data.getZ());
		try {
			sheet.addCell(valueX);
			System.out.println(sheet.getCell(rowCount, X).getContents());
			sheet.addCell(valueY);
			System.out.println(sheet.getCell(rowCount, Y).getContents());
			sheet.addCell(valueZ);
			System.out.println(sheet.getCell(rowCount, Z).getContents());
		} catch (RowsExceededException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}		
	}


	private void showChart(){
		if (dataSet != null || dataSet.size() > 0) {
			long t = dataSet.get(0).getTimestamp();
            XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
 
            XYSeries xSeries = new XYSeries("X");
            XYSeries ySeries = new XYSeries("Y");
            XYSeries zSeries = new XYSeries("Z");
 
            for (DataObject data : dataSet) {
                xSeries.add(data.getTimestamp() - t, data.getX());
                ySeries.add(data.getTimestamp() - t, data.getY());
                zSeries.add(data.getTimestamp() - t, data.getZ());
            }
            dataset.addSeries(xSeries);
            dataset.addSeries(ySeries);
            dataset.addSeries(zSeries);
 
            XYSeriesRenderer xRenderer = new XYSeriesRenderer();
            xRenderer.setColor(Color.RED);
            xRenderer.setPointStyle(PointStyle.CIRCLE);
            xRenderer.setFillPoints(true);
            xRenderer.setLineWidth(1);
            xRenderer.setDisplayChartValues(false);
 
            XYSeriesRenderer yRenderer = new XYSeriesRenderer();
            yRenderer.setColor(Color.GREEN);
            yRenderer.setPointStyle(PointStyle.CIRCLE);
            yRenderer.setFillPoints(true);
            yRenderer.setLineWidth(1);
            yRenderer.setDisplayChartValues(false);
 
            XYSeriesRenderer zRenderer = new XYSeriesRenderer();
            zRenderer.setColor(Color.BLUE);
            zRenderer.setPointStyle(PointStyle.CIRCLE);
            zRenderer.setFillPoints(true);
            zRenderer.setLineWidth(1);
            zRenderer.setDisplayChartValues(false);
 
            XYMultipleSeriesRenderer multiRenderer = new XYMultipleSeriesRenderer();
            multiRenderer.setXLabels(0);
            multiRenderer.setLabelsColor(Color.RED);
            multiRenderer.setChartTitle("t vs (x,y,z)");
            multiRenderer.setXTitle("Sensor Data");
            multiRenderer.setYTitle("Values of Acceleration");
            multiRenderer.setZoomButtonsVisible(true);
            for (int i = 0; i < dataSet.size(); i++) {
            	 
                multiRenderer.addXTextLabel(i + 1, ""
                        + (dataSet.get(i).getTimestamp() - t));
            }
            for (int i = 0; i < 12; i++) {
                multiRenderer.addYTextLabel(i + 1, ""+i);
            }
            multiRenderer.addSeriesRenderer(xRenderer);
            multiRenderer.addSeriesRenderer(yRenderer);
            multiRenderer.addSeriesRenderer(zRenderer);
 
            // Getting a reference to LinearLayout of the MainActivity Layout
 
            // Creating a Line Chart
            mChart = ChartFactory.getLineChartView(getBaseContext(), dataset,
                    multiRenderer);
 
            // Adding the Line Chart to the LinearLayout
            layout.addView(mChart);
		}
	}
}