package de.dhbw.shake_it_app;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class ClubShake extends Fragment {
	
	private Switch switchShaken;
	private TextView textViewAvgIndexClub, textAvgIndexClubPkt, textViewAvgIndexPkt, textViewAktIndexPkt, TextTNAnzahl, textVeranstaltungName, textAktIndexClubPkt, textClubName;
	private ImageView imageClub;
	private String clubName = "Club";
	private String bildname = "tiffanys";
	private int avgIndexClub, aktIndexUser, avgIndexUser, anzahlTN, aktIndexClub;
	private ShakeAnalyser shakeAnalyser;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		// Retrieving the currently selected item number
		int position = getArguments().getInt("position");
		
		if(getArguments().getString("clubname")!=null) clubName = getArguments().getString("clubname");
		
		// List of rivers
		String[] menus = getResources().getStringArray(R.array.menus);

		// Creating view corresponding to the fragment
		View v = inflater.inflate(R.layout.clubshake, container, false);

		// Updating the action bar title
		getActivity().getActionBar().setTitle(menus[position]);
		
		//Testdaten
		//Hinweis für Raphael... Hier anstelle der Testdaten die Daten des Users und des spezifischen Clubs aus der Datenbank lesen
		avgIndexClub = 89;
		aktIndexUser = 78;
		avgIndexUser = 90;
		aktIndexClub =56;
		anzahlTN = 200;
		 
		//ShakeAnalyser initialisieren
		 shakeAnalyser = ShakeAnalyser.getShakeAnalyser(getActivity());
		 
		 //Bild setzen
		 imageClub = (ImageView)v.findViewById(R.id.imageClub);
		 if (clubName.equals("Tiffany's")) bildname = "tiffanys";
		 else if(clubName.equals("Koi")) bildname = "koi";
		 else if (clubName.equals("Ritzz")) bildname="ritzz";	
		 else if (clubName.equals("Suite")) bildname="suite";
		 else if (clubName.equals("Zimmer")) bildname="zimmer";

     	 int resId = getResources().getIdentifier(bildname, "drawable", v.getContext().getPackageName());
     	 imageClub.setImageResource(resId);
		
		//Durchschnittlicher Index des Clubs setzen
		textViewAvgIndexClub = (TextView) v.findViewById(R.id.textViewAvgIndexClub);
	//	textViewAvgIndexClub.setText("Durchschnittlicher Shake-Index im " + clubName );
		
		textAvgIndexClubPkt = (TextView) v.findViewById(R.id.textAvgIndexClubPkt);
		textAvgIndexClubPkt.setText(String.valueOf(avgIndexClub));
		
		textAktIndexClubPkt = (TextView)v.findViewById(R.id.textViewAktClubIndexPkt);
		textAktIndexClubPkt.setText(String.valueOf(aktIndexClub));
		
		//Indize des Users setzen:
		textViewAktIndexPkt = (TextView) v.findViewById(R.id.textViewAktIndexPkt);
		textViewAktIndexPkt.setText(String.valueOf(aktIndexUser));
		
		textViewAvgIndexPkt = (TextView) v.findViewById(R.id.textViewAvgIndexPkt);
		textViewAvgIndexPkt.setText(String.valueOf(avgIndexUser));
		
		textClubName = (TextView) v.findViewById(R.id.textClubName);
		textClubName.setText(clubName+ " Shake-Index");
		
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
