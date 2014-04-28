package de.dhbw.shake_it_app;



import java.util.ArrayList;

import de.dhbw.shake_it_app.data.DataProvider;
import de.dhbw.shake_it_app.data.KeyValue;
import de.dhbw.shake_it_app.data.model.Session;
import de.dhbw.shake_it_app.data.model.User;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;



public class Rangliste extends Fragment {
	
    // Declare the UI components
    private TextView textViewRanglisteUeberschrift;
    
    private String clubName;
    private View v2;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		// Retrieving the currently selected item number
		final int position = getArguments().getInt("position");
		Refresher.get(this);
		
		// List of rivers
		String[] menus = getResources().getStringArray(R.array.menus);

		// Creating view corresponding to the fragment
		View v = inflater.inflate(R.layout.rangliste, container, false);
		v2 = v;


		// Updating the action bar title
		getActivity().getActionBar().setTitle(menus[position]);
		
		//testdaten
		clubName = KeyValue.getInstance().getClubName();

		textViewRanglisteUeberschrift = (TextView) v.findViewById(R.id.textViewRanglisteUeberschrift);
		textViewRanglisteUeberschrift.setText("Aktuelle Shaker im "+ clubName);
		
		//ListView
        ArrayList<Rangliste_Item> image_details = getListData();
        final ListView ListViewRangliste = (ListView) v.findViewById(R.id.ListViewRangliste);
        ListViewRangliste.setAdapter(new RanglisteCustomListAdapter(getActivity(), image_details));
        

        ListViewRangliste.setOnItemClickListener(new OnItemClickListener() {
			
			@SuppressWarnings("unused")
			public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Object o = ListViewRangliste.getItemAtPosition(position);
                Rangliste_Item ranglisteItem = (Rangliste_Item) o;
//                Toast.makeText(getActivity(), "Selected :" + position + " " + ranglisteItem, Toast.LENGTH_LONG).show();
				
			}
		});     

       
		return v;
		
	}
 
	    private ArrayList<Rangliste_Item> getListData() { 
	    	User[] user = (User[]) DataProvider.get().getModel(DataProvider.User);
	    	Session[] sessions = (Session[]) DataProvider.get().getModel(DataProvider.Session);
	    	ArrayList <Rangliste_Item> results = new ArrayList<Rangliste_Item>();
	    	
	        for(int i = 0; i < user.length; i++){
		      for(int x = 0; x < sessions.length; x++){
		    	  if(sessions[x].getUserID() == user[i].getID() && sessions[x].getIsActive() && sessions[x].getLocationID() == KeyValue.getInstance().getClubId()){
		    		  Rangliste_Item ranglisteData = new Rangliste_Item();
				      ranglisteData.setUsername(user[i].getName());
				      ranglisteData.setAvgIndexUser((int) sessions[x].getOverallShakeIndex());
				      results.add(ranglisteData);
		    	  }
		      }    
	        }  
	      
	        return results;
	    }
	    
	    public void update(){
	    	if(KeyValue.getInstance().getAmShaken()==true){
	        	v2.post((new Runnable() {
	                public void run() {
	        	    	User[] user = (User[]) DataProvider.get().getModel(DataProvider.User);
	        	    	Session[] sessions = (Session[]) DataProvider.get().getModel(DataProvider.Session);
	        	    	ArrayList <Rangliste_Item> results = new ArrayList<Rangliste_Item>();
	        	    	
	        	        for(int i = 0; i < user.length; i++){
	        		      for(int x = 0; x < sessions.length; x++){
	        		    	  if(sessions[x].getUserID() == user[i].getID() && sessions[x].getIsActive() && sessions[x].getLocationID() == KeyValue.getInstance().getClubId()){
	        		    		  Rangliste_Item ranglisteData = new Rangliste_Item();
	        				      ranglisteData.setUsername(user[i].getName());
	        				      ranglisteData.setAvgIndexUser((int) sessions[x].getOverallShakeIndex());
	        				      results.add(ranglisteData);
	        		    	  }
	        		      }    
	        	        }
	        	        final ListView ListViewRangliste = (ListView) v2.findViewById(R.id.ListViewRangliste);
	        	        ListViewRangliste.setAdapter(new RanglisteCustomListAdapter(getActivity(), results));
	                }
	            }));
	        	}
	        }
}
	
