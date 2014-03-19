package de.dhbw.shake_it_app.model;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ModelController {

	public ModelController() throws Exception {
//		Gson gson = new Gson();
		getLocation();
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
	
	public String getLocation() throws Exception {
		InputStream is = null;
		try {
			URL url = new URL("http://space-labs.appspot.com/repo/2855006/shakeit/api/location.sjs");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				
			conn.setRequestMethod("GET");
			conn.connect();
				
			int response = conn.getResponseCode();
			is = conn.getInputStream();
				
			// Convert the InputStream into a string
			String contentAsString = convertStreamToString (is); //Nächste Folie
			
			return contentAsString;
		} finally {
			if(is != null) {
				is.close();
			}		
		}
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