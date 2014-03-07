package de.dhbw.shake_it_app;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.Toast;

public class LogInActivity extends Activity{
    private Button login;
    private EditText Username;
    private EditText Password;
    private CheckedTextView changeid;
    public SQLiteDatabase sampleDB;
    public String COLUMN_ID="_id";
    public String COLUMN1="username";
    public String COLUMN2="password";
    public String TABLE_NAME="Androdata";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        login=(Button)findViewById(R.id.Loginbutton);
        Username=(EditText)findViewById(R.id.username);
        Password=(EditText)findViewById(R.id.password);
        sampleDB =  this.openOrCreateDatabase(TABLE_NAME, MODE_PRIVATE, null);
        boolean x=init(TABLE_NAME);
        if(x==false)
        {
        createDB();
        insertDB();
        }
        changeid.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent myintent=new Intent("android.intent.action.DATABASE");
                startActivity(myintent);
            }
        });
        login.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                int k=check();
                if(k==1)                
                {
                    Toast.makeText(LogInActivity.this, "Login Successful ...", Toast.LENGTH_SHORT).show();
                    Intent myintent=new Intent("android.intent.action.CHOICE");
                    startActivity(myintent);
                }
                else
                {
                    Username.setText("");
                    Password.setText("");
                    Toast.makeText(LogInActivity.this, "Authentication Failed...", Toast.LENGTH_SHORT).show();
                }   
            }
        });
    }
    public boolean init(String tableName)
    {
        Cursor cursor = sampleDB.rawQuery("select DISTINCT tbl_name from sqlite_master where tbl_name = '"+tableName+"'", null);
        if(cursor!=null) {
            if(cursor.getCount()>0) {
                                cursor.close();
                return true;
            }
                        cursor.close();
        }
        return false;
    }
    private void insertDB() {
        sampleDB.execSQL("INSERT INTO " +
                TABLE_NAME +
                " Values ('1','Androviewer','viewer');");   
        System.out.println("Inserted data successfully....");
    }
    private void createDB() {
        sampleDB.execSQL("CREATE TABLE IF NOT EXISTS " +
                TABLE_NAME+ "(" + COLUMN_ID
                + " integer primary key autoincrement, " + COLUMN1
                + " text not null,"+ COLUMN2
                + " text not null);");
        System.out.println("Database created successfully....");
    }
    private int check() {
        String a=Username.getText().toString();
        String b=Password.getText().toString();
        // TODO Auto-generated method stub
        Cursor c = sampleDB.rawQuery("SELECT username, password FROM " +
                TABLE_NAME +
                " where _id=1", null);

        if (c != null ) {
            if  (c.moveToFirst()) {
                do {
                    String orgusername = c.getString(c.getColumnIndex("username"));
                    String orgpassword = c.getString(c.getColumnIndex("password"));
                    if(a.equals(orgusername)&&b.equals(orgpassword))
                    {
                        return 1;
                    }
                    else
                    {
                        return 0;
                    }
                }while (c.moveToNext());
            }
        }
        return 0;
    }
}
	


