package com.example.kitchapp;

import android.os.Bundle;
import android.app.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class AddManualmente extends Activity implements OnClickListener {

	private EditText nameProduct;
	private EditText cantProduct;
	Handler_Sqlite helper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_manualmente);
		helper = new Handler_Sqlite(this);
		//Toast.makeText(this, "Actividad anyadir manualmente", Toast.LENGTH_SHORT).show();
		/*categoria=(Spinner)findViewById(R.id.Spinner01);
		ArrayAdapter<CharSequence> adaptador = ArrayAdapter.createFromResource(this,R.array.Categorias,android.R.layout.simple_spinner_item);
		adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		categoria.setAdapter(adaptador);*/
		Button add = (Button) findViewById(R.id.button_addProduct);
		add.setOnClickListener(this);
		nameProduct = (EditText) findViewById(R.id.editTextNameProduct);
		cantProduct = (EditText) findViewById(R.id.EditTextCantProduct);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.acceso_despensa, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()){
		case R.id.button_addProduct:
			try {
				int cant = Integer.parseInt(cantProduct.getText().toString());
				if (cant <= 0) {
					errorCant();
				}
				else {
					SQLiteDatabase tmp = helper.open();
					if (tmp != null) {
						if (helper.existProductAdded(nameProduct.getText().toString())) {
							int cantLast = helper.getCant(nameProduct.getText().toString());
							helper.updateProduct(nameProduct.getText().toString(),nameProduct.getText().toString(),cantLast + cant);
						}
						else {
							Bundle extras = this.getIntent().getExtras();
							helper.insertProducts(nameProduct.getText().toString(), cant, extras.getInt("idCat"), "", "insertPantry", 1);
						}
						helper.close();
					}
					Intent i = new Intent(this,MostrarProductosCategoria.class);
					i.putExtra("idCat",this.getIntent().getExtras().getInt("idCat"));
					startActivity(i);
				}
			}
			catch (NumberFormatException e) {
				errorCant();
			}
			break;
		}
	}

	public void errorCant() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		 
	    builder.setTitle("Error")
	            .setIcon(
	                    getResources().getDrawable(
	                            R.drawable.close))
	            .setMessage("La cantidad del producto introducida tiene que ser un n�mero mayor que cero")
	            .setNeutralButton(R.string.ok, new DialogInterface.OnClickListener() {
	 
	                @Override
	                public void onClick(DialogInterface arg0, int arg1) {
	                	arg0.cancel();
	                }
	            });
	 
	    builder.create();
	    builder.show();
	}

}
