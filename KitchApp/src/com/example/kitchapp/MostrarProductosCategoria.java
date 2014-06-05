package com.example.kitchapp;

import java.util.ArrayList;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.speech.RecognizerIntent;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;


public class MostrarProductosCategoria extends ActionBarActivity implements OnClickListener {
	
    private ListView list;
    private ArrayList<ItemProducto> products;
    private int pos;
    private boolean pressedButtonDelete;
    private MenuItem item_add;
    private EditText cantProduct;
    private EditText nameProduct;
    private Button save;
    private Button cancel;
    private Button decrement;
    private Button increment;
    private Button accept;
    private Button cancelUnits;
    private Spinner unitsProduct;
    private String units;
    private String productName;
    private int productCant;
    private String productBarcode;
    private boolean addVoice;
    private AlertDialog.Builder builder;
    private Integer tipoCat;
    private static final int REQUEST_CODE = 1234;
    Handler_Sqlite helper;
	Dialog match_text_dialog;
	ListView textlist;
	ArrayList<String> matches_text;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mostrar_productos_categoria);
		pressedButtonDelete = false;
		helper=new Handler_Sqlite(this);
		products = new ArrayList<ItemProducto>();
		
<<<<<<< HEAD
<<<<<<< HEAD
		
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
		/*SQLiteDatabase tmp=helper.open();	
=======
		/*SQLiteDatabase tmp = helper.open();	
>>>>>>> Rama-Edu-Android
=======
		/*SQLiteDatabase tmp=helper.open();	
>>>>>>> Rama-Edu-Android
=======
		/*SQLiteDatabase tmp = helper.open();	
		
>>>>>>> 0eeb76638ed6d7066f13a91e1147d0ce22aab4a7
		if (tmp!=null){
				
				helper.insertProducto("Yogurt",2,1,null);
				helper.insertProducto("Manzana",3,2,null);
				helper.insertProducto("Magdalena",5,3,null);
				helper.insertProducto("Leche",6,1);
<<<<<<< HEAD
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
=======
				helper.insertProducto("coca-cola",4,4,null);
=======
				/*helper.insertProducto("coca-cola",4,4,null);
>>>>>>> Rama-Edu-Android
=======
				/*helper.insertProducto("coca-cola",4,4,null);
>>>>>>> 32477ed215e82d01b6f7e658596c4fb24f018cf5
				helper.insertProducto("Pollo",1,5,null);
				helper.insertProducto("Merluza",1,6,null);
				helper.insertProducto("Red bull",4,4,null);
				helper.insertProducto("Arroz largo",4,8,null);
				helper.insertProducto("Guisantes",1,9,null);
				helper.insertProducto("Gel baï¿½o",4,10,null);
				helper.insertProducto("Macarrones",2,8,null);
				helper.insertProducto("Helado Fresa",4,9,null);
		}*/
<<<<<<< HEAD
<<<<<<< HEAD
>>>>>>> 0eeb76638ed6d7066f13a91e1147d0ce22aab4a7
=======
>>>>>>> Rama-Edu-Android
=======
>>>>>>> 32477ed215e82d01b6f7e658596c4fb24f018cf5
=======
>>>>>>> Rama-Edu-Android
=======

>>>>>>> bc19c81f8e762b2026442b06370439486d3b88f6
=======
>>>>>>> Rama-Edu-Android
=======
>>>>>>> eb9e561c31a6809bd723ad4dc8cbc927e80754b1
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
					title.setText("Lacteos");
					break;
				
				case 2:
					title.setText("Frutas y Verduras");
					break;

				case 3:
					title.setText("Pan y Bolleria");
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
					title.setText("Condimentos");
					break;

				case 8:
					title.setText("Pastas y Arroces");
					break;

				case 9:
					title.setText("Congelados");
					break;

				case 10:
					title.setText("Salsas");
					break;

				case 11:
<<<<<<< HEAD
<<<<<<< HEAD
					title.setText("Droguerï¿½a");
=======
					title.setText("Drogueria");
>>>>>>> Rama-Edu-Android
=======
					title.setText("Drogueria");
>>>>>>> eb9e561c31a6809bd723ad4dc8cbc927e80754b1
					break;
			
				case 12:
					title.setText("Varios");
					break;	

			}
		}
			
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
			
<<<<<<< HEAD
<<<<<<< HEAD
		
			
=======

>>>>>>> Rama-Edu-Android
		
			
=======

		
			
>>>>>>> 32477ed215e82d01b6f7e658596c4fb24f018cf5
=======
>>>>>>> Rama-Edu-Android
=======

