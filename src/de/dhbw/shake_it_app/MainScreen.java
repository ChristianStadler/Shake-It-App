package de.dhbw.shake_it_app;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

 
public class MainScreen extends Fragment {
	
	  private Spinner spinnerStadtteil;
	  private View v;
	  private String selectedStadtteil = null;

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

		return v;
	}
	
	
	 
	  @Override
	  public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	 

	  }

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
	                    Toast.makeText(parent.getContext(), "Stadtteil, dass du ausgewählt hast " + selectedItem,
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