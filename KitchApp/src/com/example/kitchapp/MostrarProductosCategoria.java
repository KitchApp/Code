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
import android.database.sqlite.SQLiteDatabase;
<<<<<<< HEAD
=======
import android.speech.RecognizerIntent;
>>>>>>> Rama-Edu-Android
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MostrarProductosCategoria extends Activity implements OnClickListener {
	
	private ListView list;
    private ArrayList<ItemProducto> products;
    private int pos;
    private TextView cantProduct;
    private Button save;
    private Button cancel;
    private Button decrement;
    private Button increment;
    private int cantFinal;
    private AlertDialog.Builder builder;
<<<<<<< HEAD
    Handler_Sqlite helper=new Handler_Sqlite(this);
=======
    private Integer tipoCat;
    Handler_Sqlite helper=new Handler_Sqlite(this);
    private static final int REQUEST_CODE = 1234;
	Dialog match_text_dialog;
	ListView textlist;
	ArrayList<String> matches_text;
>>>>>>> Rama-Edu-Android
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mostrar_productos_categoria);
		products = new ArrayList<ItemProducto>();
		
		
<<<<<<< HEAD
		/*SQLiteDatabase tmp=helper.open();	
=======
		/*SQLiteDatabase tmp = helper.open();	
>>>>>>> Rama-Edu-Android
		if (tmp!=null){
				helper.insertProducto("Yogurt",2,1);
				helper.insertProducto("Manzana",3,2 );
				helper.insertProducto("Magdalena",5,3);
				helper.insertProducto("Leche",6,1);
				helper.insertProducto("coca-cola", 4, 4);
				helper.insertProducto("Pollo", 1, 5);
				helper.insertProducto("Merluza", 1, 6);
				helper.insertProducto("Red bull", 4, 4);
				helper.insertProducto("Arroz largo", 4, 8);
				helper.insertProducto("Guisantes", 1, 9);
				helper.insertProducto("Gel baño", 4, 10);
				helper.insertProducto("Macarrones", 2, 8);
				helper.insertProducto("Helado Fresa", 4, 9);
<<<<<<< HEAD
=======
		}*/
>>>>>>> Rama-Edu-Android
				/*helper.insertCategory(1,"Lácteos")
				helper.insertCategory(2,"Frutas y Verduras")
				helper.insertCategory(3,"Pan y Bollería")
				helper.insertCategory(4,"Bebidas")
				helper.insertCategory(5,"Carnes")
				helper.insertCategory(6,"Pescados")
				helper.insertCategory(7,"Salsas y condimentos")
				helper.insertCategory(8,"Arroces")
				helper.insertCategory(9,"Congelados")
				helper.insertCategory(10,"Varios")*/
		
