package com.example.kitchapp;


import java.util.HashMap;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends Activity {
	
	private HashMap<String,String> users;
	private EditText userName;
	private EditText password;
	Handler_Sqlite helper = new Handler_Sqlite(this);

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setting default screen to login.xml
		setContentView(R.layout.activity_login);
		
		userName = (EditText)findViewById(R.id.editTextuserName);
		password = (EditText)findViewById(R.id.editTextPassword);

		TextView registerScreen = (TextView)findViewById(R.id.link_to_register);
		Button b1=(Button)findViewById(R.id.btnLogin);
		
}

		

	public void intento_logueo(View view) {
		String name = userName.getText().toString(); 
		String key = password.getText().toString();
		SQLiteDatabase tmp = helper.open();
		if (tmp != null) {
			if (helper.readUser(name) && key.equals(helper.readPassword(name))) {
				Intent i = new Intent(this, PantallaTransicion.class);
				startActivity(i);
			}
			else if (!helper.readUser(name)) {
				errorLogging();
			}
			else {
				errorPassword();
			}
			helper.close();
				
		}

	}
	
	public void registrarse(View view) {
		Intent i = new Intent(this, Registro.class);
		startActivity(i);
	}
	
	
	public void errorLogging() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		 
	    builder.setTitle("Error")
	            .setIcon(
	                    getResources().getDrawable(
	                            R.drawable.close))
	            .setMessage("No se encuentra registrado en KitchApp. Por favor registrese")
	            .setNeutralButton(R.string.ok, new DialogInterface.OnClickListener() {
	 
	                @Override
	                public void onClick(DialogInterface arg0, int arg1) {
	                	arg0.cancel();
	                }
	            });
	 
	    builder.create();
	    builder.show();
	}
	
	public void errorPassword() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		 
	    builder.setTitle("Error")
	            .setIcon(
	                    getResources().getDrawable(
	                            R.drawable.close))
	            .setMessage("Contraseña incorrecta. Intentelo de nuevo")
	            .setNeutralButton(R.string.ok, new DialogInterface.OnClickListener() {
	 
	                @Override
	                public void onClick(DialogInterface arg0, int arg1) {
	                	arg0.cancel();
	                }
	            });
	 
	    builder.create();
	    builder.show();

	}
	
}
