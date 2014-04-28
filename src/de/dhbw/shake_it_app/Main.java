package de.dhbw.shake_it_app;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import de.dhbw.shake_it_app.data.KeyValue;

public class Main extends Activity {

	// Within which the entire activity is enclosed
	private DrawerLayout mDrawerLayout;

	// ListView represents Navigation Drawer
	private ListView mDrawerList;
	
	private SharedPreferences sharedPreferences;

	// ActionBarDrawerToggle indicates the presence of Navigation Drawer in the action bar
	private ActionBarDrawerToggle mDrawerToggle;

	// Title of the action bar
	private String mTitle = "";
	
	//
    private String[] drawerTitles;
    
    private int[] drawerIcons;
	private ImageButton cancelButton;
	private TextView textPopUp;

	//Context
	private static Context context;
	
	//Fragment
	private android.app.Fragment rFragment;

	@SuppressLint("NewApi")
	@Override 
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mTitle = getString(R.string.app_name);
		getActionBar().setTitle(mTitle);

		// Getting reference to the DrawerLayout
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

		mDrawerList = (ListView) findViewById(R.id.drawer_list);
		
		sharedPreferences = getSharedPreferences("de.dhbw.shake_it_app", Context.MODE_PRIVATE);
		KeyValue.getInstance().setAmShaken(false);
		
		context = getApplicationContext();

        // Hole die Titel aus einem Array aus der strings.xml
        drawerTitles = getResources().getStringArray(R.array.menus);

        // Setzt die Icons zu den Einträgen
            drawerIcons = new int[] {R.drawable.ic_action_search, R.drawable.ic_action_locate, R.drawable.ic_action_group, R.drawable.ic_action_person, R.drawable.ic_action_logout};

        // Bereitet die ActionBar auf den Navigation Drawer vor
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
        
		// Getting reference to the ActionBarDrawerToggle
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, R.string.drawer_open,
				R.string.drawer_close) {
			

			/** Called when drawer is closed */ 
			public void onDrawerClosed(View view) {
				getActionBar().setTitle(mTitle);
				invalidateOptionsMenu();

			}

			/** Called when a drawer is opened */
			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle("Shake-It-App");
				if (KeyValue.getInstance().getAmShaken()==true) {
					mDrawerList.getChildAt(1).findViewById(R.id.title).setEnabled(true);
					mDrawerList.getChildAt(2).findViewById(R.id.title).setEnabled(true);
				}
				else if(KeyValue.getInstance().getAmShaken()==false){
					mDrawerList.getChildAt(1).findViewById(R.id.title).setEnabled(false);
					mDrawerList.getChildAt(2).findViewById(R.id.title).setEnabled(false);
				}
				System.out.println("AmShaken in der Main onDrawerOpened: " + KeyValue.getInstance().getAmShaken());
				invalidateOptionsMenu();
			}

		};
		

		

		// Setting DrawerToggle on DrawerLayout
		mDrawerLayout.setDrawerListener(mDrawerToggle);

        // Erstellt den neuen MenuAdapter aus der Klasse MenuListAdapter
        Main_MenuListAdapter mMenuAdapter = new Main_MenuListAdapter(this, drawerTitles, drawerIcons);
        mDrawerList.setAdapter(mMenuAdapter);

		// Setting the adapter on mDrawerList
		//mDrawerList.setAdapter(adapter);

		// Enabling Home button
		getActionBar().setHomeButtonEnabled(true);

		// Enabling Up navigation
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		//Initialisierung des Startbildschirms 
        if (savedInstanceState == null) {
            changeView(0);
        }


		// Setting item click listener for the listview mDrawerList
		mDrawerList.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if (position==1&&KeyValue.getInstance().getAmShaken()==false) {
					Toast.makeText(getApplicationContext(), "Noch kein Club ausgewählt.", Toast.LENGTH_LONG).show();
				}
				if (position==2&&KeyValue.getInstance().getAmShaken()==false) {
					Toast.makeText(getApplicationContext(), "Noch kein Club ausgewählt, daher keine Rangliste verfügbar.", Toast.LENGTH_LONG).show();
				}
				else {
					// Getting an array of rivers
					String[] menuItems = getResources().getStringArray(R.array.menus);
					// Currently selected river
					mTitle = menuItems[position];

					changeView(position);
				}
				mDrawerLayout.closeDrawer(mDrawerList);

			}
		});
	}
	
	public static Context getAppContext() {
		return context;
	}
	
	public void changeView(int position) {
		if (position==4) {
			actionLogout();
		}
		else {
			rFragment = getFragment(position);

			// Creating a fragment object
			//WebViewFragment rFragment = new WebViewFragment();

			// Passing selected item information to fragment
			Bundle data = new Bundle();
			data.putInt("position", position);
			rFragment.setArguments(data);				
			
			// Getting reference to the FragmentManager
			FragmentManager fragmentManager = getFragmentManager();

			// Creating a fragment transaction
			FragmentTransaction ft = fragmentManager.beginTransaction();
			
			// Adding a fragment to the fragment transaction
			ft.replace(R.id.content_frame, rFragment);
			ft.addToBackStack(null);
			ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
			// Committing the transaction
			ft.commit();
		}

	}
	

	protected android.app.Fragment getFragment(int position) {
		// Creating a fragment object
		switch (position) {
		case 0:
			return rFragment = new MainScreen();
		case 1:
			return rFragment = new ClubShake();
		case 2:	
			return rFragment = new Rangliste();
		case 3:
			return rFragment = new Profil();	
		default:
			return rFragment = new MainScreen();
		}
		

	}

	private void actionLogout() {
		KeyValue.getInstance().clear(); 
  	  Intent nextScreen = new Intent(this, LogInActivity.class);
  	  nextScreen.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NO_HISTORY);
	  startActivity(nextScreen);
		
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		mDrawerToggle.syncState();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/** Called whenever we call invalidateOptionsMenu() */
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// If the drawer is open, hide action items related to the content view
		boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);

		return super.onPrepareOptionsMenu(menu);
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	

        
	public SensorManager getSensorManager(){
		SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		return sensorManager;
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
//	    if(keyCode == KeyEvent.KEYCODE_BACK)
//	    {
//
//	    }
	    if(keyCode == KeyEvent.KEYCODE_HOME)
	    {
	    	finish();
	    }
	    return super.onKeyDown(keyCode, event);
	}
	
	 
}
