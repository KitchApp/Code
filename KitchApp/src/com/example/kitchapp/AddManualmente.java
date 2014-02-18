package com.example.kitchapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddManualmente extends Activity implements OnClickListener{
	
	private Spinner categoria;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_manualmente);
		Toast.makeText(this, "Actividad anyadir manualmente", Toast.LENGTH_SHORT).show();
		categoria=(Spinner)findViewById(R.id.Spinner01);
		ArrayAdapter<CharSequence> adaptador = ArrayAdapter.createFromResource(this,R.array.Categorias,android.R.layout.simple_spinner_item);
		adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		categoria.setAdapter(adaptador);
		Button add = (Button) findViewById(R.id.button_addProduct);
		add.setOnClickListener(this);
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
		case R.id.button_addProduct:
			Toast.makeText(this, "Me han pinchado", Toast.LENGTH_SHORT).show();
			Intent i = new Intent(this,MostrarProductosCategoria.class);
			EditText nameProduct = (EditText) v.findViewById(R.id.editTextNameProduct);
			EditText cantProduct = (EditText) v.findViewById(R.id.EditTextCantProduct);
			i.putExtra("nameProduct",nameProduct.getText().toString());
			i.putExtra("cantProduct",Integer.parseInt(cantProduct.getText().toString()));
			i.putExtra("key",0);
			startActivity(i);
			break;
		}
	}

}
