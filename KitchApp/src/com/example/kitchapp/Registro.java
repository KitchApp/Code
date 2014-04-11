package com.example.kitchapp;


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
import android.widget.Toast;
 
public class Registro extends Activity implements OnClickListener {
	
	private EditText userName;
	private EditText password;
	private EditText email;
<<<<<<< HEAD
	Handler_Sqlite helper = new Handler_Sqlite(this);
=======
>>>>>>> Rama-Mayra_Android
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set View to register.xml
        setContentView(R.layout.registro);
 
        TextView loginScreen = (TextView) findViewById(R.id.link_to_login);
        Button buttonRegister = (Button) findViewById(R.id.btnRegistro);
        userName = (EditText) findViewById(R.id.reg_usuario);
        password = (EditText) findViewById(R.id.reg_password);
        email = (EditText) findViewById(R.id.reg_email);
 
        // Listening to Login Screen link
        loginScreen.setOnClickListener(this);
        buttonRegister.setOnClickListener(this);
 
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
			
			case R.id.link_to_login:
				finish();
				break;
			case R.id.btnRegistro:
				String mail = email.getText().toString();
				String [] mailarroba = mail.split("@");
<<<<<<< HEAD
				if ((mailarroba.length < 2) || (mail.equals(mailarroba[0])) || (mailarroba.length > 2)) {
=======
				if (mail.equals(mailarroba[0]) || mailarroba.length > 2) {
>>>>>>> Rama-Mayra_Android
					errorMail();
				}
				else {
					String [] mailpoint = mailarroba[1].split("\\.");
<<<<<<< HEAD
					if (mailpoint.length < 2 || mailarroba[1].equals(mailpoint[0])) {
						errorMail();
					}
					else {
						String nameUser = userName.getText().toString();
						String userPassword = password.getText().toString();
						String userMail = email.getText().toString();
						SQLiteDatabase tmp = helper.open();
						if (tmp != null) {
							if (!helper.readUser(nameUser)) {
								helper.insertUser(nameUser, userPassword, userMail);
								Intent intent = new Intent(this,PantallaTransicion.class);
								startActivity(intent);
								finish();
							}
							else {
								errorRegister();
							}
							helper.close();
						}
=======
					if (mailarroba[1].equals(mailpoint[0]) || mailpoint.length > 2) {
						errorMail();
					}
					else {
						Intent intent = new Intent(this,Login.class);
						intent.putExtra("userName",userName.getText().toString());
						intent.putExtra("password",password.getText().toString());
						intent.putExtra("email", email.getText().toString());
						startActivity(intent);
						finish();
>>>>>>> Rama-Mayra_Android
					}
				}
				break;
		}
		
	}

	
	public void errorMail() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		 
	    builder.setTitle("Error")
	            .setIcon(
	                    getResources().getDrawable(
	                            R.drawable.close))
<<<<<<< HEAD
	            .setMessage("La direccion de correo tiene que ser tipo example@dominio.com/es")
	            .setNeutralButton(R.string.ok, new DialogInterface.OnClickListener() {
	 
	                @Override
	                public void onClick(DialogInterface arg0, int arg1) {
	                	arg0.cancel();
	                }
	            });
	 
	    builder.create();
	    builder.show();
	}
	
	public void errorRegister() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		 
	    builder.setTitle("Error")
	            .setIcon(
	                    getResources().getDrawable(
	                            R.drawable.close))
	            .setMessage("Ya existe ese nombre de usuario. Por favor registrese de nuevo")
=======
	            .setMessage("La direcci�n de correo tiene que ser tipo example@dominio.com/es")
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
}
