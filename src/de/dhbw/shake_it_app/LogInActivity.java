package de.dhbw.shake_it_app;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.Toast;

public class LogInActivity extends Fragment{
    private Button login;
    private EditText Username;
    private EditText Password;
    private CheckedTextView changeid;
    public SQLiteDatabase sampleDB;
    public String COLUMN_ID="_id";
    public String COLUMN1="username";
    public String COLUMN2="password";
    public String TABLE_NAME="Androdata";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		// Retrieving the currently selected item number
		int position = getArguments().getInt("position");

		
		// List of rivers
		String[] menus = getResources().getStringArray(R.array.menus);

		// Creating view corresponding to the fragment
		View v = inflater.inflate(R.layout.login, container, false);


		// Updating the action bar title
		getActivity().getActionBar().setTitle(menus[position]);

		return v;
	}
}
	


