package com.example.kitchapp;

import android.os.Bundle;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AccesoDespensa extends ActionBarActivity implements OnClickListener {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_acceso_despensa);

		Button button_dairy = (Button) findViewById(R.id.buttonDairy);
		button_dairy.setOnClickListener(this);
		Button button_fruits = (Button) findViewById(R.id.buttonFruits);
		button_fruits.setOnClickListener(this);
		Button button_bread = (Button) findViewById(R.id.buttonBread);
		button_bread.setOnClickListener(this);
		Button button_drinks = (Button) findViewById(R.id.buttonDrinks);
		button_drinks.setOnClickListener(this);
		Button button_meat = (Button) findViewById(R.id.buttonMeat);
		button_meat.setOnClickListener(this);
		Button button_fish = (Button) findViewById(R.id.buttonFish);
		button_fish.setOnClickListener(this);
		Button button_condiment = (Button) findViewById(R.id.buttonCondiment);
		button_condiment.setOnClickListener(this);
		Button button_pasta = (Button) findViewById(R.id.buttonPasta);
		button_pasta.setOnClickListener(this);
		Button button_frozen = (Button) findViewById(R.id.buttonFrozen);
		button_frozen.setOnClickListener(this);
		Button button_sauces = (Button) findViewById(R.id.buttonSauces);
		button_sauces.setOnClickListener(this);
		Button button_store = (Button) findViewById(R.id.buttonStore);
		button_store.setOnClickListener(this);
		Button button_others = (Button) findViewById(R.id.buttonOthers);
		button_others.setOnClickListener(this);

	}

	
	
	
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.search:
			Intent i = new Intent(this, Bar_Search.class);
			startActivity(i);
			return true;
		case R.id.logout:
			SharedPreferences settings = getSharedPreferences(
					PantallaTransicion.PREFS_NAME, 0);
			SharedPreferences.Editor editor = settings.edit();

			editor.putBoolean("hasLoggedIn", false);
			editor.commit();

			Intent j = new Intent(this, Login.class);
			j.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(j);
			finish();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	
	@Override
	public void onClick(View v) {
		switch (v.getId()){

		case R.id.buttonDairy:
			Intent i = new Intent(this,MostrarProductosCategoria.class);
			i.putExtra("idCat",1);
			startActivity(i);
			break;
		
		case R.id.buttonFruits:
			Intent j = new Intent(this,MostrarProductosCategoria.class);
			j.putExtra("idCat",2);
			startActivity(j);
			break;
			
		case R.id.buttonBread:
			Intent intent = new Intent(this,MostrarProductosCategoria.class);
			intent.putExtra("idCat",3);
			startActivity(intent);
			break;
			
		case R.id.buttonDrinks:
			Intent intent1 = new Intent(this,MostrarProductosCategoria.class);
			intent1.putExtra("idCat",4);
			startActivity(intent1);
			break;
			
		case R.id.buttonMeat:
			Intent intent2 = new Intent(this,MostrarProductosCategoria.class);
			intent2.putExtra("idCat",5);
			startActivity(intent2);
			break;
			
		case R.id.buttonFish:
			Intent intent3 = new Intent(this,MostrarProductosCategoria.class);
			intent3.putExtra("idCat",6);
			startActivity(intent3);
			break;
			
		case R.id.buttonCondiment:
			Intent intent4 = new Intent(this,MostrarProductosCategoria.class);
			intent4.putExtra("idCat",7);
			startActivity(intent4);
			break;
			
		case R.id.buttonPasta:
			Intent intent5 = new Intent(this,MostrarProductosCategoria.class);
			intent5.putExtra("idCat",8);
			startActivity(intent5);
			break;
			
		case R.id.buttonFrozen:
			Intent intent6 = new Intent(this,MostrarProductosCategoria.class);
			intent6.putExtra("idCat",9);
			startActivity(intent6);
			break;
			
		case R.id.buttonSauces:
			Intent intent7 = new Intent(this,MostrarProductosCategoria.class);
			intent7.putExtra("idCat",10);
			startActivity(intent7);
			break;
		
		case R.id.buttonStore:
			Intent intent8 = new Intent(this,MostrarProductosCategoria.class);
			intent8.putExtra("idCat",11);
			startActivity(intent8);
			break;
			
		case R.id.buttonOthers:
			Intent intent9 = new Intent(this,MostrarProductosCategoria.class);
			intent9.putExtra("idCat",12);
			startActivity(intent9);
			break;
			
		}
	}
	
}
