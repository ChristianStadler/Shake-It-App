package de.dhbw.shake_it_app;



import android.app.Fragment;
import android.os.Bundle;
import android.renderscript.Sampler.Value;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;



public class Rangliste extends Fragment {
	
	private TextView textSpitzname, textPunkte;
	private ImageView imageViewUser;
	private String username; 
	private int avgIndexUser;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		// Retrieving the currently selected item number
		int position = getArguments().getInt("position");

		
		// List of rivers
		String[] menus = getResources().getStringArray(R.array.menus);

		// Creating view corresponding to the fragment
		View v = inflater.inflate(R.layout.rangliste, container, false);


		// Updating the action bar title
		getActivity().getActionBar().setTitle(menus[position]);
		
		//testdaten
		username = "Tigger";
		avgIndexUser = 89;
		
		//Daten des Profils setzen
		textSpitzname = (TextView)v.findViewById(R.id.textSpitzname);
		textSpitzname.setText(username);
		
		textPunkte = (TextView)v.findViewById(R.id.textPunkte);
		textPunkte.setText(String.valueOf(avgIndexUser));
		
		imageViewUser = (ImageView)v.findViewById(R.id.imageViewUser);

		return v;
		
	}

}
