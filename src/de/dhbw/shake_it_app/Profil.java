package de.dhbw.shake_it_app;

import de.dhbw.shake_it_app.data.KeyValue;
import de.dhbw.shake_it_app.data.model.User;
import de.dhbw.shake_it_app.data.operator.DataOperator;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class Profil extends Fragment {
	
	private TextView textViewNameUser, textEmailUser;
	@SuppressWarnings("unused")
	private ImageView imageViewUser;
	private String userName, eMailUser;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		// Retrieving the currently selected item number
		int position = getArguments().getInt("position");
		
		// List of rivers
		String[] menus = getResources().getStringArray(R.array.menus);

		// Creating view corresponding to the fragment
		View v = inflater.inflate(R.layout.profil, container, false);

		// Updating the action bar title
		getActivity().getActionBar().setTitle(menus[position]);
		
		User user = DataOperator.get().returnUser(KeyValue.getInstance().getUser());
		//erste Testdaten
		userName = user.getName();
		eMailUser = user.getEmail();
		
		//Avatar User
		imageViewUser = (ImageView) v.findViewById(R.id.imageViewUser);
		
		//Username setzen
		textViewNameUser = (TextView) v.findViewById(R.id.textViewNameUser);
		textViewNameUser.setText(userName);
		
		//E-Mail setzen
		textEmailUser = (TextView) v.findViewById(R.id.textEmailUser);
		textEmailUser.setText(eMailUser);

		return v;
	}
}