>>>>>>> bc19c81f8e762b2026442b06370439486d3b88f6

	//helper.close();
		
		
		
		Bundle extra = this.getIntent().getExtras();
		
		if (extra!=null && extra.getInt("key") != 0) {
			int key = extra.getInt("key");
			if (key == 1) {
				//initializeArrayList(tipoCat);
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
				if (!encontrado) {
					products.add(item);
					SQLiteDatabase tmp1 = helper.open();	
					if (tmp1!=null){
						helper.insertProducts(item.getNombre(),item.getCantidad(),tipoCat,"");
<<<<<<< HEAD
<<<<<<< HEAD

<<<<<<< HEAD
<<<<<<< HEAD
>>>>>>> 0eeb76638ed6d7066f13a91e1147d0ce22aab4a7
=======
>>>>>>> Rama-Edu-Android
=======
>>>>>>> 32477ed215e82d01b6f7e658596c4fb24f018cf5
=======
>>>>>>> Rama-Edu-Android
=======
>>>>>>> bc19c81f8e762b2026442b06370439486d3b88f6
						helper.close();
					}
					
				}
				else {
					modifyProduct();
					products.get(i-1).setCantidad(cantProductModify);
					SQLiteDatabase tmc = helper.open();	
					if (tmc!=null){
						helper.updateProduct(item.getNombre(),item.getNombre(),cantProductModify);
						helper.close();
					}
					
				}
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
>>>>>>> Rama-Edu-Android
=======
				}
			}
			else {
				int posi = extra.getInt("posicion");
				int cantProducto = extra.getInt("cantProduct");
				ItemProducto producto = products.get(posi);
				producto.setCantidad(cantProducto);
				products.set(posi,producto);
>>>>>>> 0eeb76638ed6d7066f13a91e1147d0ce22aab4a7
=======
				
		
>>>>>>> Rama-Edu-Android
=======
				
		
>>>>>>> 32477ed215e82d01b6f7e658596c4fb24f018cf5
=======
>>>>>>> Rama-Edu-Android
=======
				
		
>>>>>>> bc19c81f8e762b2026442b06370439486d3b88f6
			}

		}
		
		/*TextView link_atras = (TextView) findViewById(R.id.textView_Atras);
		link_atras.setOnClickListener(this);*/
		Button button_add=(Button)findViewById(R.id.button_add);
		button_add.setOnClickListener(this);
