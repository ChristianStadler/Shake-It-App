package de.dhbw.shake_it_app;

import java.util.ArrayList;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import de.dhbw.shake_it_app.data.DataProvider;
import de.dhbw.shake_it_app.data.KeyValue;
import de.dhbw.shake_it_app.data.model.Location;
import de.dhbw.shake_it_app.data.model.Session;
import de.dhbw.shake_it_app.data.operator.DataOperator;

 
public class MainScreen extends Fragment {
	
	private TextView textViewFilter, textViewName, textViewClubs;
	private EditText editTextName;
	private ListView ListViewClubListe;
	private ImageButton buttonSuche;
	private View v;
	private String clubName;
	private int eingabeAktuellerShakeIndex, eingabeDurchschnShakeIndex;
	private SeekBar seekBarAktShake, seekBarDurchschnShake;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		// Retrieving the currently selected item number
		int position = getArguments().getInt("position");
		
		// List of rivers
		String[] menus = getResources().getStringArray(R.array.menus);

		// Creating view corresponding to the fragment
		v = inflater.inflate(R.layout.main_screen, container, false);
//		addListenerOnSpinnerItemSelection();

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
		
		clubName = editTextName.getText().toString();
		
//		textViewStadtteil = (TextView) v.findViewById(R.id.textViewStadtteil);
//		textViewStadtteil.setText("Stadtteil");
	    
	    //Eingabe des Aktuellen Indize
	    seekBarAktShake = (SeekBar) v.findViewById(R.id.SeekBarAktShake);
//	    eingabeAktuellerShakeIndex = SeekBarAktShake.getProgress();
	    seekBarAktShake.setMax(100);
	   
	    //Eingabe des Durchschnittlichen Indize zur Suche
	    seekBarDurchschnShake = (SeekBar) v.findViewById(R.id.seekBarDurchschnShake);
//	    eingabeDurchschnShakeIndex = seekBarDurchschnShake.getProgress();
	    seekBarDurchschnShake.setMax(100);
	    
	    //SuchButton mit Erstellung der ListView
	    buttonSuche = (ImageButton) v.findViewById(R.id.buttonSuche);
	    buttonSuche.setOnClickListener(new View.OnClickListener() {
	        public void onClick(View v) {
		    	//Hier Daten nach den angegeben Suchparametern aus der DB lesen
	    	    eingabeAktuellerShakeIndex = seekBarAktShake.getProgress();
	    	    eingabeDurchschnShakeIndex = seekBarDurchschnShake.getProgress();
//	    	    textViewStadtteil.setText("Stadtteil");
	    	    textViewName.setText("Name");
	    	    clubName = editTextName.getText().toString();
	    	    ArrayList<MainScreen_Club_Item> image_details = getListData();
	    	    ListViewClubListe.setAdapter(new MainScreenCustomListAdapter(getActivity(), image_details));

//	        	new AsyncClass().execute();
	        }
	    });

		//�berschrift der ListView
		textViewClubs = (TextView) v.findViewById(R.id.textViewClubs);
		textViewClubs.setText("Clubs");
		

		//ListView initialisiern und Eventhandler
		ListViewClubListe = (ListView) v.findViewById(R.id.ListViewClubListe);
		ListViewClubListe.setItemsCanFocus(true);
		ListViewClubListe.setFocusable(false);
		ListViewClubListe.setFocusableInTouchMode(false);
		ListViewClubListe.setClickable(false);
		
        ListViewClubListe.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Object o = ListViewClubListe.getItemAtPosition(position);
                MainScreen_Club_Item club_item = (MainScreen_Club_Item) o;
	        	Main main = (Main)getActivity();
//	        	System.out.println("ClubItem: "+club_item.getClubName()+ " - KeyValue: " + KeyValue.getInstance().getClubName());
            	if (KeyValue.getInstance().getAmShaken()==false) {
               //     Toast.makeText(getActivity(), "Selected :" + " " + club_item, Toast.LENGTH_LONG).show();

    	        	main.changeView(1);
    	        	KeyValue.getInstance().setClubName(club_item.getClubName());
    	        	KeyValue.getInstance().setClubId(club_item.getClubId());
				}
            	else if(club_item.getClubName().equals(KeyValue.getInstance().getClubName())) {
    	        	main.changeView(1);
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

	    
	    private ArrayList<MainScreen_Club_Item> getListData() {
	    	double cumulatedValuesOverall = 0;
	    	double valueCurrent = 0;
			int amountValuesOverall = 0;
	    	
	    	Location[] location = (Location[]) DataProvider.get().getModel(DataProvider.Location);
	    	Session[] sessions =(Session[]) DataProvider.get().getModel(DataProvider.Session);
	        ArrayList<MainScreen_Club_Item> results = new ArrayList<MainScreen_Club_Item>();

	        
	        for(int i = 0; i<location.length; i++){
	        	cumulatedValuesOverall = 0;
	        	valueCurrent = 0;
	        	amountValuesOverall = 0;
	        	for(int x = 0; x < sessions.length; x++ ){
	        		if(sessions[x].getLocationID() == location[i].getID()){
	        			if(!sessions[x].getIsActive()){
		        			cumulatedValuesOverall+=sessions[x].getOverallShakeIndex();
		    				amountValuesOverall++;
	        			}else{
	        				valueCurrent=sessions[x].getCurrentShakeIndex();
	        			}
	        		}
	        	}
	        	int CurrentLocationIndex = (int) Math.round(valueCurrent);
	        	int OverallLocationIndex = (int) Math.round(cumulatedValuesOverall/amountValuesOverall);
	        	
	        	if (eingabeAktuellerShakeIndex <= CurrentLocationIndex && eingabeDurchschnShakeIndex <= OverallLocationIndex && (clubName.equals(location[i].getName()) || clubName.equals("")) )
	        	{
				      MainScreen_Club_Item clubItem = new MainScreen_Club_Item();
				      clubItem.setClubName(location[i].getName());
				      clubItem.setClubId(location[i].getID());
				      clubItem.setAktClubIndexe(CurrentLocationIndex);
				      clubItem.setAvgClubIndex(OverallLocationIndex);
				      results.add(clubItem); 
	        	}
	        }
	        for(int i = 0; i < results.size(); i++){
	        	for(int x = 0; x < results.size()-1; x++){
	        		if(results.get(x).getAktClubIndex() < results.get(x+1).getAktClubIndex()){
	        			MainScreen_Club_Item clubTemp = results.get(x);
	        			results.set(x, results.get(x+1));
	        			results.set(x+1, clubTemp);
	        		}
	        	}
	        	for(int x = 0; x < results.size()-1; x++){
	        		if(results.get(x).getAktClubIndex() == results.get(x+1).getAktClubIndex()){
	        			if(results.get(x).getAvgClubIndex() < results.get(x+1).getAvgClubIndex()){
		        			MainScreen_Club_Item clubTemp = results.get(x);
		        			results.set(x, results.get(x+1));
		        			results.set(x+1, clubTemp);
	        			}
	        		}
	        	}
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
	    
	    public static void writeSomething(Location location) {
	    	System.out.println(location.getName());
	    }
}