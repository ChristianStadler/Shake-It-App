package de.dhbw.shake_it_app;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


 public class Registrierung extends Activity{
	 
	 private EditText eMail, password, username, passwordWDH;
	 private Button RegistrierungButton;
	 private String eMailUser, passwordUser, nameUser, passwordWDHUser, fehlermeldung;
	 
     @Override
     protected void onCreate(Bundle savedInstanceState) {
 
         super.onCreate(savedInstanceState);
         setContentView(R.layout.registrierung);
         
         
         eMail = (EditText) findViewById(R.id.eMail);
         username = (EditText) findViewById(R.id.username);
         password = (EditText) findViewById(R.id.password);
         passwordWDH =(EditText) findViewById(R.id.passwordWDH);
         
         RegistrierungButton = (Button) findViewById(R.id.RegistrierungButton);
         
         
     }
     
     public void registrierenClick(){
         eMailUser = eMail.getText().toString();
         nameUser = username.getText().toString();
         passwordUser = password.getText().toString();
         passwordWDHUser = passwordWDH.getText().toString();
         
         //weitere Prüfungen wie: wurde e-mail schon verwendet
         //oder auch gibt es den username schon
         if(passwordUser!=passwordWDHUser){
        	 fehlermeldung="Die Passwörter sind nicht gleich";
         }
         else {
        	 //wenn alle Angaben richtig sind 
        	 //-> Daten in die DB einspeichern
        	 
        	 //-> den Hauptbilschrirm starten
	    	  Intent nextScreen = new Intent(this, Main.class);
        	  startActivity(nextScreen);
		}
    	 Toast.makeText(this, fehlermeldung, Toast.LENGTH_SHORT).show();
         
     }

	 
 }
 