=======
>>>>>>> Rama-Edu-Android
=======
>>>>>>> eb9e561c31a6809bd723ad4dc8cbc927e80754b1
		list = (ListView)findViewById(R.id.listViewProducts);
		ItemProductoAdapter adapter;
		// Inicializamos el adapter.
		adapter = new ItemProductoAdapter(this,products);
		// Asignamos el Adapter al ListView, en este punto hacemos que el
		// ListView muestre los datos que queremos.
		list.setAdapter(adapter);
		list.setOnItemClickListener(new OnItemClickListener(){
			 
		    @Override
		    public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
		        // TODO Auto-generated method stub
		        
		        	modificarProducto(arg1,position);
		 
		    }
		 
		}); 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_action_bar, menu);
        item_add = menu.findItem(R.id.add_Product);
        return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		switch (item.getItemId()) {
<<<<<<< HEAD
=======
		
			case R.id.home:
				Intent intent = new Intent(this, PantallaPrincipal.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
				return true;
			
>>>>>>> eb9e561c31a6809bd723ad4dc8cbc927e80754b1
			case R.id.add_Product:
				alertDialogListView(true,0);
				return true;
				
			case R.id.delete_Product:
				if (pressedButtonDelete) {
					item_add.setEnabled(true);
					deleteProducts();
					hideCheckBox();
					pressedButtonDelete = false;
				}
				else {
					item_add.setEnabled(false);
					showCheckBox();
					pressedButtonDelete = true;
				}
				return true;
				
<<<<<<< HEAD
=======
			case R.id.logout:
				SharedPreferences settings = getSharedPreferences(PantallaTransicion.PREFS_NAME, 0);
				SharedPreferences.Editor editor = settings.edit();
		
				editor.putBoolean("hasLoggedIn", false);
				editor.commit();
		
				Intent j = new Intent(this, Login.class);
				j.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(j);
				finish();
				return true;
				
>>>>>>> eb9e561c31a6809bd723ad4dc8cbc927e80754b1
			default:
				return super.onOptionsItemSelected(item);
		}
		
	}
	
	public void modificarProducto(View view,int position) {
		
		builder = new AlertDialog.Builder(this);
		 
        // Get the layout inflater
        LayoutInflater inflater = this.getLayoutInflater();
 
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout

        view = inflater.inflate(R.layout.activity_modificar_producto_despensa, null);
        nameProduct = (EditText) view.findViewById(R.id.nameProductModify);
        nameProduct.setText(products.get(position).getNombre());
        cantProduct = (EditText) view.findViewById(R.id.cantProduct);
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

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()) {
			case R.id.button_save:
				ItemProducto prod = products.get(pos);
				String nameLast = prod.getNombre();
				prod.setNombre(nameProduct.getText().toString());
				prod.setCantidad(Integer.parseInt(cantProduct.getText().toString()));
				products.set(pos,prod);
				SQLiteDatabase tmp = helper.open();
				if (tmp != null) {
					helper.updateProduct(nameLast,prod.getNombre(),prod.getCantidad(),"updatePantry",1);
					helper.close();
				}
				Intent j = new Intent(this,MostrarProductosCategoria.class);
				j.putExtra("idCat",tipoCat);
				startActivity(j);
				finish();
				
				break;
			
			case R.id.button_cancel:
            	Intent i = new Intent(this,MostrarProductosCategoria.class);
            	i.putExtra("idCat",tipoCat);
				startActivity(i);
				finish();
				break;
				
			case R.id.button_decrement:
				decrementCant(v);
				break;
				
			case R.id.button_increment:
				incrementCant(v);
				break;
				
			case R.id.button_acceptPantry:
				String unit = "";
	 			if (units.equals("Litros")) {
					unit = "l";
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
				else if (units.equals("Unidades")) {
					unit = "unid";
				}
	 			addProductPantry(unit);
<<<<<<< HEAD
				break;
			
			case R.id.buttonCancelPantry:
				Intent intent = new Intent(this,MostrarProductosCategoria.class);
				intent.putExtra("idCat",tipoCat);
				startActivity(intent);
				finish();
				break;
=======
				break;
			
			case R.id.buttonCancelPantry:
				Intent intent = new Intent(this,MostrarProductosCategoria.class);
				intent.putExtra("idCat",tipoCat);
				startActivity(intent);
				finish();
				break;
>>>>>>> eb9e561c31a6809bd723ad4dc8cbc927e80754b1
				
				
		}
            	
				
	}
	
	private void initializeArrayList(Integer category) {
	
		products=helper.readProducts(category,"readPantry");
		
	}
	
	public void decrementCant(View view) {
		
		if (Integer.parseInt(cantProduct.getText().toString()) > 0) {
	        cantProduct.setText(Integer.parseInt(cantProduct.getText().toString())-1 + "");

		}
		
	}
	
	public void incrementCant(View view) {
		
        cantProduct.setText(Integer.parseInt(cantProduct.getText().toString())+1 + "");

		
	}
	
	public void modifyProduct() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		 
	    builder.setTitle("Informacion")
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
	
	public void alertDialogListView(boolean full, int except){
		
	    if(full & except==0){
	    	final String [] items = new String[] {"Manualmente", "Voz", "Codigo de barras" };
		    final Integer[] icons = new Integer[] {R.drawable.teclado_android, R.drawable.microfono, R.drawable.barras};
		    ListAdapter adapter = new ItemAdapter(this, items, icons);
		    
	    	new AlertDialog.Builder(this).setAdapter(adapter, new DialogInterface.OnClickListener() {
	        	public void onClick(DialogInterface dialog, int item ) {
		        		if (item==0)
		        			addManualmente();
		        		else if (item == 1) {
		        			addVoice = true;
		        			addVoice();
		        		}
		        		else if(item==2){
		        			addVoice = false;
		        			addBarCode();
		        		}
	        			
		        }
		    }).show();
	    }
	    else{
	    	if(except==1){
	    		final String [] items = new String[] {"Voz", "Codigo de barras" };
	    	    final Integer[] icons = new Integer[] {R.drawable.microfono, R.drawable.barras};
	    	    ListAdapter adapter = new ItemAdapter(this, items, icons);
	    	    
	    		new AlertDialog.Builder(this).setAdapter(adapter, new DialogInterface.OnClickListener() {
		        	public void onClick(DialogInterface dialog, int item ) {
			        		if (item==0) {
			        			addVoice = true;
			        			addVoice();
			        		}
			        		else if (item == 1) {
			        			addVoice = false;
			        			addBarCode();
			        		}        			
			        }
			    }).show();
	    	}
	    	else if(except==2){
	    		final String [] items = new String[] {"Manualmente", "Codigo de barras" };
	    	    final Integer[] icons = new Integer[] {R.drawable.teclado_android, R.drawable.barras};
	    	    ListAdapter adapter = new ItemAdapter(this, items, icons);
	    	    
	    		new AlertDialog.Builder(this).setAdapter(adapter, new DialogInterface.OnClickListener() {
		        	public void onClick(DialogInterface dialog, int item ) {
			        		if (item==0)
			        			addManualmente();
			        		else if (item == 1) {
			        			addVoice = false;
			        			addBarCode();
			        		}        			
			        }
			    }).show();
	    	}
	    	else if(except==3){
	    		final String [] items = new String[] {"Manualmente", "Voz" };
	    	    final Integer[] icons = new Integer[] {R.drawable.teclado_android, R.drawable.microfono};
	    	    ListAdapter adapter = new ItemAdapter(this, items, icons);
	    	    
	    		new AlertDialog.Builder(this).setAdapter(adapter, new DialogInterface.OnClickListener() {
		        	public void onClick(DialogInterface dialog, int item ) {
			        		if (item==0)
			        			addManualmente();
			        		else if (item == 1) {
			        			addVoice = true;
			        			addVoice();
			        		}        			
			        }
			    }).show();
	    	}
	    }

	}
	
	
	public void addManualmente() {
		  Intent intent = new Intent(this,AddManualmente.class);
		  intent.putExtra("idCat",tipoCat);
		  startActivity(intent);
		  finish();
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
	
	public void addBarCode(){
		IntentIntegrator scanIntegrator = new IntentIntegrator(this);

		scanIntegrator.initiateScan();
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
		if (addVoice) {
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
		else {
			IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
			if (scanningResult != null )
				addProductBarCode(scanningResult.getContents());
			else {
				Toast toast = Toast.makeText(getApplicationContext(), 
	   		        "No scan data received!", Toast.LENGTH_SHORT);
	   		    toast.show();
			}
		}
   		
     

    }
	
	
	public void alertDialogReport(String msj) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		 
	    builder.setTitle("Error")
	           .setIcon(
	                    getResources().getDrawable(
	                            R.drawable.close))
	            .setMessage(msj)
	            .setNeutralButton(R.string.ok, new DialogInterface.OnClickListener() {
	 
	                @Override
	                public void onClick(DialogInterface arg0, int arg1) {
	                	alertDialogListView(false,3);
	                }
	            });
	 
	    builder.create();
	    builder.show();
	}
	
	
	public void addProduct(int position) {
		String[] prod = matches_text.get(position).split(" ");
	 	productName = "";
	 	String oneCantM = "un";
	 	String oneCantF = "una";
	 	productCant = 0;
	 	boolean error = false;
   	 	for (int i = 0;i < prod.length;i++) {
   	 			if (productCant == 0) {
   	 				if ((prod[i].equals(oneCantM)) || (prod[i].equals(oneCantF))) {
   	 					productCant = 1;
   	 				}
   	 				else {
   	 					try {
   	 						productCant = Integer.parseInt(prod[i]);
   	 					}
   	 					catch (NumberFormatException e) {
   	 						if (i == prod.length-1) {
   	 							errorCantVoice();
   	 							error = true;
   	 						}
   	 						else {
   	 							productName += prod[i];
   	 							productName += " ";
   	 						}
   	 					}
   	 				}
   	 			}
   	 			else {
   	 				productName += prod[i];
   	 				productName += " ";
   	 			}
   	 			
   	 		}
   	 		if (!error) {
   	 			if (productCant <= 0) {
   	 				errorCantVoice();
   	 			}
   	 			else {
<<<<<<< HEAD
<<<<<<< HEAD
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
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
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
				helper.insertProducts(item.getNombre(),item.getCantidad(),tipoCat,"");

				helper.close();
			}
>>>>>>> 0eeb76638ed6d7066f13a91e1147d0ce22aab4a7
=======

>>>>>>> Rama-Edu-Android
=======
=======
   	 				if (!encontrado) {
   	 					products.add(item);
   	 					SQLiteDatabase tmp = helper.open();	
   	 					if (tmp!=null){
   	 						helper.insertProducts(item.getNombre(),item.getCantidad(),tipoCat,"");
   	 						helper.close();
   	 					}
>>>>>>> Rama-Edu-Android
=======
>>>>>>> bc19c81f8e762b2026442b06370439486d3b88f6

			
   	 				}
   	 				else {
   	 					modifyProduct();
   	 					products.get(i-1).setCantidad(cantProductModify);
   	 					SQLiteDatabase tmp = helper.open();
   	 					if (tmp!=null) {
   	 						helper.updateProduct(item.getNombre(),item.getNombre(),cantProductModify);
   	 						helper.close();
   	 					}
   	 				}
   	 	
		/*Intent intent = new Intent(this,MostrarProductosCategoria.class);
		startActivity(intent);*/
   	 				list = (ListView) findViewById(R.id.listViewProducts);
   	 				ItemProductoAdapter adapter = new ItemProductoAdapter(this,products);
   	 				list.setAdapter(adapter);
=======
   	 				selectUnits();
>>>>>>> Rama-Edu-Android
=======
   	 				selectUnits();
>>>>>>> eb9e561c31a6809bd723ad4dc8cbc927e80754b1
   	 			}
   	 		}
		
	}
	
	public void errorCantVoice() {
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
	
	public void showCheckBox() {
		for (int i=0;i<list.getChildCount();i++) {
			View v = list.getChildAt(i);
			CheckBox check = (CheckBox) v.findViewById(R.id.checkBox_Product);
			check.setVisibility(View.VISIBLE);
		}
	}
	
	public void hideCheckBox() {
		for (int i=0;i<list.getChildCount();i++) {
			View v = list.getChildAt(i);
			CheckBox check = (CheckBox) v.findViewById(R.id.checkBox_Product);
			check.setVisibility(View.INVISIBLE);
		}
	}
	
	public void deleteProducts() {
		for (int i=0;i<products.size();i++) {
			ItemProducto item = products.get(i);
			if (item.isSelected()) {
				products.remove(i);
				i--;
				SQLiteDatabase tmp = helper.open();
				if (tmp!=null) {
					helper.removeProduct(item.getNombre(),"deletePantry",1);
					helper.close();
				}
			}
		}
		list = (ListView) findViewById(R.id.listViewProducts);
		ItemProductoAdapter adapter = new ItemProductoAdapter(this,products);
		list.setAdapter(adapter);
	}
	
	public void selectUnits() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		 
        // Get the layout inflater
        LayoutInflater inflater = this.getLayoutInflater();
 
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout

        View view = inflater.inflate(R.layout.activity_select_units, null);
    	
    	unitsProduct = (Spinner) view.findViewById(R.id.spinnerUnitsPantry);
    	ArrayAdapter<CharSequence> adapterUnits = ArrayAdapter.createFromResource(this, R.array.Unidades, android.R.layout.simple_spinner_item);
    	adapterUnits.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    	unitsProduct.setAdapter(adapterUnits);
    	unitsProduct.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
    		
    		public void onItemSelected (AdapterView<?> parent,
            android.view.View v, int position, long id) {
                units = unitsProduct.getItemAtPosition(position).toString();
    		}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
    	
        accept = (Button) view.findViewById(R.id.button_acceptPantry);
    	accept.setOnClickListener(this);
    	cancelUnits = (Button) view.findViewById(R.id.buttonCancelPantry);
    	cancelUnits.setOnClickListener(this);
    	
        builder.setView(view);
                
        
        builder.create();
        builder.show();
	}
	
	public void addProductPantry(String unit) {
		if (addVoice) {
			ItemProducto item = new ItemProducto(products.size(),productName,productCant,tipoCat,unit,false);
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
					helper.insertProducts(item.getNombre(),item.getCantidad(),tipoCat,unit,"","insertPantry",1);
					helper.close();
				}

			}
			else {
				modifyProduct();
				products.get(i-1).setCantidad(cantProductModify);
				SQLiteDatabase tmp = helper.open();
				if (tmp!=null) {
					helper.updateProduct(item.getNombre(),item.getNombre(),cantProductModify,"updatePantry",1);
					helper.close();
				}
			}
		}
		else {
			ItemProducto item = new ItemProducto(products.size(),productName,1,tipoCat,unit,false);
			products.add(item);
			SQLiteDatabase tmp = helper.open();
			if (tmp!=null) {
				helper.insertProducts(productName, 1, tipoCat,unit,productBarcode,"insertPantry",1);
				helper.close();
			}
		}
		Intent intent = new Intent(this,MostrarProductosCategoria.class);
		intent.putExtra("idCat", tipoCat);
		startActivity(intent);
		finish();
	}
	
	public void addProductBarCode(String barcode) {
		if (!helper.exist(barcode,"products")){
   			if (!helper.exist(barcode,"productsTemporary")){
   				alertDialogReport("Producto no existente");
   			}
   			else{
   					
   				ArrayList<Object> tmp=helper.readProductsTemporary(barcode);
   				productName = (String)tmp.get(0);
   				productBarcode = (String)tmp.get(1);
   				selectUnits();
   					
   			}
		}
   		else{
   			Toast.makeText(this,"Producto ya existente",Toast.LENGTH_SHORT).show();
   		}
			
	}

}
