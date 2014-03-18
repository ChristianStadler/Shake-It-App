package de.dhbw.shake_it_app;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class ClubShake extends Fragment {
	
	private Switch switchShaken;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		// Retrieving the currently selected item number
		int position = getArguments().getInt("position");
		
		// List of rivers
		String[] menus = getResources().getStringArray(R.array.menus);

		// Creating view corresponding to the fragment
		View v = inflater.inflate(R.layout.clubshake, container, false);

		// Updating the action bar title
		getActivity().getActionBar().setTitle(menus[position]);
		
		Switch switchShaken = (Switch) v.findViewById(R.id.switchShaken);
		switchShaken.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked==true) {
					Toast.makeText(
							getActivity(),
							 "Ab jetzt werden deine Bewegungen aufgenommen",
						    Toast.LENGTH_SHORT).show();
                        //hier den ShakeAnalyse starten
				}
				else if (isChecked==false) {
					Toast.makeText(
							getActivity(),
							 "Ab jetzt werden deine Bewegungen NICHT mehr aufgenommen",
						    Toast.LENGTH_SHORT).show();
                    //hier den ShakeAnalyse stoppen
				}
				else {
					
				}
				
			}
		});
		
		return v;
		
	}
}
