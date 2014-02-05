package com.example.kitchapp;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class MostrarProductosCategoria extends Activity implements OnClickListener {
	
	private ListView list;
    private ArrayList<ItemProducto> products;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mostrar_productos_categoria);
		products = new ArrayList<ItemProducto>();
		initializeArrayList();
		list = (ListView)findViewById(R.id.listViewProducts);
		ItemProductoAdapter adapter;
		// Inicializamos el adapter.
		adapter = new ItemProductoAdapter(this,products);
		// Asignamos el Adapter al ListView, en este punto hacemos que el
		// ListView muestre los datos que queremos.
		list.setAdapter(adapter);
		//setContentView(R.layout.activity_mostrar_productos_categoria);
		list.setOnItemClickListener(new OnItemClickListener(){
			 
		    @Override
		    public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
		        // TODO Auto-generated method stub
		        Toast.makeText(getApplicationContext(), "Ha pulsado el item " + position, Toast.LENGTH_SHORT).show();
		        modificarProducto(arg1);
		 
		    }
		 
		}); 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.mostrar_productos_categoria, menu);
		return true;
	}
	
	public void modificarProducto(View view) {
		/*Intent intent = new Intent(this,ModificarProductoDespensa.class);
		startActivity(intent);*/
		/*ModificarProductoDespensa intent = new ModificarProductoDespensa();
		Toast.makeText(this, "Actividad mostrar productos", Toast.LENGTH_SHORT).show();
		intent.onCreateDialog(null);*/
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		 
        // Get the layout inflater
        LayoutInflater inflater = this.getLayoutInflater();
 
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.activity_modificar_producto_despensa, null))
                // Add action buttons
                .setPositiveButton(R.string.set,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                /*Toast.makeText(getActivity(), "Set",
                                        Toast.LENGTH_SHORT).show();*/
                            	dialog.cancel();
                            }
                        })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        /*Toast.makeText(getActivity(), "Cancel", Toast.LENGTH_SHORT)
                                .show();*/
                    	dialog.cancel();
                    }
                });
        builder.create();
        builder.show();
 
	}
	/*@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}*/
	
	private void initializeArrayList() {
		products.add(new ItemProducto(0,"Leche Asturiana Entera",5,""));
		products.add(new ItemProducto(1,"Leche Asturiana Desnatada",4,""));
		products.add(new ItemProducto(2,"Leche Asturiana Semidesnatada",3,""));
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
}
