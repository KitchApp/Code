package com.example.kitchapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PantallaPrincipal extends Activity{

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setting default screen to login.xml
		setContentView(R.layout.activity_pantalla_principal);
		Button button1=(Button)findViewById(R.id.button1);
		button1.setTextColor(getApplication().getResources().getColor(R.color.white)); 
		Button button2=(Button)findViewById(R.id.button2);
		button2.setTextColor(getApplication().getResources().getColor(R.color.white));
		Button button3=(Button)findViewById(R.id.button3);
		button3.setTextColor(getApplication().getResources().getColor(R.color.white));
		Button button4=(Button)findViewById(R.id.button4);
		button4.setTextColor(getApplication().getResources().getColor(R.color.white));

}
	
	
	public void accederDespensa(View view) {
		Intent i = new Intent(this, AccesoDespensa.class);
		//Toast.makeText(this, "Actividad Main ", Toast.LENGTH_SHORT).show();
		startActivity(i);
        
	}
}