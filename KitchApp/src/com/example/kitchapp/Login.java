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
<<<<<<< HEAD
=======
		/*users = new HashMap<String,String>();
		initializeUsers();*/
		Bundle extra = this.getIntent().getExtras();
		if (extra != null) {
			String nameUser = extra.getString("userName");
			String userPassword = extra.getString("password");
			String userEmail = extra.getString("email");
			SQLiteDatabase tmp = helper.open();
			if (tmp != null) {
				if (!helper.readUser(nameUser)) {
					helper.insertUser(nameUser, userPassword, userEmail);
				}
				else {
					errorRegister();
				}
				helper.close();

			}
			/*if (!users.containsKey(extra.getString("userName"))) {
				users.put(extra.getString("userName"),extra.getString("password"));
			}
			else {
				errorRegister();
			}*/
		}
>>>>>>> Rama-Mayra_Android
		
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
<<<<<<< HEAD
=======
				//Toast.makeText(this, "Actividad Main ", Toast.LENGTH_SHORT).show();
>>>>>>> Rama-Mayra_Android
				startActivity(i);
			}
			else if (!helper.readUser(name)) {
				errorLogging();
			}
			else {
				errorPassword();
			}
			helper.close();
				
<<<<<<< HEAD
		}
=======
		}
		/*if (users.containsKey(name) && key.equals(users.get(name))) {
			Intent i = new Intent(this, PantallaTransicion.class);
			//Toast.makeText(this, "Actividad Main ", Toast.LENGTH_SHORT).show();
			startActivity(i);
		}
		else if (!users.containsKey(name)) {
			errorLogging();
		}
		else
			errorPassword();*/
>>>>>>> Rama-Mayra_Android

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
<<<<<<< HEAD
	            .setMessage("No se encuentra registrado en KitchApp. Por favor registrese")
=======
	            .setMessage("No se encuentra registrado en KitchApp. Por favor regístrese")
>>>>>>> Rama-Mayra_Android
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
<<<<<<< HEAD
	            .setMessage("Contraseña incorrecta. Intentelo de nuevo")
=======
	            .setMessage("Contraseña incorrecta. Inténtelo de nuevo")
>>>>>>> Rama-Mayra_Android
	            .setNeutralButton(R.string.ok, new DialogInterface.OnClickListener() {
	 
	                @Override
	                public void onClick(DialogInterface arg0, int arg1) {
	                	arg0.cancel();
	                }
	            });
	 
	    builder.create();
	    builder.show();

	}
	
<<<<<<< HEAD
=======
	public void errorRegister() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		 
	    builder.setTitle("Error")
	            .setIcon(
	                    getResources().getDrawable(
	                            R.drawable.close))
	            .setMessage("Ya existe ese nombre de usuario. Por favor regístrese de nuevo")
	            .setNeutralButton(R.string.ok, new DialogInterface.OnClickListener() {
	 
	                @Override
	                public void onClick(DialogInterface arg0, int arg1) {
	                	arg0.cancel();
	                }
	            });
	 
	    builder.create();
	    builder.show();
	}
>>>>>>> Rama-Mayra_Android
}
