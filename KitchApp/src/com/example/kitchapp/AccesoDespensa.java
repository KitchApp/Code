package com.example.kitchapp;


import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_acceso_despensa);

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
		Intent i;
		switch (v.getId()){

		case R.id.buttonLacteos:
			i = new Intent(this,MostrarProductosCategoria.class);
			i.putExtra("lacteos", 1);
			startActivity(i);
			break;
		case R.id.buttonFrutas:
			i = new Intent(this,MostrarProductosCategoria.class);
			i.putExtra("frutas", 2);
			startActivity(i);
			break;
		case R.id.buttonPan:
			i = new Intent(this,MostrarProductosCategoria.class);
			i.putExtra("pan", 3);
			startActivity(i);
			break;
		case R.id.buttonBebidas:
			i = new Intent(this,MostrarProductosCategoria.class);
			i.putExtra("bebidas", 4);
			startActivity(i);
			break;
		case R.id.buttonCarnes:
			i = new Intent(this,MostrarProductosCategoria.class);
			i.putExtra("carnes", 5);
			startActivity(i);
			break;
		case R.id.buttonPescados:
			i = new Intent(this,MostrarProductosCategoria.class);
			i.putExtra("pescados", 6);
			startActivity(i);
			break;
		case R.id.buttonSalsas:
			i = new Intent(this,MostrarProductosCategoria.class);
			i.putExtra("salsas", 7);
			startActivity(i);
			break;
		case R.id.buttonPastas:
			i = new Intent(this,MostrarProductosCategoria.class);
			i.putExtra("pastas", 8);
			startActivity(i);
			break;
		case R.id.buttonCongelados:
			i = new Intent(this,MostrarProductosCategoria.class);
			i.putExtra("congelados", 9);
			startActivity(i);
			break;
		case R.id.buttonVarios:
			i = new Intent(this,MostrarProductosCategoria.class);
			i.putExtra("varios", 10);
			startActivity(i);
			break;

			
		case R.id.button_add:

			alertDialog(v);
		}
	}
	
	
	public void alertDialog(View v){
		final String [] items = new String[] {"Manualmente", "Voz", "Código de barras" };
	    final Integer[] icons = new Integer[] {R.drawable.teclado_android, R.drawable.microfono, R.drawable.barras};
	    ListAdapter adapter = new ItemAdapter(this, items, icons);
	    
        new AlertDialog.Builder(this).setAdapter(adapter, new DialogInterface.OnClickListener() {
        	public void onClick(DialogInterface dialog, int item ) {
        		if (item==0)
        			addManualmente();
	        }
	    }).show();
	}
	
	
	public void addManualmente() {
		  Intent intent = new Intent(this,AddManualmente.class);
		  startActivity(intent);
	  }

}
