package com.example.kitchapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class PantallaPrincipal extends Activity{

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setting default screen to login.xml
		setContentView(R.layout.activity_pantalla_principal);
}
	
	
	public void accederDespensa(View view) {
		Intent i = new Intent(this, AccesoDespensa.class);
		Toast.makeText(this, "Actividad Main ", Toast.LENGTH_SHORT).show();
		startActivity(i);
        
	}
}