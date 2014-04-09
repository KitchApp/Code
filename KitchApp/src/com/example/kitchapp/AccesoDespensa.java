package com.example.kitchapp;


import java.util.ArrayList;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;


public class AccesoDespensa extends Activity implements OnClickListener {
	
<<<<<<< HEAD
<<<<<<< HEAD

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
	Button b1;
=======
	
>>>>>>> Rama-Edu-Android
=======
	Button buttonLacteos;
	Button buttonFrutas;
	Button buttonPan;
	Button buttonBebidas;
	Button buttonCarnes;
	Button buttonPescados;
	Button buttonSalsas;
	Button buttonPastas;
	Button buttonCongelados;
	Button buttonVarios;
>>>>>>> 3595f17192c1223c6fcb9aab83ebc4a75ab32ca2
	
=======
>>>>>>> Rama-Edu-Android
=======
>>>>>>> 0eeb76638ed6d7066f13a91e1147d0ce22aab4a7
=======
>>>>>>> Rama-Edu-Android
=======
>>>>>>> 32477ed215e82d01b6f7e658596c4fb24f018cf5
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_acceso_despensa);
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD

<<<<<<< HEAD
<<<<<<< HEAD
		b1 = (Button) findViewById(R.id.button1);
		b1.setOnClickListener(this);
=======
>>>>>>> 3595f17192c1223c6fcb9aab83ebc4a75ab32ca2

		buttonLacteos = (Button) findViewById(R.id.buttonLacteos);
		buttonLacteos.setOnClickListener(this);
		buttonFrutas = (Button) findViewById(R.id.buttonFrutas);
		buttonFrutas.setOnClickListener(this);
		buttonPan = (Button) findViewById(R.id.buttonPan);
		buttonPan.setOnClickListener(this);
		buttonBebidas = (Button) findViewById(R.id.buttonBebidas);
		buttonBebidas.setOnClickListener(this);
		buttonCarnes = (Button) findViewById(R.id.buttonCarnes);
		buttonCarnes.setOnClickListener(this);
		buttonPescados = (Button) findViewById(R.id.buttonPescados);
		buttonPescados.setOnClickListener(this);
		buttonSalsas = (Button) findViewById(R.id.buttonSalsas);
		buttonSalsas.setOnClickListener(this);
		buttonPastas = (Button) findViewById(R.id.buttonPastas);
		buttonPastas.setOnClickListener(this);
		buttonCongelados = (Button) findViewById(R.id.buttonCongelados);
		buttonCongelados.setOnClickListener(this);
		buttonVarios = (Button) findViewById(R.id.buttonVarios);
		buttonVarios.setOnClickListener(this);

		Button b=(Button)findViewById(R.id.button_add);
		b.setOnClickListener(this);
		Button button_add=(Button)findViewById(R.id.button_add);
		button_add.setOnClickListener(this);
		

<<<<<<< HEAD
>>>>>>> cbefd14e7b9a60ed8c3553c1b066bc11efc60619
=======
=======
	
>>>>>>> Rama-Edu-Android
=======
	
>>>>>>> 0eeb76638ed6d7066f13a91e1147d0ce22aab4a7
=======

>>>>>>> Rama-Edu-Android
=======

>>>>>>> 32477ed215e82d01b6f7e658596c4fb24f018cf5
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
		Button button_sauces = (Button) findViewById(R.id.buttonSauces);
		button_sauces.setOnClickListener(this);
		Button button_pasta = (Button) findViewById(R.id.buttonPasta);
		button_pasta.setOnClickListener(this);
		Button button_frozen = (Button) findViewById(R.id.buttonFrozen);
		button_frozen.setOnClickListener(this);
		Button button_others = (Button) findViewById(R.id.buttonOthers);
		button_others.setOnClickListener(this);

	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.acceso_despensa, menu);
		return true;
	}
	

	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
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
			
		case R.id.buttonSauces:
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
			
		case R.id.buttonOthers:
			Intent intent7 = new Intent(this,MostrarProductosCategoria.class);
			intent7.putExtra("idCat",10);
			startActivity(intent7);
			break;
			
		}
	}
	
}
