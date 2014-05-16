package com.example.kitchapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
<<<<<<< HEAD

public class PantallaPrincipal extends Activity{
=======
import android.view.View.OnClickListener;
import android.widget.Button;

public class PantallaPrincipal extends Activity implements OnClickListener{
>>>>>>> Rama-Lorena-Android

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setting default screen to login.xml
<<<<<<< HEAD
		setContentView(R.layout.activity_pantalla_principal);
=======
		
		setContentView(R.layout.activity_pantalla_principal);
		Button buttonPantry = (Button) findViewById(R.id.button2);
		buttonPantry.setOnClickListener(this);
		Button buttonList = (Button) findViewById(R.id.button1);
		buttonList.setOnClickListener(this);
		Button buttonRecipes = (Button) findViewById(R.id.button3);
		buttonRecipes.setOnClickListener(this);
>>>>>>> Rama-Lorena-Android
		
}
	
	
<<<<<<< HEAD
	public void accederDespensa(View view) {
		
		Intent i = new Intent(this, AccesoDespensa.class);
		//Toast.makeText(this, "Actividad Main ", Toast.LENGTH_SHORT).show();
		startActivity(i);
        
	}
=======
	public void onClick(View v) {
		switch(v.getId()) {
			case R.id.button1:
				Intent i = new Intent(this, ShoppingLists.class);
				startActivity(i);
				break;
			
			case R.id.button2:
				Intent j = new Intent(this, AccesoDespensa.class);
				startActivity(j);
				break;
				
		}
	}
	
	
>>>>>>> Rama-Lorena-Android
}
