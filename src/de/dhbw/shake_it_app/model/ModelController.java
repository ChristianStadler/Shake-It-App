package de.dhbw.shake_it_app.model;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class ModelController extends HTTPController {
	
	private static ModelController modelController;
	private Gson gson;
	
	// DEBUGGING
	public static void startDebugging() {	
		ModelController.get().startInternDebugging();
	}
	
	private void startInternDebugging() {
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
	
	private ModelController() {
		gson = new Gson();
	}
	
	public static ModelController get() {
		if(modelController == null)
			modelController = new ModelController();
		return modelController;
	}
	
	private Gson getGson() {
		return gson;
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
	
	private String getResultJson(String modelURL, String requestMethod) throws Exception {	
		return convertStreamToString(getConnection(modelURL, requestMethod).getInputStream());
	}

}