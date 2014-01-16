package com.example.kitchapp;


import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Login extends Activity implements OnClickListener{

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setting default screen to login.xml
		setContentView(R.layout.activity_login);

		TextView registerScreen = (TextView)findViewById(R.id.link_to_register);
		Button b1=(Button)findViewById(R.id.btnLogin);
		
		//Listener para botón login
		b1.setOnClickListener(this);

		// Listener para link de registro de nueva cuenta
		registerScreen.setOnClickListener(this);
}

	

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()){
		case R.id.link_to_register:
			Intent registro = new Intent(getApplicationContext(), Registro.class);
			startActivity(registro);
		case R.id.btnLogin:
			Intent principal=new Intent("com.example.kitchapp.PantallaPrincipal");
			startActivity(principal);
		}
		
	}
}