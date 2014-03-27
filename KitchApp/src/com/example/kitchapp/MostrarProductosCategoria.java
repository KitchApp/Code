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
    private int cantFinal;
    private AlertDialog.Builder builder;
<<<<<<< HEAD
    Handler_Sqlite helper=new Handler_Sqlite(this);
=======
    private Integer tipoCat;
    private static final int REQUEST_CODE = 1234;
    Handler_Sqlite helper;
	Dialog match_text_dialog;
	ListView textlist;
	ArrayList<String> matches_text;
>>>>>>> Rama-Edu-Android
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mostrar_productos_categoria);
		helper=new Handler_Sqlite(this);
		products = new ArrayList<ItemProducto>();
		
		
<<<<<<< HEAD
<<<<<<< HEAD
		/*SQLiteDatabase tmp=helper.open();	
=======
		/*SQLiteDatabase tmp = helper.open();	
>>>>>>> Rama-Edu-Android
=======
		/*SQLiteDatabase tmp=helper.open();	
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
<<<<<<< HEAD
=======
		}*/
>>>>>>> Rama-Edu-Android
				/*helper.insertCategory(1,"Lácteos")
=======
		}
		helper.close();*/
				/*helper.insertCategory(1,"Lï¿½cteos")
>>>>>>> Rama-Edu-Android
				helper.insertCategory(2,"Frutas y Verduras")
				helper.insertCategory(3,"Pan y Bollerï¿½a")
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
<<<<<<< HEAD
			Integer lacteos=extraLacteos.getInt("lacteos");
			initializeArrayList(lacteos);
=======
		Bundle extras= this.getIntent().getExtras();
		if(extras!=null){
			tipoCat=extras.getInt("idCat");
			SQLiteDatabase tmp = helper.open();
			if (tmp != null) {
				initializeArrayList(tipoCat);
				helper.close();
			}
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
			
=======
			if(extraLacteos.getInt("lacteos")==1){
				initializeArrayList(extraLacteos.getInt("lacteos"));
			}
			else if(extraLacteos.getInt("frutas")==2){
				initializeArrayList(extraLacteos.getInt("frutas"));
			}
			else if(extraLacteos.getInt("pan")==3){
					initializeArrayList(extraLacteos.getInt("pan"));
				}
			else if(extraLacteos.getInt("bebidas")==4){
				initializeArrayList(extraLacteos.getInt("bebidas"));
			}
			else if(extraLacteos.getInt("carnes")==5){
				initializeArrayList(extraLacteos.getInt("carnes"));
			}
			else  if(extraLacteos.getInt("pescados")==6){
				initializeArrayList(extraLacteos.getInt("pescados"));
			}
			else  if(extraLacteos.getInt("salsas")==7){
				initializeArrayList(extraLacteos.getInt("salsas"));
			}
			else if(extraLacteos.getInt("arroces")==8){
				initializeArrayList(extraLacteos.getInt("arroces"));
			}
			else  if(extraLacteos.getInt("congelados")==9){
				initializeArrayList(extraLacteos.getInt("congelados"));
			}
			else  if(extraLacteos.getInt("varios")==10){
				initializeArrayList(extraLacteos.getInt("varios"));
			}
				
>>>>>>> 3595f17192c1223c6fcb9aab83ebc4a75ab32ca2
		}
			
			
		//}		
			
		
<<<<<<< HEAD
		helper.close();
=======
		//helper.close();
>>>>>>> Rama-Edu-Android
		
		
		
		Bundle extra = this.getIntent().getExtras();
		
<<<<<<< HEAD
		
<<<<<<< HEAD
		if (extra!=null) {
			int key = extra.getInt("key");
			if (key == 0) {
				
=======
=======
>>>>>>> Rama-Edu-Android
		if (extra!=null && extra.getInt("key") != 0) {
			int key = extra.getInt("key");
			if (key == 1) {
				//initializeArrayList(tipoCat);
>>>>>>> Rama-Edu-Android
				ItemProducto item = new ItemProducto(products.size(),extra.getString("nameProduct"),extra.getInt("cantProduct"));
				boolean encontrado = false;
				int i = 0;
				int cantProductModify = 0;
				while (i<products.size() && !encontrado){
					ItemProducto prod = products.get(i);
					String name = prod.getNombre().toLowerCase();
					if (name.equals(item.getNombre().toLowerCase())) {
						encontrado = true;
						cantProductModify = prod.getCantidad() + item.getCantidad();
					}
					i++;
				}
				/*if (!encontrado) {
					products.add(item);
					SQLiteDatabase tmc = helper.open();	
					if (tmc!=null){
						helper.insertProducto(item.getNombre(),item.getCantidad(),tipoCat);
						helper.close();
					}
					
				}
				else {
<<<<<<< HEAD
					errorProduct();
				}*/
