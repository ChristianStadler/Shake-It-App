package de.dhbw.shake_it_app;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


 public class PWvergessen extends Activity{
	 
	 private EditText eMail,username;
	 private Button ButtonPWanfordern;
	 
     @Override
     protected void onCreate(Bundle savedInstanceState) {
 
         super.onCreate(savedInstanceState);
         setContentView(R.layout.pwvergessen);
         
         eMail = (EditText) findViewById(R.id.eMail);
         username = (EditText) findViewById(R.id.username);
         
         ButtonPWanfordern = (Button)findViewById(R.id.ButtonPWanfordern);
         
     }
     
     //Beim Klicken von Passwort anfordern
     public void PWanfordernClick(View v){
    	 
    	 Toast.makeText(this, "Passwort wird zugeschickt.", Toast.LENGTH_SHORT).show();
   	  	Intent nextScreen = new Intent(this, LogInActivity.class);
   	  	startActivity(nextScreen);
     }

	 
 }

