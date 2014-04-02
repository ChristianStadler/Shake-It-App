package de.dhbw.shake_it_app.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import android.os.StrictMode;

class HTTPConnector {

	protected InputStream is;
	
	protected boolean getConnectivityState() {
//		ConnectivityManager connectivityManager = (ConnectivityManager).getSystemService(Context.CONNECTIVITY_SERVICE);
//		NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
//		if (networkInfo != null && networkInfo.isConnected()) {
//			return true;
//		} else {
//			return false;
//		}
		return true;
	}
	
	protected HttpURLConnection getConnection(String RequestMethod, String modelURL, String... jsonString) throws IOException {
		// Workaround!!!
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		
		URL url = new URL("http://space-labs.appspot.com/repo/2855006/shakeit/api/" + modelURL);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			
		
		connection.setRequestMethod(RequestMethod);
			
		if(jsonString != null) {
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setDoOutput(true);
			OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
			out.write(jsonString[0]);
			out.flush();
			out.close();
		}
		
		connection.connect();
				
		return connection;
	}
	
	protected static String convertStreamToString(InputStream is) throws Exception {
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