package de.dhbw.shake_it_app;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class ClubShake extends Fragment {
	
	private Switch switchShaken;
	private TextView textViewAvgIndexClub, textAvgIndexClubPkt, textViewAvgIndexPkt, textViewAktIndexPkt, TextTNAnzahl, textVeranstaltungName;
	private String clubName;
	private int avgIndexClub, aktIndexUser, avgIndexUser, anzahlTN;
	private ShakeAnalyser shakeAnalyser;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		// Retrieving the currently selected item number
		int position = getArguments().getInt("position");
		
		// List of rivers
		String[] menus = getResources().getStringArray(R.array.menus);

		// Creating view corresponding to the fragment
		View v = inflater.inflate(R.layout.clubshake, container, false);

		// Updating the action bar title
		getActivity().getActionBar().setTitle(menus[position]);
		
		//Testdaten
		clubName = "Tiffany";
		avgIndexClub = 89;
		aktIndexUser = 78;
		avgIndexUser = 90;
		anzahlTN = 200;
		
		//ShakeAnalyser initialisieren
		 shakeAnalyser = ShakeAnalyser.getShakeAnalyser(getActivity());
		
		//Durchschnittlicher Index des Clubs setzen
		textViewAvgIndexClub = (TextView) v.findViewById(R.id.textViewAvgIndexClub);
	//	textViewAvgIndexClub.setText("Durchschnittlicher Shake-Index im " + clubName );
		
		textAvgIndexClubPkt = (TextView) v.findViewById(R.id.textAvgIndexClubPkt);
		textAvgIndexClubPkt.setText(String.valueOf(avgIndexClub));
		
		//Indize des Users setzen:
		textViewAktIndexPkt = (TextView) v.findViewById(R.id.textViewAktIndexPkt);
		textViewAktIndexPkt.setText(String.valueOf(aktIndexUser));
		
		textViewAvgIndexPkt = (TextView) v.findViewById(R.id.textViewAvgIndexPkt);
		textViewAvgIndexPkt.setText(String.valueOf(avgIndexUser));
		
		//Schalter zum Starten des Shaken
		Switch switchShaken = (Switch) v.findViewById(R.id.switchShaken);
		switchShaken.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked==true) {
					Toast.makeText(
							getActivity(),
							 "Shake it, now!",
						    Toast.LENGTH_SHORT).show();
					 shakeAnalyser.startShakeAnalyser();
                        //hier den ShakeAnalyse starten
				}
				else if (isChecked==false) {
					Toast.makeText(
							getActivity(),
							 "Stop Shaking!",
						    Toast.LENGTH_SHORT).show();
                    //hier den ShakeAnalyse stoppen
					shakeAnalyser.stopShakeAnalyser();
				}
				else {
					
				}
				
			}
		});
		
		//Teilnehmerzahl & Veranstaltung des Clubs setzen
		TextTNAnzahl = (TextView) v.findViewById(R.id.TextTNAnzahl);
		TextTNAnzahl.setText(String.valueOf(anzahlTN)+ " Personen");
		
		textVeranstaltungName = (TextView) v.findViewById(R.id.textVeranstaltungName);
		textVeranstaltungName.setText("DanceNight");
		
		return v;
		
	}
}
