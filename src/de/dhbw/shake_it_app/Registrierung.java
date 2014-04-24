package de.dhbw.shake_it_app;


import de.dhbw.shake_it_app.data.KeyValue;
import de.dhbw.shake_it_app.data.operator.DataOperator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
     
     public void registrierenClick(View v){
         
         //weitere Prüfungen wie: wurde e-mail schon verwendet
         //oder auch gibt es den username schon
         if ( eMail.getText().toString()==null) {
        	 fehlermeldung="Bitte E-Mail eingeben";
		}
         else if (username.getText().toString()==null) {
        	 fehlermeldung="Bitte Username eingeben";
		}
         else if (password.getText().toString()==null) {
        	 fehlermeldung="Bitte Passwort eingeben";
		}
         else if (passwordWDH.getText().toString()==null) {
        	 fehlermeldung="Bitte Passwort-Wiederholung eingeben";
		}
         else {
             eMailUser = eMail.getText().toString();
             nameUser = username.getText().toString();
             passwordUser = password.getText().toString();
             passwordWDHUser = passwordWDH.getText().toString();
             
             if(passwordUser.equals(passwordWDHUser)==false){
            	 fehlermeldung="Die Passwörter sind nicht gleich";
             }
             else {
            	 //wenn alle Angaben richtig sind 
            	 DataOperator.get().saveRegisterData(eMailUser, nameUser, passwordUser);
            	 
            	 //-> den Hauptbilschrirm starten
            	 fehlermeldung = "erfolgreich registriert";
    	    	  Intent nextScreen = new Intent(this, Main.class);
            	  startActivity(nextScreen);
    		}
		}
    	 Toast.makeText(this, fehlermeldung, Toast.LENGTH_SHORT).show();
         
     }

	 
 }
 
