package de.dhbw.shake_it_app;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import de.dhbw.shake_it_app.data.DataProvider;
import de.dhbw.shake_it_app.data.model.User;

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

				public void onCheckedChanged(CompoundButton buttonView,
						boolean isChecked) {
					// TODO Auto-generated method stub
					if (isChecked==true) {
						datenSpeichern=true;
						//in die Datenbank einspeichern, damit das n�chste Mal nicht anmelden auf dem Login-Screen
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
    
	      public void loginClick(View v){
        	  
        	  //Userdaten auslesen
        	  username = Textusername.getText().toString();
        	  password = Textpassword.getText().toString();
        	  

        	  //User users = new User(5, "name", "email", "password");
        	  //System.out.println(users.getName());      	  
        	  
        	  User[] user = (User[]) DataProvider.get().getModel(DataProvider.User, "filter=name&value=" + username);
        	  
        	  //mit Daten aus der Datenbank abgleichen
        	  //auf n�chste Main-Activity gehen
        	  
        	  System.out.println(md5(password));
	    	  System.out.println(user[0].getPassword());
	    	  
        	  if (user[0].getPassword().equals(md5(password)))
        	  {
	    	  nextScreen = new Intent(this, Main.class);
        	  startActivity(nextScreen);
        	  }
        	  else
        	  {
        		  System.out.println("else");
        	  //Error Message
        	  }

          }

	      public void pwVergessenClick(View v){
	    	  nextScreen = new Intent(this, PWvergessen.class);
        	  startActivity(nextScreen);
	      }

	      
	      public void regstrierungClick(View v){
	    	  nextScreen = new Intent(this, Registrierung.class);
        	  startActivity(nextScreen);
	      }
	      
	      public String md5(String s) {
	    	    try {
	    	        // Create MD5 Hash
	    	        MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
	    	        digest.update(s.getBytes());
	    	        byte messageDigest[] = digest.digest();

	    	        // Create Hex String
	    	        StringBuffer hexString = new StringBuffer();
	    	        for (int i=0; i<messageDigest.length; i++)
	    	            hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
	    	        return hexString.toString();

	    	    } catch (NoSuchAlgorithmException e) {
	    	        e.printStackTrace();
	    	    }
	    	    return "";
	    	}
 }

