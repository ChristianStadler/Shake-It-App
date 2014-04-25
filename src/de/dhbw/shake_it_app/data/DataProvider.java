package de.dhbw.shake_it_app.data;

import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import de.dhbw.shake_it_app.data.model.Location;
import de.dhbw.shake_it_app.data.model.Session;
import de.dhbw.shake_it_app.data.model.User;

public class DataProvider extends HTTPConnector {
	
	public static final String Location = "location";
	public static final String Session = "session";
	public static final String User = "user";
	
	public class NewEntry {
		private long new_id;
	}
	
	private static DataProvider dataProvider;
	private Gson gson;
	
	// DEBUGGING
	public void startDebugging() {
		// START
		System.out.println("CS_START DEBUGGING -----");
		//new AsyncClass().execute("Christian");
		
//		User user = new User(2, "Michael", "michael@sap.com", "e22a63fb76874c99488435f26b117e37", 80.54);
//		System.out.println("CS_"+createModel(DataProvider.User, user));
				
//		User[] users = (User[]) getModel(DataProvider.User);
//		User[] users = (User[]) getModel(DataProvider.User, "id=3755004");
//		User[] users = (User[]) getModel(DataProvider.User, "filter=password&value=dc647eb65e6711e155375218212b3964");
//		User[] users = (User[]) getModel(DataProvider.User, "sort=name");
		
//		if(users != null) {
//			for(User user : users) {
//				System.out.println("CS_ID: "+user.getID());
//				System.out.println("CS_Name: "+user.getName());
//				System.out.println("CS_Email: "+user.getEmail());
//				System.out.println("CS_Password: "+user.getPassword());
//				System.out.println("CS_-------------------------");
//			}
//		} else
//			System.out.println("CS_ARRAY IS NULL");
		
//		System.out.println("CS_+deleteModel(DataProvider.User, 3755005));
				
		
		// DELETE ALL SESSIONS
		System.out.println("CS_DELETE ALL SESSIONS");
		Session[] allSessions = (Session[]) getModel(DataProvider.Session);
		for(Session session : allSessions)
			deleteModel(DataProvider.Session, session.getID());
		
		// ENDE
		System.out.println("CS_END DEBUGGING -----");
	}
	// END DEBUGGING
	
	private DataProvider() {
		setGson(new Gson());
	}
	
	public static DataProvider get() {
		if(dataProvider == null)
			dataProvider = new DataProvider();
		return dataProvider;
	}
	
	/* GETTER */
	private Gson getGson() {
		return gson;
	}
	
	/* SETTER */
	private void setGson(Gson gson) {
		this.gson = gson;
	}
	
	// START API METHODS
	public Object[] getModel(String model, String... parameters) {
		// Modify the URL with optional parameters
		String requestURL = model + ".sjs";
		if(parameters.length > 0) {
			requestURL += "?";
			for(String parameter : parameters) {
				requestURL += parameter + "&";
			}
			requestURL = requestURL.substring(0, requestURL.length()-1);
			
		}
		
		// Define type of class in which the json is converted
		Class<?> convertClass = null;
		if(model.equals(DataProvider.Location)) convertClass = Location[].class;
		if(model.equals(DataProvider.Session)) convertClass = Session[].class;
		if(model.equals(DataProvider.User)) convertClass = User[].class;
		
		if(getConnectivityState())
			try {
				String string = getResultJson("GET", requestURL);
				if(string.startsWith("[")){
				Object[] obj = (Object[]) getGson().fromJson(getResultJson("GET", requestURL), convertClass);
				return obj;
				}else{
					Object[] obj= (Object[]) getGson().fromJson("["+getResultJson("GET", requestURL)+"]", convertClass);
					return obj;
				}
			} catch (JsonSyntaxException e) {
				System.out.println(e.toString());
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		return null;
	}
	
//	TODO
	public long createModel(String model, Object object) {
		// Modify the URL with optional parameters
		String requestURL = model + ".sjs";
		
		// Define type of class in which the json is converted
//		Class<?> convertClass = null;
//		if(model.equals(DataProvider.Location)) convertClass = Location.class;
//		if(model.equals(DataProvider.Session)) convertClass = Session.class;
//		if(model.equals(DataProvider.User)) convertClass = User.class;

		if(getConnectivityState()) {
			try {
				String jsonString = getGson().toJson(object);
				NewEntry newEntry = getGson().fromJson(getResultJson("PUT", requestURL, jsonString), NewEntry.class);
				return newEntry.new_id;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}		
		return -1;
	}
	
//	TODO
	public boolean updateModel(String model, Object object) {
		// Modify the URL with optional parameters
		String requestURL = model + ".sjs";

		if(getConnectivityState()) {
			try {
				String jsonString = getGson().toJson(object);
				if(getResultJson("POST", requestURL, jsonString).contains("{ Updated : true }"))
					return true;
				else
					return false;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public boolean deleteModel(String model, long id) {
		// Modify the URL with optional parameters
		String requestURL = model + ".sjs?id=" + id;

		if(getConnectivityState()) {
			try {
				if(getResultJson("DELETE", requestURL).contains("{ Deleted : true }"))
					return true;
				else
					return false;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	// END API METHODS
	
	private String getResultJson(String requestMethod, String requestURL, String... jsonString) throws IOException, Exception {	
		return convertStreamToString(getConnection(requestMethod, requestURL, jsonString).getInputStream());
	}

}