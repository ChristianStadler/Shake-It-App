package de.dhbw.shake_it_app.data;

import de.dhbw.shake_it_app.MainScreen;
import de.dhbw.shake_it_app.data.model.Location;
import android.os.AsyncTask;

public class AsyncClass extends AsyncTask<Void, Integer, String> {

	@Override
	protected String doInBackground(Void... params) {
		// TODO Auto-generated method stub
		return null;
	}

	protected void onPostExecute(String result) {		
		Location location = new Location(12345,"Tiffany","Description",12.555,13.456);
		Location[] locations = new Location[5];
		locations[0] = location;
		MainScreen.writeSomething(location);
//		MainScreen.fillList(locations);
	}
	
}