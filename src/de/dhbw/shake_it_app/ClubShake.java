package de.dhbw.shake_it_app;

import de.dhbw.shake_it_app.data.DataProvider;
import de.dhbw.shake_it_app.data.KeyValue;
import de.dhbw.shake_it_app.data.model.Location;
import de.dhbw.shake_it_app.data.operator.DataOperator;
import de.dhbw.shake_it_app.data.operator.ShakeAnalyser;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class ClubShake extends Fragment {
	
	private Switch switchShaken;
	private TextView textViewAvgIndexClub, textAvgIndexClubPkt, textViewAvgIndexPkt, textViewAktIndexPkt, TextTNAnzahl, textVeranstaltungName, textAktIndexClubPkt, textClubName;
	private TextView textPopUp;
	private ImageView imageClub;
	private String clubName = "Club";
	private String bildname = "tiffanys";
	private int avgIndexClub, aktIndexUser, avgIndexUser, anzahlTN, aktIndexClub;
	private ShakeAnalyser shakeAnalyser;
	private SharedPreferences sharedPreferences;
	private long clubId;
	private long userId;
	private Refresher refresher;
	private ImageButton cancelButton;

	private View v2;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Retrieving the currently selected item number
		int position = getArguments().getInt("position");
//		if(getArguments().getString("clubname")!=null) clubName = getArguments().getString("clubname");
		clubName = KeyValue.getInstance().getClubName();
		clubId = KeyValue.getInstance().getClubId();
		userId = KeyValue.getInstance().getUser();
		
		// List of rivers
		String[] menus = getResources().getStringArray(R.array.menus);

		// Creating view corresponding to the fragment
		View v = inflater.inflate(R.layout.clubshake, container, false);
		v2 = v;

		// Updating the action bar title
		getActivity().getActionBar().setTitle(menus[position]);
		
		//sharedPreferences
		sharedPreferences = v.getContext().getSharedPreferences("de.dhbw.shake_it_app", Context.MODE_PRIVATE);
		
		 
		 
		//ShakeAnalyser initialisieren
		 shakeAnalyser = ShakeAnalyser.getShakeAnalyser(getActivity());
		 refresher = Refresher.get(this,clubId,userId,shakeAnalyser);
		 avgIndexClub = DataOperator.get().returnOverallLocationIndex(clubId);
		 aktIndexClub = DataOperator.get().returnCurrLocationIndex(clubId);
		 
		 avgIndexUser = DataOperator.get().returnOverallUserIndex(userId);
		 aktIndexUser = shakeAnalyser.getConvertedSessionIndex();
		 

		 //Bild setzen
		 imageClub = (ImageView)v.findViewById(R.id.imageClub);
		 if (clubName.equals("Tiffany")) bildname = "tiffanys";
		 else if(clubName.equals("KOI Club")) bildname = "koi";
		 else if (clubName.equals("Ritzz")) bildname="ritzz";	
		 else if (clubName.equals("Suite")) bildname="suite";
		 else if (clubName.equals("Das Zimmer")) bildname="zimmer";
		
		 else if (clubName.equals("Baton Rouge")) bildname="batonrouge";
		 else if (clubName.equals("Soho")) bildname="soho";
		 else if (clubName.equals("Schneckenhof")) bildname="schneckenhof";

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
		
		switchShaken.setChecked(KeyValue.getInstance().getAmShaken());
		
		switchShaken.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked==true) {
//					initiatePopupWindow();
					Toast.makeText(
							getActivity(),
							 "Shake it, now!",
						    Toast.LENGTH_SHORT).show();
                    //hier den ShakeAnalyse starten
					 shakeAnalyser.startShakeAnalyser(clubId, userId);
					 KeyValue.getInstance().setAmShaken(true);
					 System.out.println("Shaken" +KeyValue.getInstance().getAmShaken());
					 if(refresher.getState()==Thread.State.NEW){
						 
					 refresher.start();
					 }
				}
				else if (isChecked==false) {
					Toast.makeText(
							getActivity(),
							 "Stop Shaking!",
						    Toast.LENGTH_SHORT).show();
                    //hier den ShakeAnalyse stoppen
					shakeAnalyser.stopShakeAnalyser();
					 KeyValue.getInstance().setAmShaken(false);
					 System.out.println("Shaken" +KeyValue.getInstance().getAmShaken());
//					 refresher.stopRefrehs();
				}
				else {
					
				}
				
			}
		});

		
		return v;
		
	}
	
    public PopupWindow pw;
    public void initiatePopupWindow() {
        try {
            //We need to get the instance of the LayoutInflater, use the context of this activity
            LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //Inflate the view from a predefined XML layout
            View layout = inflater.inflate(R.layout.popup_falsevalue, null, false);
            // create a 300px width and 470px height PopupWindow
            pw = new PopupWindow(layout, 350, 200, true);
            // display the popup in the center
            pw.showAtLocation(layout, Gravity.CENTER, 0, 0);

            textPopUp = (TextView) layout.findViewById(R.id.textPopUp);
            cancelButton = (ImageButton) layout.findViewById(R.id.imageButtonClose);
            cancelButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    pw.dismiss();
                }
        });


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    
    public void setData(){
    	if(KeyValue.getInstance().getAmShaken()==true){
    	v2.post((new Runnable() {
            public void run() {
            	int currentLocationIndex = refresher.getCurrentLocationIndex();
        		int overallLocationIndex = refresher.getOverallLocationIndex();
        		int overallUserIndex = refresher.getOverallUserIndex();
        		int currentUserIndex = shakeAnalyser.returnCurrentIndex();
        		
        		
            	textAvgIndexClubPkt = (TextView) v2.findViewById(R.id.textAvgIndexClubPkt);
            	textAvgIndexClubPkt.setText(String.valueOf(overallLocationIndex));	
         
            	
            	textAktIndexClubPkt = (TextView)v2.findViewById(R.id.textViewAktClubIndexPkt);
        		textAktIndexClubPkt.setText(String.valueOf(currentLocationIndex));
        		
        		//Indize des Users setzen:
        		textViewAktIndexPkt = (TextView) v2.findViewById(R.id.textViewAktIndexPkt);
        		textViewAktIndexPkt.setText(String.valueOf(currentUserIndex));
        		
        		textViewAvgIndexPkt = (TextView) v2.findViewById(R.id.textViewAvgIndexPkt);
        		textViewAvgIndexPkt.setText(String.valueOf(overallUserIndex));
        		
        		if(shakeAnalyser.getIndexTooHigh()==true) initiatePopupWindow();
            }
        }));
    	}
    }
}
