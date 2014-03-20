package de.dhbw.shake_it_app.data;

import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import de.dhbw.shake_it_app.data.model.Location;
import de.dhbw.shake_it_app.data.model.Model;
import de.dhbw.shake_it_app.data.model.Session;
import de.dhbw.shake_it_app.data.model.ShakeEvent;
import de.dhbw.shake_it_app.data.model.User;

public class DataProvider extends HTTPConnector {
	
	private static DataProvider dataProvider;
	private Gson gson;
	
	// DEBUGGING
	public void startDebugging() {
		// START
		System.out.println("CS_START DEBUGGING -----");
				
//		User[] users = (User[]) getModel(Model.User);
////		User[] users = (User[]) getModel("user", "id=3755004");
////		User[] users = (User[]) getModel("user", "filter=password&value=dc647eb65e6711e155375218212b3964");
////		User[] users = (User[]) getModel("user", "sort=name");
//		
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
		
		System.out.println(deleteModel(Model.User, 3755005));
				
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
		String modelURL = model + ".sjs";
		if(parameters.length > 0) {
			modelURL += "?";
			for(String parameter : parameters) {
				modelURL += parameter + "&";
			}
		}
		// Define type of class in which the json is converted
		Class<?> convertClass = null;
		if(model.equals(Model.Location)) convertClass = Location[].class;
		if(model.equals(Model.Session)) convertClass = Session[].class;
		if(model.equals(Model.ShakeEvent)) convertClass = ShakeEvent[].class;
		if(model.equals(Model.User)) convertClass = User[].class;
		
		if(getConnectivityState())
			try {
				return (Object[]) getGson().fromJson(getResultJson(modelURL, "GET"), convertClass);
			} catch (JsonSyntaxException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		return null;
	}
	
	public int createModel(String model, Object object) {
		// Modify the URL with optional parameters
		String modelURL = model + ".sjs";

		// TBD
//		if(getConnectivityState()) {
//			try {
//				String resultJsonString = getResultJson(modelURL, "POST");
//				if(resultJsonString.contains("{ Updated : true }"))
//					return 1;
//				else
//					return 0;
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}		
		return 0;
	}
	
	public boolean updateModel(String model, Object object) {
		// Modify the URL with optional parameters
		String modelURL = model + ".sjs";

		// TBD
//		if(getConnectivityState()) {
//			try {
//				String resultJsonString = getResultJson(modelURL, "POST");
//				if(resultJsonString.contains("{ Updated : true }"))
//					return true;
//				else
//					return false;
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
		return false;
	}
	
	public boolean deleteModel(String model, int id) {
		// Modify the URL with optional parameters
		String modelURL = model + ".sjs?id=" + id;

		if(getConnectivityState()) {
			try {
				String resultJsonString = getResultJson(modelURL, "DELETE");
				if(resultJsonString.contains("{ Deleted : true }"))
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
	
	private String getResultJson(String requestMethod, String modelURL) throws IOException, Exception {	
		return convertStreamToString(getConnection(requestMethod, modelURL).getInputStream());
	}

}