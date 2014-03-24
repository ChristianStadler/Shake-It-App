package de.dhbw.shake_it_app;


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
	    	  Log.d("tt", "gg");
	    	  System.out.println("Test");
        	  
        	  //Userdaten auslesen
        	  username = Textusername.getText().toString();
        	  password = Textpassword.getText().toString();
        	  

        	  User users = new User(5, "name", "email", "password");
        	  System.out.println(users.getName());      	  
        	  
        	  DataProvider provider = DataProvider.get();
        	  User[] user = (User[]) provider.getModel(DataProvider.User, "sort=name");
        	  System.out.println(user[1].getName());
        	  
        	  
  
        	  //mit Daten aus der Datenbank abgleichen
	    	  
       	  
        	  //�bergabe von Werten an andere Activity 
              //nextScreen.putExtra("Vorname", username);
              //nextScreen.putExtra("Passwort", password);
	    	 
        	  //auf n�chste Main-Activity gehen
	    	  
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

