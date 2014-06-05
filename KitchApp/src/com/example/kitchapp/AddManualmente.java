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
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class AddManualmente extends Activity implements OnClickListener {

	private Spinner unitsProduct;
	private EditText nameProduct;
	private EditText cantProduct;
	private String units;
	Handler_Sqlite helper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_manualmente);
		helper = new Handler_Sqlite(this);
		unitsProduct=(Spinner)findViewById(R.id.spinnerUnitsAddPantry);
		ArrayAdapter<CharSequence> adaptador = ArrayAdapter.createFromResource(this,R.array.Unidades,android.R.layout.simple_spinner_item);
		adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		unitsProduct.setAdapter(adaptador);
		Button add = (Button) findViewById(R.id.button_addProduct);
		add.setOnClickListener(this);
		nameProduct = (EditText) findViewById(R.id.editTextNameProduct);
		cantProduct = (EditText) findViewById(R.id.EditTextCantProduct);
		unitsProduct.setOnItemSelectedListener(new OnItemSelectedListener(){
			 
		    @Override
		    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
		        // TODO Auto-generated method stub
		        units = unitsProduct.getItemAtPosition(position).toString();
		 
		    }

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		 
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
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
							helper.updateProduct(nameProduct.getText().toString(),nameProduct.getText().toString(),cantLast + cant,"updatePantry",1);
						}
						else {
							String unit = "";
				 			if (units.equals("Litros")) {
								unit = "l";
							}
							else if (units.equals("Centilitros")) {
								unit = "cl";
							}
							else if (units.equals("Mililitros")) {
								unit = "ml";
							}
							else if (units.equals("Kilos")) {
								unit = "kg";
							}
							else if (units.equals("Gramos")) {
								unit = "gr";
							}
							else if (units.equals("Miligramos")) {
								unit = "mg";
							}
							else if (units.equals("Unidades")) {
								unit = "unid";
							}
							Bundle extras = this.getIntent().getExtras();
							helper.insertProducts(nameProduct.getText().toString(), cant, extras.getInt("idCat"), unit, "", "insertPantry", 1);
						}
						helper.close();
					}
					Intent i = new Intent(this,MostrarProductosCategoria.class);
					i.putExtra("idCat",this.getIntent().getExtras().getInt("idCat"));
					startActivity(i);
					finish();
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
	            .setMessage("La cantidad del producto introducida tiene que ser un numero mayor que cero")
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
