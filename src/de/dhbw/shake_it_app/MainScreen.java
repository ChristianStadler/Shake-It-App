package de.dhbw.shake_it_app;

import android.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

 
public class MainScreen extends Fragment {
	
	private TextView textViewFilter, textViewName, textViewClubs, textViewStadtteil, textViewAktShake;
	private EditText editTextName;
	private Spinner spinnerStadtteil;
	private View v;
	private String selectedStadtteil = null;
	private Editable clubName;
	private ImageButton imageButtonWeiterClub;
	private int eingabeAktuellerShakeIndex, eingabeDurchschnShakeIndex;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		// Retrieving the currently selected item number
		int position = getArguments().getInt("position");
		
		// List of rivers
		String[] menus = getResources().getStringArray(R.array.menus);

		// Creating view corresponding to the fragment
		v = inflater.inflate(R.layout.main_screen, container, false);
		addListenerOnSpinnerItemSelection();

		// Updating the action bar title
		getActivity().getActionBar().setTitle(menus[position]);
		
		// Subtitle setzen
		textViewFilter = (TextView) v.findViewById(R.id.textViewFilter);
		textViewFilter.setText("Suche");
		
		//Daten setzen und erfassen
		textViewName = (TextView) v.findViewById(R.id.textViewName);
		textViewName.setText("Name");
		
		editTextName = (EditText) v.findViewById(R.id.editTextName);
		clubName = editTextName.getText();
		
		textViewStadtteil = (TextView) v.findViewById(R.id.textViewStadtteil);
		textViewStadtteil.setText("Stadtteil");
	    
	    //Eingabe des Aktuellen Indize
	    SeekBar SeekBarAktShake = (SeekBar) v.findViewById(R.id.SeekBarAktShake);
	    eingabeAktuellerShakeIndex = SeekBarAktShake.getProgress();
	   
	    //Eingabe des Durchschnittlichen Indize zur Suche
	    SeekBar seekBarDurchschnShake = (SeekBar) v.findViewById(R.id.seekBarDurchschnShake);
	    eingabeDurchschnShakeIndex = seekBarDurchschnShake.getProgress();
	    
		//Anzeige der Ergebnisse
		textViewClubs = (TextView) v.findViewById(R.id.textViewClubs);
		textViewClubs.setText("Clubs");
		
		//Button um auf ClubSeite zu kommen.
		imageButtonWeiterClub = (ImageButton) v.findViewById(R.id.imageButtonWeiterClub);
	    imageButtonWeiterClub.setOnClickListener(new View.OnClickListener() {
	        @Override
	        public void onClick(View v) {
	        	Main main = (Main)getActivity();
	        	main.changeView(1);
	        }

	    });

		return v;
	}


	@Override
	  public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	  }
	  
	  //Stadtteil spinner 
	  public void addListenerOnSpinnerItemSelection() {
		  spinnerStadtteil = (Spinner) v.findViewById(R.id.spinnerStadtteil);
		  spinnerStadtteil.setOnItemSelectedListener(new MyOnItemSelectedListener());
		  }
	  
	    public class MyOnItemSelectedListener implements OnItemSelectedListener {
	    	 
	        public void onItemSelected(AdapterView<?> parent, View v, int pos, long id) {
	            
	            String selectedItem = parent.getItemAtPosition(pos).toString();
	            
	            //check which spinner triggered the listener
	            switch (parent.getId()) {
	            //country spinner
	            case R.id.spinnerStadtteil:
	                //make sure the country was already selected during the onCreate
	                if(selectedStadtteil != null){
	                    Toast.makeText(parent.getContext(), "Stadtteil, dass du ausgewählt hast: " + selectedItem,
	                    Toast.LENGTH_LONG).show();
	                }
	                selectedStadtteil = selectedItem;
	                break;
	            }
	        }
	  	  public void onNothingSelected(AdapterView<?> parent) {
	          // Do nothing.
	      }
     
	}
}