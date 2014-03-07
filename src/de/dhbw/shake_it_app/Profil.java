package de.dhbw.shake_it_app;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Profil extends Fragment {
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		// Retrieving the currently selected item number
		int position = getArguments().getInt("position");
		
		// List of rivers
		String[] menus = getResources().getStringArray(R.array.menus);

		// Creating view corresponding to the fragment
		View v = inflater.inflate(R.layout.profil, container, false);
		TextView positionTextView = (TextView) v.findViewById(R.id.textViewPosition);
		positionTextView.setText(Integer.toString(position));

		// Updating the action bar title
		getActivity().getActionBar().setTitle(menus[position]);

		return v;
	}
}
