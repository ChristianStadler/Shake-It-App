package de.dhbw.shake_it_app.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.os.StrictMode;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class ModelController {
	
	private static ModelController modelController;
	private Gson gson;
	
	// DEBUGGING
	public static void startDebugging() {	
		getModelController().startInternDebugging();
	}
	
	private void startInternDebugging() {
		// START
		System.out.println("CS_START DEBUGGING");
				
		User[] user = getUser();
		System.out.println("CS_ID: "+user[0].getID());
		System.out.println("CS_Name: "+user[0].getName());
		System.out.println("CS_Email: "+user[0].getEmail());
		System.out.println("CS_Password: "+user[0].getPassword());
				
		// ENDE
		System.out.println("CS_END DEBUGGING");
	}
	
	// ModelController
	private ModelController() {
		gson = new Gson();
	}
	
	public static ModelController getModelController() {
		if(modelController == null)
			modelController = new ModelController();
		return modelController;
	}
	
	private Gson getGson() {
		return gson;
	}
	
	public boolean getConnectivityState() {
//		ConnectivityManager con = (ConnectivityManager).getSystemService(Context.CONNECTIVITY_SERVICE);
//		NetworkInfo networkInfo = con.getActiveNetworkInfo();
//		if (networkInfo != null && networkInfo.isConnected()) {
//			return true;
//		} else {
//			return false;
//		}
		return true;
	}
	
	public Location[] getLocation() {
//		String json = "[{\"id\":2805007,\"name\":\"Zimmer\",\"description\":\"Best Club in Mannheim\",\"latitude\":15.555,\"longitude\":16.33},{\"id\":2805007,\"name\":\"Zimmer\",\"description\":\"Best Club in Mannheim\",\"latitude\":15.555,\"longitude\":16.33}]";
		try {
			return getGson().fromJson(getJsonString("location"), Location[].class);
		} catch (JsonSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public Session[] getSession() {
		try {
			return getGson().fromJson(getJsonString("session"), Session[].class);
		} catch (JsonSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public ShakeEvent[] getShakeEvent() {
		try {
			return getGson().fromJson(getJsonString("shake"), ShakeEvent[].class);
		} catch (JsonSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public User[] getUser() {
		try {
			return getGson().fromJson(getJsonString("user"), User[].class);
		} catch (JsonSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	private String getJsonString(String model) throws Exception {
		String apiURL = "http://space-labs.appspot.com/repo/2855006/shakeit/api/" + model + ".sjs";	
		InputStream is = null;
		String contentAsString = null;
		
		//Workarround!!! Normalerweise sollte dies in einer BackgroundTask passieren
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		
		URL url = new URL(apiURL);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
		conn.setRequestMethod("GET");
		conn.connect();
		
		is = conn.getInputStream();
		contentAsString = convertStreamToString (is);

//		System.out.println("CS_"+contentAsString);
//		contentAsString = "[{\"id\":3685002,\"email\":\"christian.stadler@shake-it-app.de\",\"name\":\"Christian\",\"password\":\"dc647eb65e6711e155375218212b3964\"}]";
		return contentAsString;
	}
	
	public static String convertStreamToString(InputStream is) throws Exception {
	    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
	    StringBuilder sb = new StringBuilder();
	    String line = null;

	    while ((line = reader.readLine()) != null) {
	        sb.append(line);
	    }

	    is.close();

	    return sb.toString();
	}

}