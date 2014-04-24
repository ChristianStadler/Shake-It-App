package de.dhbw.shake_it_app;

import java.util.ArrayList;

import de.dhbw.shake_it_app.data.DataProvider;
import de.dhbw.shake_it_app.data.KeyValue;
import de.dhbw.shake_it_app.data.model.Location;
import de.dhbw.shake_it_app.data.model.User;
import de.dhbw.shake_it_app.data.operator.DataOperator;
import android.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

 
public class MainScreen extends Fragment {
	
	private TextView textViewFilter, textViewName, textViewClubs, textViewStadtteil, textViewAktShake;
	private EditText editTextName;
	private Spinner spinnerStadtteil;
	private ListView ListViewClubListe;
	private ImageButton buttonSuche;
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
		
		//ShakeAnalyser shakeAnalyser = new ShakeAnalyser((Main)getActivity());
		
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
	    
	    //SuchButton mit Erstellung der ListView
	    buttonSuche = (ImageButton) v.findViewById(R.id.buttonSuche);
	    buttonSuche.setOnClickListener(new View.OnClickListener() {
	        public void onClick(View v) {
		    	//Hier Daten nach den angegeben Suchparametern aus der DB lesen
//		        ArrayList<MainScreen_Club_Item> image_details = getListData();
	        	new AsyncClass().execute();
	        }
	    });

		//�berschrift der ListView
		textViewClubs = (TextView) v.findViewById(R.id.textViewClubs);
		textViewClubs.setText("Clubs");
		
		/*
		//Button um auf ClubSeite zu kommen.
		imageButtonWeiterClub = (ImageButton) v.findViewById(R.id.imageButtonWeiterClub);
	    imageButtonWeiterClub.setOnClickListener(new View.OnClickListener() {
	        @Override
	        public void onClick(View v) {
	        	Main main = (Main)getActivity();
	        	main.changeView(1);
	        }
	    });
	    */
		//ListView initialisiern und Eventhandler
		ListViewClubListe = (ListView) v.findViewById(R.id.ListViewClubListe);
		ListViewClubListe.setItemsCanFocus(true);
		ListViewClubListe.setFocusable(false);
		ListViewClubListe.setFocusableInTouchMode(false);
		ListViewClubListe.setClickable(false);
		
        ListViewClubListe.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
            	if (KeyValue.getInstance().getAmShaken()==false) {
                    Object o = ListViewClubListe.getItemAtPosition(position);
                    MainScreen_Club_Item club_item = (MainScreen_Club_Item) o;
               //     Toast.makeText(getActivity(), "Selected :" + " " + club_item, Toast.LENGTH_LONG).show();
    	        	Main main = (Main)getActivity();
    	        	main.changeView(1);
    	        	KeyValue.getInstance().setClubName(club_item.getClubName());
    	        	KeyValue.getInstance().setClubId(club_item.getClubId());
				}
            	else {
					Toast.makeText(
							getActivity(),
							 "Du bist schon in einem anderen Club am Shaken!",
						    Toast.LENGTH_SHORT).show();
				}

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
	                /*
	            	if(selectedStadtteil != null){
	                    Toast.makeText(parent.getContext(), "Stadtteil, dass du ausgew�hlt hast: " + selectedItem,
	                    Toast.LENGTH_LONG).show();
	                }
	                */
	                selectedStadtteil = selectedItem;
	                break;
	            }
	        }
	  	  public void onNothingSelected(AdapterView<?> parent) {
	          // Do nothing.
	      }
     
	}

	    
	    private ArrayList<MainScreen_Club_Item> getListData() {
	    	Location[] location = (Location[]) DataProvider.get().getModel(DataProvider.Location);
	        ArrayList<MainScreen_Club_Item> results = new ArrayList<MainScreen_Club_Item>();

	        int i = location.length;
	        while(i>0)
	        {
		      MainScreen_Club_Item clubItem = new MainScreen_Club_Item();
		      clubItem.setClubName(location[i-1].getName());
		      clubItem.setClubId(location[i-1].getID());
		      clubItem.setAktClubIndexe(DataOperator.get().returnCurrLocationIndex(location[i-1].getID()));
		      clubItem.setAvgClubIndex(DataOperator.get().returnOverallLocationIndex(location[i-1].getID()));
		      results.add(clubItem);
	          i--;
	        }  
	        
	        return results;
	    }
	    
	    public void fillList(Location[] location){
	        ArrayList<MainScreen_Club_Item> results = new ArrayList<MainScreen_Club_Item>();

	        int i = location.length;
	        while(i>0)
	        {
		      MainScreen_Club_Item clubItem = new MainScreen_Club_Item();
		      clubItem.setClubName(location[i-1].getName());
		      clubItem.setClubId(location[i-1].getID());
		      clubItem.setAktClubIndexe(DataOperator.get().returnCurrLocationIndex(location[i-1].getID()));
		      clubItem.setAvgClubIndex(DataOperator.get().returnOverallLocationIndex(location[i-1].getID()));
		      results.add(clubItem);
	          i--;
	        }  
	        ListViewClubListe.setAdapter(new MainScreenCustomListAdapter(getActivity(), results));
	    }
}