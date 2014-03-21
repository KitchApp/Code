package com.example.kitchapp;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddManualmente extends Activity implements OnClickListener {

	private Spinner categoria;
	private EditText nameProduct;
	private EditText cantProduct;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_manualmente);
		//Toast.makeText(this, "Actividad anyadir manualmente", Toast.LENGTH_SHORT).show();
		/*categoria=(Spinner)findViewById(R.id.Spinner01);
		ArrayAdapter<CharSequence> adaptador = ArrayAdapter.createFromResource(this,R.array.Categorias,android.R.layout.simple_spinner_item);
		adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		categoria.setAdapter(adaptador);*/
		Button add = (Button) findViewById(R.id.button_addProduct);
		add.setOnClickListener(this);
		nameProduct = (EditText) findViewById(R.id.editTextNameProduct);
		cantProduct = (EditText) findViewById(R.id.EditTextCantProduct);
		/*categoria.setOnItemSelectedListener(new OnItemSelectedListener(){
			 
		    @Override
		    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
		        // TODO Auto-generated method stub
		        //Toast.makeText(getApplicationContext(), "Ha pulsado el item " + position, Toast.LENGTH_SHORT).show();
		 
		    }

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		 
		});*/
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
			//Toast.makeText(this, "Me han pinchado", Toast.LENGTH_SHORT).show();
			try {
				int cant = Integer.parseInt(cantProduct.getText().toString());
				Intent i = new Intent(this,MostrarProductosCategoria.class);
				i.putExtra("nameProduct",nameProduct.getText().toString());
				i.putExtra("cantProduct",cant);
				i.putExtra("key",1);
				i.putExtra("idCat",this.getIntent().getExtras().getInt("idCat"));
				startActivity(i);
			}
			catch (NumberFormatException e) {
				Toast.makeText(this, "La cantidad introducida tiene que ser de tipo entero", Toast.LENGTH_SHORT).show();
			}
			break;
		}
	}

}
