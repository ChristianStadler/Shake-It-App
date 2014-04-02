package de.dhbw.shake_it_app;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Main extends Activity {

	// Within which the entire activity is enclosed
	private DrawerLayout mDrawerLayout;

	// ListView represents Navigation Drawer
	private ListView mDrawerList;

	// ActionBarDrawerToggle indicates the presence of Navigation Drawer in the action bar
	private ActionBarDrawerToggle mDrawerToggle;

	// Title of the action bar
	private String mTitle = "";
	
	//
    private String[] drawerTitles;
    private int[] drawerIcons;
    
    public SharedPreferences sharedPreferences;

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

        // Hole die Titel aus einem Array aus der strings.xml
        drawerTitles = getResources().getStringArray(R.array.menus);
        // Setzt die Icons zu den Einträgen
        drawerIcons = new int[] {R.drawable.ic_action_search, R.drawable.ic_action_locate, R.drawable.ic_action_group, R.drawable.ic_action_person};

 
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
				invalidateOptionsMenu();
			}

		};
		

		// Setting DrawerToggle on DrawerLayout
		mDrawerLayout.setDrawerListener(mDrawerToggle);

        // Erstellt den neuen MenuAdapter aus der Klasse MenuListAdapter
        Main_MenuListAdapter mMenuAdapter = new Main_MenuListAdapter(this, drawerTitles, drawerIcons);
        mDrawerList.setAdapter(mMenuAdapter);
		// Creating an ArrayAdapter to add items to the listview mDrawerList
		//ArrayAdapter<String> adapter = new ArrayAdapter<String>(getBaseContext(), 
		//		R.layout.drawer_list_item, getResources().getStringArray(R.array.menus));

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
				if (position==1) {
					Toast.makeText(getApplicationContext(), "Noch kein Club ausgewählt", Toast.LENGTH_LONG).show();
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
	
	public void changeView(int position) {
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

		// Committing the transaction
		ft.commit();
	}
	
	public void changeView(int position, String clubName) {
		rFragment = getFragment(position);

		// Creating a fragment object
		//WebViewFragment rFragment = new WebViewFragment();

		// Passing selected item information to fragment
		Bundle data = new Bundle();
		data.putInt("position", position);
		data.putString("clubname", clubName);
		rFragment.setArguments(data);				
		
		// Getting reference to the FragmentManager
		FragmentManager fragmentManager = getFragmentManager();

		// Creating a fragment transaction
		FragmentTransaction ft = fragmentManager.beginTransaction();

		// Adding a fragment to the fragment transaction
		ft.replace(R.id.content_frame, rFragment);

		// Committing the transaction
		ft.commit();
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

	 
}
