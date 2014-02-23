package com.example.kitchapp;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class MostrarProductosCategoria extends Activity implements OnClickListener {
	
	private ListView list;
    private ArrayList<ItemProducto> products;
    private int pos;
    private TextView cantProduct;
    private Button save;
    private Button cancel;
    private int cantFinal;
    private AlertDialog.Builder builder;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mostrar_productos_categoria);
			/*int posi = extra.getInt("posicion");
			int cantProducto = extra.getInt("cantProduct");
			ItemProducto producto = products.get(posi);
			System.out.println("holaaaaa");
			producto.setCantidad(cantProducto);
			products.set(posi,producto);*/
			//products = new ArrayList<ItemProducto>();
			//products = extra.getParcelable("productos");
		products = new ArrayList<ItemProducto>();
		initializeArrayList();
		Bundle extra = this.getIntent().getExtras();
		if (extra!=null) {
			int key = extra.getInt("key");
			if (key == 0) {
				ItemProducto item = new ItemProducto(products.size(),extra.getString("nameProduct"),extra.getInt("cantProduct"),"");
				boolean encontrado = false;
				int i = 0;
				while (i<products.size() && !encontrado){
					ItemProducto prod = products.get(i);
					String name = prod.getNombre();
					if (name.equals(item.getNombre())) {
						encontrado = true;
					}
					i++;
				}
				if (!encontrado) {
					products.add(item);
				}
				else {
					errorProduct();
				}
			}
			else {
				int posi = extra.getInt("posicion");
				int cantProducto = extra.getInt("cantProduct");
				ItemProducto producto = products.get(posi);
				producto.setCantidad(cantProducto);
				products.set(posi,producto);
			}
		}
		TextView link_atras = (TextView) findViewById(R.id.textView_Atras);
		link_atras.setOnClickListener(this);
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
		        //Toast.makeText(getApplicationContext(), "Ha pulsado el item " + position, Toast.LENGTH_SHORT).show();
		        cantFinal = products.get(position).getCantidad();
		        modificarProducto(arg1,position);
		 
		    }
		 
		}); 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.mostrar_productos_categoria, menu);
		return true;
	}
	
	public void modificarProducto(View view,int position) {
		/*Intent intent = new Intent(this,ModificarProductoDespensa.class);
		startActivity(intent);*/
		/*ModificarProductoDespensa intent = new ModificarProductoDespensa();
		Toast.makeText(this, "Actividad mostrar productos", Toast.LENGTH_SHORT).show();
		intent.onCreateDialog(null);*/
		builder = new AlertDialog.Builder(this);
		 
        // Get the layout inflater
        LayoutInflater inflater = this.getLayoutInflater();
 
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        view = inflater.inflate(R.layout.activity_modificar_producto_despensa, null);
        cantProduct = (TextView) view.findViewById(R.id.cantProduct);
        cantProduct.setText(products.get(position).getCantidad() + "");
    	pos = position;
    	save = (Button) view.findViewById(R.id.button_save);
    	save.setOnClickListener(this);
    	cancel = (Button) view.findViewById(R.id.button_cancel);
    	cancel.setOnClickListener(this);
        builder.setView(view);
                // Add action buttons
                /*.setPositiveButton(R.string.save,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                /*Toast.makeText(getActivity(), "Set",
                                        Toast.LENGTH_SHORT).show();
                            	finish();
                            	
                            }
                        })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        /*Toast.makeText(getActivity(), "Cancel", Toast.LENGTH_SHORT)
                                .show();
                    	ItemProducto prod = products.get(pos);
                    	prod.setCantidad(cantFinal);
                    	products.set(pos,prod);
                    	finish();
                    	
                    }
                });*/
        
        builder.create();
        builder.show();
 
	}
	/*@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}*/

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()) {
			case R.id.button_save:
				Intent intent = new Intent(this,MostrarProductosCategoria.class);
				ItemProducto prod = products.get(pos);
				intent.putExtra("posicion",pos);
				intent.putExtra("cantProduct",prod.getCantidad());
				intent.putExtra("key",1);
				//intent.putExtra("productos",products);
				startActivity(intent);
				break;
			
			case R.id.button_cancel:
				ItemProducto produ = products.get(pos);
            	produ.setCantidad(cantFinal);
            	products.set(pos,produ);
            	Intent i = new Intent(this,MostrarProductosCategoria.class);
				i.putExtra("posicion",pos);
				i.putExtra("cantProduct",produ.getCantidad());
				i.putExtra("key",1);
				startActivity(i);
				break;
			
			case R.id.textView_Atras:
				Intent j = new Intent(this,AccesoDespensa.class);
				startActivity(j);
				break;
				
		}
            	
				
	}
	
	private void initializeArrayList() {
		products.add(new ItemProducto(0,"Leche Asturiana Entera",5,""));
		products.add(new ItemProducto(1,"Leche Asturiana Desnatada",4,""));
		products.add(new ItemProducto(2,"Leche Asturiana Semidesnatada",3,""));
		products.add(new ItemProducto(3,"Yogures Naturales Danone",4,""));
		products.add(new ItemProducto(4,"Natillas Chocolate Danone",2,""));
		products.add(new ItemProducto(5,"Queso Semicurado El Ventero",1,""));
		
	}
	
	public void decrementCant(View view) {
		/*cantProduct = (TextView) view.findViewById(R.id.cantProduct);
		int cant = Integer.parseInt(cantProduct.getText().toString());
		int cantModified = cant--;
		cantProduct.setText(cantModified + "");*/
		
		ItemProducto product = products.get(pos);
		if (product.getCantidad() > 0) {
			product.setCantidad(product.getCantidad()-1);
			products.set(pos,product);
			modificarProducto(view,pos);
		}
		
	}
	
	public void incrementCant(View view) {
		/*cantProduct = (TextView) view.findViewById(R.id.cantProduct);
		int cant = Integer.parseInt(cantProduct.getText().toString());
		int cantModified = cant--;
		cantProduct.setText(cantModified + "");*/
		
		ItemProducto product = products.get(pos);
		product.setCantidad(product.getCantidad()+1);
		products.set(pos,product);
		modificarProducto(view,pos);
		
	}
	
	public void errorProduct() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		 
	    builder.setTitle("Error")
	            .setIcon(
	                    getResources().getDrawable(
	                            R.drawable.close))
	            .setMessage("Producto ya existente en la despensa")
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
