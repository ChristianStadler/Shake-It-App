package de.dhbw.shake_it_app;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

 public class LogInActivity extends Activity{
	      private Button login;
	      private EditText Textusername;
	      private EditText Textpassword;
	      private TextView PWvergessenButton;
	      private CheckedTextView changeid;
	      private String username, password;
	      public Intent nextScreen;
	  
	      @Override
	      protected void onCreate(Bundle savedInstanceState) {
	  
	          super.onCreate(savedInstanceState);
	          setContentView(R.layout.login);

        	  
        	  
	          //Userdaten 
	          Textusername=(EditText)findViewById(R.id.username);
	          Textpassword=(EditText)findViewById(R.id.password);    
	          
	          //Nutzerdaten speichern
          
	          
	          //Login Button
	          login=(Button)findViewById(R.id.Loginbutton);
	          
	          

	          
	          
	          //Passwort vergessen
	          PWvergessenButton = (TextView)findViewById(R.id.PWvergessenButton);
	          /*
	          PWvergessenButton.setOnClickListener(new OnClickListener(){
	          	public void onClick(View v) {
                  Intent myintent=new Intent("android.intent.action.DATABASE");
                  startActivity(myintent);
	          	}
	          });
	          */
	          //Registrieren Button
	      }
    
	      public  void loginClick(View v){
        	  
        	  //Userdaten auslesen
        	 // username = Textusername.getText().toString();
        	  //password = Textpassword.getText().toString();
        	  
        	  //mit Daten aus der Datenbank abgleichen

       	  
        	  //Übergabe von Werten an andere Activity 
             // nextScreen.putExtra("Vorname", username);
              //nextScreen.putExtra("Passwort", password);
        	  //auf nächste Main-Activity gehen
	    	  nextScreen = new Intent(this, Main.class);
        	  startActivity(nextScreen);

       }
	      
 }

