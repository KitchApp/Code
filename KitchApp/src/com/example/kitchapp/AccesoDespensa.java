package com.example.kitchapp;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class AccesoDespensa extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_acceso_despensa);

		Button b1 = (Button) findViewById(R.id.button1);
		b1.setOnClickListener(this);
		Toast.makeText(this, "Actividad despensa", Toast.LENGTH_SHORT).show();
		Button b=(Button)findViewById(R.id.button_add);
		b.setOnClickListener(this);
		Toast.makeText(this, "Actividad despensa", Toast.LENGTH_SHORT).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.acceso_despensa, menu);
		return true;
	}
	
	/*public void mostrarProductos(View view) {
		Intent intent = new Intent(this,MostrarProductosCategoria.class);
		startActivity(intent);
	}*/
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()){
		/*case R.id.button_add:
			Intent i = new Intent(this,AddDespensa.class);
			startActivity(i);
			break;*/
		case R.id.button1:
			Intent i = new Intent(this,MostrarProductosCategoria.class);
			startActivity(i);
			break;
			
		case R.id.button1:
			Intent intent = new Intent(this,MostrarProductosCategoria.class);
			startActivity(intent);
			break;
		}
	}
}
