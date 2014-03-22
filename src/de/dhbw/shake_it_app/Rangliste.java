package de.dhbw.shake_it_app;



import java.util.ArrayList;

import android.app.Fragment;
import android.os.Bundle;
import android.renderscript.Sampler.Value;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;



public class Rangliste extends Fragment {
	
    // Declare the UI components
    private ListView ListViewRangliste;
    private ArrayAdapter arrayAdapter;
	private TextView textSpitzname, textPunkte;
	private ImageView imageViewUser;
	
	//
	private String username;
	private int avgIndexUser;
 // Initialize the array
//	private String[] ranglisteNamen = { "Tigger", "TanzBienchen89", "HüpfMäuschen"};

	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		// Retrieving the currently selected item number
		final int position = getArguments().getInt("position");

		
		// List of rivers
		String[] menus = getResources().getStringArray(R.array.menus);

		// Creating view corresponding to the fragment
		View v = inflater.inflate(R.layout.rangliste, container, false);


		// Updating the action bar title
		getActivity().getActionBar().setTitle(menus[position]);
		
        ArrayList<Rangliste_Item> image_details = getListData();
        ListViewRangliste = (ListView) v.findViewById(R.id.ListViewRangliste);
        ListViewRangliste.setAdapter(new RanglisteCustomListAdapter(getActivity(), image_details));
        ListViewRangliste.setOnItemClickListener(new OnItemClickListener() {
			
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
                Object o = ListViewRangliste.getItemAtPosition(position);
                Rangliste_Item ranglisteItem = (Rangliste_Item) o;
                Toast.makeText(getActivity(), "Selected :" + " " + ranglisteItem, Toast.LENGTH_LONG).show();
				
			}
		});     

 
    
/*
	 
	        // Initialize the UI components
	        ListViewRangliste = (ListView) findViewById(R.id.rangliste_list_item);
	        // For this moment, you have ListView where you can display a list.
	        // But how can we put this data set to the list?
	        // This is where you need an Adapter
	 
	        // context - The current context.
	        // resource - The resource ID for a layout file containing a layout 
	                // to use when instantiating views.
	        // From the third parameter, you plugged the data set to adapter
	        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, ranglisteNamen);
	 
	        // By using setAdapter method, you plugged the ListView with adapter
	        ListViewRangliste.setAdapter(arrayAdapter);
*/	        
	        
		/*
		//testdaten
		username = "Tigger";
		avgIndexUser = 89;
		
		//Daten des Profils setzen
		textSpitzname = (TextView)v.findViewById(R.id.textSpitzname);
		textSpitzname.setText(username);
		
		textPunkte = (TextView)v.findViewById(R.id.textPunkte);
		textPunkte.setText(String.valueOf(avgIndexUser));
		
		imageViewUser = (ImageView)v.findViewById(R.id.imageViewUser);
		*/
		return v;
		
	}
 
	    private ArrayList<Rangliste_Item> getListData() {
	        ArrayList <Rangliste_Item> results = new ArrayList<Rangliste_Item>();
	        Rangliste_Item ranglisteData = new Rangliste_Item();
	        
	        ranglisteData.setUsername("Tigger");
	        ranglisteData.setAvgIndexUser(89);
	        results.add(ranglisteData);
	        
	        ranglisteData.setUsername("Tanzbienchen");
	        ranglisteData.setAvgIndexUser(90);
	        results.add(ranglisteData);
	        
	        ranglisteData.setUsername("HüpfMäuschen");
	        ranglisteData.setAvgIndexUser(71);
	        results.add(ranglisteData);
	        
	        return results;
	    }
	
	
	


}
