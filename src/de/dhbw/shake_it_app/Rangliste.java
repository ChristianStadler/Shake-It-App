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
    private TextView textViewRanglisteUeberschrift;
    
    private String clubName;

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
		
		//testdaten
		clubName = "Tiffany's";
		
		textViewRanglisteUeberschrift = (TextView) v.findViewById(R.id.textViewRanglisteUeberschrift);
		textViewRanglisteUeberschrift.setText("Aktuelle Shaker im "+ clubName);
		
		//ListView
        ArrayList<Rangliste_Item> image_details = getListData();
        final ListView ListViewRangliste = (ListView) v.findViewById(R.id.ListViewRangliste);
        ListViewRangliste.setAdapter(new RanglisteCustomListAdapter(getActivity(), image_details));
        ListViewRangliste.setOnItemClickListener(new OnItemClickListener() {
			
			public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Object o = ListViewRangliste.getItemAtPosition(position);
                Rangliste_Item ranglisteItem = (Rangliste_Item) o;
                Toast.makeText(getActivity(), "Selected :" + position + " " + ranglisteItem, Toast.LENGTH_LONG).show();
				
			}
		});     

       
		return v;
		
	}
 
	    private ArrayList<Rangliste_Item> getListData() {
	        ArrayList <Rangliste_Item> results = new ArrayList<Rangliste_Item>();
	        
	        Rangliste_Item ranglisteData = new Rangliste_Item();
	        ranglisteData.setUsername("Tigger");
	        ranglisteData.setAvgIndexUser(89);
	        results.add(ranglisteData);
	        
	         ranglisteData = new Rangliste_Item();
	        ranglisteData.setUsername("Tanzbienchen");
	        ranglisteData.setAvgIndexUser(90);
	        results.add(ranglisteData);
	        
	         ranglisteData = new Rangliste_Item();
	        ranglisteData.setUsername("HüpfMäuschen");
	        ranglisteData.setAvgIndexUser(71);
	        results.add(ranglisteData);
	        
	         ranglisteData = new Rangliste_Item();
	        ranglisteData.setUsername("Lalal");
	        ranglisteData.setAvgIndexUser(83);
	        results.add(ranglisteData);
	        
	         ranglisteData = new Rangliste_Item();
	        ranglisteData.setUsername("HansWurst");
	        ranglisteData.setAvgIndexUser(83);
	        results.add(ranglisteData);
	        
	         ranglisteData = new Rangliste_Item();
	        ranglisteData.setUsername("hahaha");
	        ranglisteData.setAvgIndexUser(83);
	        results.add(ranglisteData);
	        
	        return results;
	    }
	
	
	


}
