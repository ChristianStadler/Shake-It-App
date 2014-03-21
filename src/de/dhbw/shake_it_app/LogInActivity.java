package de.dhbw.shake_it_app;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import de.dhbw.shake_it_app.data.DataProvider;

 public class LogInActivity extends Activity{
	      private Button login, RegistrierenButton;
	      private EditText Textusername;
	      private EditText Textpassword;
	      private TextView PWvergessenButton;
	      private CheckBox checkBoxDatenSpeichern;
	      private String username, password;
	      private Intent nextScreen;
	      private boolean datenSpeichern = false; 
	  
	      @Override
	      protected void onCreate(Bundle savedInstanceState) {
	  
	          super.onCreate(savedInstanceState);
	          setContentView(R.layout.login);

	          //Userdaten 
	          Textusername=(EditText)findViewById(R.id.username);
	          Textpassword=(EditText)findViewById(R.id.password);    
	          
	          
	          //Login Button
	          login = (Button)findViewById(R.id.Loginbutton);
	          
	          //ChechBox Daten speichern
	          checkBoxDatenSpeichern = (CheckBox)findViewById(R.id.checkBoxDatenSpeichern);
	          checkBoxDatenSpeichern.setOnCheckedChangeListener(new OnCheckedChangeListener(){

				@Override
				public void onCheckedChanged(CompoundButton buttonView,
						boolean isChecked) {
					// TODO Auto-generated method stub
					if (isChecked==true) {
						datenSpeichern=true;
						//in die Datenbank einspeichern, damit das nächste Mal nicht anmelden auf dem Login-Screen
					}
					else datenSpeichern=false;
					
				}
	        	  
	          }
	          );

	          
	          //Passwort vergessen
	          PWvergessenButton = (TextView)findViewById(R.id.PWvergessenButton);
	          
	          //Registrieren Button
	          RegistrierenButton = (Button)findViewById(R.id.RegistrierenButton);
	          
	          //startDebugging() is for debugging the DataProvider
	          DataProvider.get().startDebugging();
	          
	      }
    
	      public  void loginClick(View v){
        	  
        	  //Userdaten auslesen
        	 // username = Textusername.getText().toString();
        	  //password = Textpassword.getText().toString();
        	  
        	  //mit Daten aus der Datenbank abgleichen
	    	  
       	  
        	  //Übergabe von Werten an andere Activity 
              //nextScreen.putExtra("Vorname", username);
              //nextScreen.putExtra("Passwort", password);
        	  //auf nächste Main-Activity gehen
	    	  
	    	  nextScreen = new Intent(this, Main.class);
        	  startActivity(nextScreen);
        	  

          }
	      
	      public void pwVergessenClick(View v){
	    	  nextScreen = new Intent(this, PWvergessen.class);
        	  startActivity(nextScreen);
	      }
	      
	      public void regstrierungClick(View v){
	    	  nextScreen = new Intent(this, Registrierung.class);
        	  startActivity(nextScreen);
	      }
	      
 }