<<<<<<< HEAD
		Bundle extraLacteos= this.getIntent().getExtras();
		if(extraLacteos!=null){
			Integer lacteos=extraLacteos.getInt("lacteos");
			initializeArrayList(lacteos);
=======
		Bundle extras= this.getIntent().getExtras();
		if(extras!=null){
			tipoCat=extras.getInt("idCat");
			initializeArrayList(tipoCat);
			TextView title = (TextView) findViewById(R.id.textView_Cat);
			switch (tipoCat) {
				case 1:
					title.setText("Lácteos");
					break;
				
				case 2:
					title.setText("Frutas y Verduras");
					break;

				case 3:
					title.setText("Pan y Bollería");
					break;

				case 4:
					title.setText("Bebidas");
					break;

				case 5:
					title.setText("Carnes");
					break;

				case 6:
					title.setText("Pescados");
					break;

				case 7:
					title.setText("Salsas y Condimentos");
					break;

				case 8:
					title.setText("Pastas y Arroces");
					break;

				case 9:
					title.setText("Congelados");
					break;

				case 10:
					title.setText("Varios");
					break;

					
			}
>>>>>>> Rama-Edu-Android
			
		}
			
			
		//}		
			
		
<<<<<<< HEAD
		helper.close();
=======
		//helper.close();
>>>>>>> Rama-Edu-Android
		
		
		
		Bundle extra = this.getIntent().getExtras();
		
		
<<<<<<< HEAD
		if (extra!=null) {
			int key = extra.getInt("key");
			if (key == 0) {
				
=======
		if (extra!=null && extra.getInt("key") != 0) {
			int key = extra.getInt("key");
			if (key == 1) {
				//initializeArrayList(tipoCat);
>>>>>>> Rama-Edu-Android
				ItemProducto item = new ItemProducto(products.size(),extra.getString("nameProduct"),extra.getInt("cantProduct"));
				boolean encontrado = false;
				int i = 0;
				while (i<products.size() && !encontrado){
					ItemProducto prod = products.get(i);
					String name = prod.getNombre().toLowerCase();
					if (name.equals(item.getNombre().toLowerCase())) {
						encontrado = true;
					}
					i++;
				}
				/*if (!encontrado) {
					products.add(item);
					SQLiteDatabase tmp = helper.open();	
					if (tmp!=null){
						helper.insertProducto(item.getNombre(),item.getCantidad(),tipoCat);
						helper.close();
					}
					
				}
				else {
					errorProduct();
				}*/
			}
			else {
				int posi = extra.getInt("posicion");
				int cantProducto = extra.getInt("cantProduct");
				ItemProducto producto = products.get(posi);
				producto.setCantidad(cantProducto);
				products.set(posi,producto);
			}
		}
		
<<<<<<< HEAD
		TextView link_atras = (TextView) findViewById(R.id.textView_Atras);
		link_atras.setOnClickListener(this);
=======
		/*TextView link_atras = (TextView) findViewById(R.id.textView_Atras);
		link_atras.setOnClickListener(this);*/
		Button button_add=(Button)findViewById(R.id.button_add);
		button_add.setOnClickListener(this);
>>>>>>> Rama-Edu-Android
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
    	decrement = (Button) view.findViewById(R.id.button_decrement);
    	decrement.setOnClickListener(this);
    	increment = (Button) view.findViewById(R.id.button_increment);
    	increment.setOnClickListener(this);
        builder.setView(view);
                
        
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
			
			/*case R.id.textView_Atras:
				Intent j = new Intent(this,AccesoDespensa.class);
				startActivity(j);
				break;*/
				
			case R.id.button_decrement:
				decrementCant(v);
				break;
				
			case R.id.button_increment:
				incrementCant(v);
				break;
				
			case R.id.button_add:

				alertDialog(v);
				break;
				
		}
            	
				
	}
	
	private void initializeArrayList(Integer category) {
	
		
		products=helper.read(category);
		
		/*products.add(new ItemProducto(1,helper.read()[1],4,""));
		products.add(new ItemProducto(2,helper.read()[2],3,""));
		products.add(new ItemProducto(3,helper.read()[4],4,""));
		products.add(new ItemProducto(4,"Natillas Chocolate Danone",2,""));
		products.add(new ItemProducto(5,"Queso Semicurado El Ventero",1,""));*/
		
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
			cantProduct.setText(product.getCantidad() + "");
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
		cantProduct.setText(product.getCantidad() + "");
		
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
	
	public void alertDialog(View v){
		final String [] items = new String[] {"Manualmente", "Voz", "Código de barras" };
	    final Integer[] icons = new Integer[] {R.drawable.teclado_android, R.drawable.microfono, R.drawable.barras};
	    ListAdapter adapter = new ItemAdapter(this, items, icons);
	    
        new AlertDialog.Builder(this).setAdapter(adapter, new DialogInterface.OnClickListener() {
        	public void onClick(DialogInterface dialog, int item ) {
        		if (item==0)
        			addManualmente();
        		else if (item == 1) {
        			addVoice();
        		}
        			
	        }
	    }).show();
	}
	
	
	public void addManualmente() {
		  Intent intent = new Intent(this,AddManualmente.class);
		  intent.putExtra("idCat",tipoCat);
		  startActivity(intent);
	  }
	
	public void addVoice() {
		if(isConnected()){
       	 Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        	 intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
        	 RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        	 startActivityForResult(intent, REQUEST_CODE);
       			 }
       	else{
       		Toast.makeText(getApplicationContext(), "Please Connect to Internet", Toast.LENGTH_LONG).show();
       	}
	}
	
	public boolean isConnected() {
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo net = cm.getActiveNetworkInfo();
	    if (net!=null && net.isAvailable() && net.isConnected()) {
	        return true;
	    } else {
	        return false; 
	    }
	}
	
	@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
     if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
    	    
     match_text_dialog = new Dialog(MostrarProductosCategoria.this);
     match_text_dialog.setContentView(R.layout.dialog_matches);
     match_text_dialog.setTitle("Select Matching Text");
     textlist = (ListView)match_text_dialog.findViewById(R.id.listDialogVoice);
     matches_text = data
		     .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
     ArrayAdapter<String> adapter =    new ArrayAdapter<String>(this,
    	     android.R.layout.simple_list_item_1, matches_text);
     textlist.setAdapter(adapter);
     textlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
     @Override
     public void onItemClick(AdapterView<?> parent, View view,
                             int position, long id) {
    	 
    	 match_text_dialog.hide();
    	 addProduct(position);
     }
 });
     
     match_text_dialog.show();
     
     }
     super.onActivityResult(requestCode, resultCode, data);
    }
	
	public void addProduct(int position) {
		try {
			//Intent intent = new Intent(this,MostrarProductosCategoria.class);
   	 		String[] prod = matches_text.get(position).split("");
   	 		int cant = Integer.parseInt(prod[prod.length - 1]);
   	 		String name = "";
   	 		for (int i = 0;i < prod.length - 1;i++) {
   	 			name += prod[i];
   	 			name += "";
   	 		}
   	 	initializeArrayList(tipoCat);
		ItemProducto item = new ItemProducto(products.size(),name,cant);
		boolean encontrado = false;
		int i = 0;
		while (i<products.size() && !encontrado){
			ItemProducto product = products.get(i);
			String nameP = product.getNombre().toLowerCase();
			if (nameP.equals(item.getNombre().toLowerCase())) {
				encontrado = true;
			}
			i++;
		}
		if (!encontrado) {
			products.add(item);
			SQLiteDatabase tmp = helper.open();	
			if (tmp!=null){
				helper.insertProducto(item.getNombre(),item.getCantidad(),tipoCat);
				helper.close();
			}
			
		}
		else {
			errorProduct();
		}
   	 		//intent.putExtra("key",1);
   	 		//intent.putExtra("nameProduct",name);
   	 		//intent.putExtra("cantProduct", cant);
   	 		//startActivity(intent);
		}
		catch (NumberFormatException e) {
			Toast.makeText(this, "La cantidad de producto especificada tiene que ser un número", Toast.LENGTH_SHORT).show();
		}
	}

}