=======
					modifyProduct();
					products.get(i-1).setCantidad(cantProductModify);
					SQLiteDatabase tmc = helper.open();	
					if (tmc!=null){
						helper.updateProduct(item.getNombre(),cantProductModify);
						helper.close();
					}
					
				}
>>>>>>> Rama-Edu-Android
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
				ItemProducto prod = products.get(pos);
				prod.setCantidad(Integer.parseInt(cantProduct.getText().toString()));
				products.set(pos,prod);
				SQLiteDatabase tmp = helper.open();
				if (tmp != null) {
					helper.updateProduct(prod.getNombre(),prod.getCantidad());
					helper.close();
				}
				Intent j = new Intent(this,MostrarProductosCategoria.class);
				j.putExtra("idCat",tipoCat);
				startActivity(j);
				
				break;
			
			case R.id.button_cancel:
            	Intent i = new Intent(this,MostrarProductosCategoria.class);
            	i.putExtra("idCat",tipoCat);
				startActivity(i);
				break;
			
			/*case R.id.textView_Atras:
				Intent j = new Intent(this,AccesoDespensa.class);
				startActivity(j);
				break;*/
				
<<<<<<< HEAD
			case R.id.button_decrement:
				decrementCant(v);
				break;
				
			case R.id.button_increment:
				incrementCant(v);
				break;
				
			case R.id.button_add:

				alertDialog(v);
				break;
				
=======
>>>>>>> 3595f17192c1223c6fcb9aab83ebc4a75ab32ca2
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
		
<<<<<<< HEAD
		ItemProducto product = products.get(pos);
		if (product.getCantidad() > 0) {
			product.setCantidad(product.getCantidad()-1);
			products.set(pos,product);
			modificarProducto(view,pos);
=======
		if (Integer.parseInt(cantProduct.getText().toString()) > 0) {
	        cantProduct.setText(Integer.parseInt(cantProduct.getText().toString())-1 + "");

>>>>>>> Rama-Edu-Android
		}
		
	}
	
	public void incrementCant(View view) {
		/*cantProduct = (TextView) view.findViewById(R.id.cantProduct);
		int cant = Integer.parseInt(cantProduct.getText().toString());
		int cantModified = cant--;
		cantProduct.setText(cantModified + "");*/
		
<<<<<<< HEAD
		ItemProducto product = products.get(pos);
		product.setCantidad(product.getCantidad()+1);
		products.set(pos,product);
		modificarProducto(view,pos);
=======
        cantProduct.setText(Integer.parseInt(cantProduct.getText().toString())+1 + "");

>>>>>>> Rama-Edu-Android
		
	}
	
	public void modifyProduct() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		 
	    builder.setTitle("Información")
	            .setIcon(
	                    getResources().getDrawable(
	                            android.R.drawable.ic_dialog_info))
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
   	 		if (cant <= 0) {
   	 			errorCantVoice();
   	 		}
   	 		else {
   	 			String name = "";
   	 			for (int i = 0;i < prod.length - 1;i++) {
   	 				name += prod[i];
   	 				name += "";
   	 			}
   	 			initializeArrayList(tipoCat);
   	 			ItemProducto item = new ItemProducto(products.size(),name,cant);
   	 			boolean encontrado = false;
   	 			int i = 0;
   	 			int cantProductModify = 0;
   	 			while (i<products.size() && !encontrado){
   	 				ItemProducto product = products.get(i);
   	 				String nameP = product.getNombre().toLowerCase();
   	 				if (nameP.equals(item.getNombre().toLowerCase())) {
   	 					encontrado = true;
   	 					cantProductModify = product.getCantidad() + item.getCantidad();
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
   	 				modifyProduct();
   	 				products.get(i-1).setCantidad(cantProductModify);
   	 				SQLiteDatabase tmp = helper.open();
   	 				if (tmp!=null) {
   	 					helper.updateProduct(item.getNombre(),cantProductModify);
   	 					helper.close();
   	 				}
   	 			}
   	 	
		/*Intent intent = new Intent(this,MostrarProductosCategoria.class);
		startActivity(intent);*/
   	 			ItemProductoAdapter adapter = new ItemProductoAdapter(this,products);
   	 			list.setAdapter(adapter);
   	 		}
		
		
		}
		catch (NumberFormatException e) {
			errorCantVoice();
		}
	}
	
	public void errorCantVoice() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		 
	    builder.setTitle("Error")
	            .setIcon(
	                    getResources().getDrawable(
	                            R.drawable.close))
	            .setMessage("La cantidad del producto introducida tiene que ser un número mayor que cero")
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
