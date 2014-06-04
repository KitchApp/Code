package com.example.kitchapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class MostrarProductosLista extends ActionBarActivity implements OnClickListener {
	
	private ArrayList<ItemProducto> products;
	private ArrayList<ItemProducto> productsDairy;
	private ArrayList<ItemProducto> productsFruits;
	private ArrayList<ItemProducto> productsBread;
	private ArrayList<ItemProducto> productsDrinks;
	private ArrayList<ItemProducto> productsMeat;
	private ArrayList<ItemProducto> productsFish;
	private ArrayList<ItemProducto> productsCondiment;
	private ArrayList<ItemProducto> productsPasta;
	private ArrayList<ItemProducto> productsFrozen;
	private ArrayList<ItemProducto> productsSauces;
	private ArrayList<ItemProducto> productsStore;
	private ArrayList<ItemProducto> productsOthers;
	private ExpandableListView listProducts;
	private Button accept;
	private Button cancel;
	private Button button_addManually;
	private Button button_addVoice;
	private Button button_addCode;
	private MenuItem item_delete;
	private MenuItem item_update;
	private Spinner category;
	private Spinner unitsProduct;
	private EditText nameProductModified;
	private EditText cantProductModified;
	private Button save;
	private Button cancelModified;
	private Button decrement;
	private Button increment;
	private String cat;
	private int catProduct;
	private int posProduct;
	private String units;
	private String nameProduct;
	private int cantProduct;
	private String barcodeProduct;
	private boolean pressedButtonDelete;
	private boolean pressedButtonUpdate;
	private boolean addVoice;
	private static final int REQUEST_CODE = 1234;
    List<Integer> listDataHeader;
    HashMap<Integer, List<ItemProducto>> listDataChild;
	Dialog match_text_dialog;
	ListView textlist;
	ArrayList<String> matches_text;
	Handler_Sqlite helper;
	int idList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_una_lista);
		helper=new Handler_Sqlite(this);
		pressedButtonDelete = false;
		pressedButtonUpdate = false;
		barcodeProduct = "";
		products = new ArrayList<ItemProducto>();
		productsDairy = new ArrayList<ItemProducto>();
		productsFruits = new ArrayList<ItemProducto>();
		productsBread = new ArrayList<ItemProducto>();
		productsDrinks = new ArrayList<ItemProducto>();
		productsMeat = new ArrayList<ItemProducto>();
		productsFish = new ArrayList<ItemProducto>();
		productsCondiment = new ArrayList<ItemProducto>();
		productsPasta = new ArrayList<ItemProducto>();
		productsFrozen = new ArrayList<ItemProducto>();
		productsSauces = new ArrayList<ItemProducto>();
		productsStore = new ArrayList<ItemProducto>();
		productsOthers = new ArrayList<ItemProducto>();
		
		Bundle extra=this.getIntent().getExtras();
		idList=extra.getInt("idList");
		initializeArrayList(idList);
		arraySorted();
		
		listProducts = (ExpandableListView)findViewById(R.id.listViewProductsList);
		ItemListAdapter adapter;
		adapter = new ItemListAdapter(this,listDataHeader,listDataChild,false,false);
		listProducts.setAdapter(adapter);
		listProducts.setOnChildClickListener(new OnChildClickListener() {

			@Override
			public boolean onChildClick(ExpandableListView arg0, View arg1,
					int positionGroup, int positionChild, long arg4) {
				// TODO Auto-generated method stub
						modificarProducto(arg1,positionGroup,positionChild);
						return true;
			}
		});
		
		button_addManually = (Button) findViewById(R.id.buttonAddManually);
		button_addManually.setOnClickListener(this);
		
		button_addVoice = (Button) findViewById(R.id.buttonAddVoice);
		button_addVoice.setOnClickListener(this);
		
		button_addCode = (Button) findViewById(R.id.buttonAddCode);
		button_addCode.setOnClickListener(this);
		
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.show_products_list, menu);
        item_delete = menu.findItem(R.id.delete_ProductList);
        item_update = menu.findItem(R.id.update_Pantry);
        return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		switch (item.getItemId()) {
			case R.id.update_Pantry:
				if (pressedButtonUpdate) {
					button_addManually.setEnabled(true);
					button_addVoice.setEnabled(true);
					button_addCode.setEnabled(true);
					item_delete.setEnabled(true);
					updatePantry();
					hideCheckBox();
					pressedButtonUpdate = false;
				}
				else {
					button_addManually.setEnabled(false);
					button_addVoice.setEnabled(false);
					button_addCode.setEnabled(false);
					item_delete.setEnabled(false);
					showCheckBox();
					pressedButtonUpdate = true;
				}
				return true;
				
			case R.id.delete_ProductList:
				if (pressedButtonDelete) {
					button_addManually.setEnabled(true);
					button_addVoice.setEnabled(true);
					button_addCode.setEnabled(true);
					item_update.setEnabled(true);
					deleteProducts();
					hideCheckBox();
					pressedButtonDelete = false;
				}
				else {
					button_addManually.setEnabled(false);
					button_addVoice.setEnabled(false);
					button_addCode.setEnabled(false);
					item_update.setEnabled(false);
					showCheckBox();
					pressedButtonDelete = true;
				}
				return true;
			case R.id.home:
				Intent intent = new Intent(this, PantallaPrincipal.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
				return true;
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
				
			default:
				return super.onOptionsItemSelected(item);
		}
		
	}
	
	private void initializeArrayList(Integer idList) {
		
		listDataHeader = new ArrayList<Integer>();
        listDataChild = new HashMap<Integer, List<ItemProducto>>();
		products=helper.readProducts(idList,"readList");
		for (int i = 0; i < products.size(); i++) {
			ItemProducto item = products.get(i);
			switch (item.getCategory()) {
				case 1:
					productsDairy.add(item);
					if (listDataHeader.contains(1)) {
						listDataChild.put(1,productsDairy);

					}
					else {
						listDataHeader.add(1);
						listDataChild.put(1,productsDairy);
					}
					break;
				case 2:
					productsFruits.add(item);
					if (listDataHeader.contains(2)) {
						listDataChild.put(2,productsFruits);

					}
					else {
							listDataHeader.add(2);
							listDataChild.put(2,productsFruits);
					}
					break;
				case 3:
					productsBread.add(item);
					if (listDataHeader.contains(3)) {
						listDataChild.put(3,productsBread);

					}
					else {
							listDataHeader.add(3);
							listDataChild.put(3,productsBread);
					}
					break;
				case 4:
					productsDrinks.add(item);
					if (listDataHeader.contains(4)) {
						listDataChild.put(4,productsDrinks);

					}
					else {
							listDataHeader.add(4);
							listDataChild.put(4,productsDrinks);
					}
					break;
				case 5:
					productsMeat.add(item);
					if (listDataHeader.contains(5)) {
						listDataChild.put(5,productsMeat);

					}
					else {
							listDataHeader.add(5);
							listDataChild.put(5,productsMeat);
					}
					break;
				case 6:
					productsFish.add(item);
					if (listDataHeader.contains(6)) {
						listDataChild.put(6,productsFish);

					}
					else {
							listDataHeader.add(6);
							listDataChild.put(6,productsFish);
					}
					break;
				case 7:
					productsCondiment.add(item);
					if (listDataHeader.contains(7)) {
						listDataChild.put(7,productsCondiment);

					}
					else {
							listDataHeader.add(7);
							listDataChild.put(7,productsCondiment);
					}
					break;
				case 8:
					productsPasta.add(item);
					if (listDataHeader.contains(8)) {
						listDataChild.put(8,productsPasta);

					}
					else {
							listDataHeader.add(8);
							listDataChild.put(8,productsPasta);
					}
					break;
				case 9:
					productsFrozen.add(item);
					if (listDataHeader.contains(9)) {
						listDataChild.put(9,productsFrozen);

					}
					else {
							listDataHeader.add(9);
							listDataChild.put(9,productsFrozen);
					}
					break;
				case 10:
					productsSauces.add(item);
					if (listDataHeader.contains(10)) {
						listDataChild.put(10,productsSauces);

					}
					else {
							listDataHeader.add(10);
							listDataChild.put(10,productsSauces);
					}
					break;
				case 11:
					productsStore.add(item);
					if (listDataHeader.contains(11)) {
						listDataChild.put(11,productsStore);

					}
					else {
							listDataHeader.add(11);
							listDataChild.put(11,productsStore);
					}
					break;
				case 12:
					productsOthers.add(item);
					if (listDataHeader.contains(12)) {
						listDataChild.put(12,productsOthers);

					}
					else {
							listDataHeader.add(12);
							listDataChild.put(12,productsOthers);
					}
					break;
				default:
					break;
			}
		}
	}
	
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
			case R.id.buttonAddManually:
				if (!searchProduct()) {
					selectCategory();
				}
				break;
			case R.id.buttonCancelList:
				Intent intent = new Intent(this,MostrarProductosLista.class);
				intent.putExtra("idList", idList);
				startActivity(intent);
				finish();
				break;
			case R.id.button_acceptList:
				int tipoCat = 0;
				if (cat.equals("Lacteos")) {
					tipoCat = 1;
				}
				else if (cat.equals("Frutas y Verduras")) {
					tipoCat = 2;
				}
				else if (cat.equals("Pan y Bolleria")) {
					tipoCat = 3;
				}
				else if (cat.equals("Bebidas")) {
					tipoCat = 4;
				}
				else if (cat.equals("Carnes")) {
					tipoCat = 5;
				}
				else if (cat.equals("Pescados")) {
					tipoCat = 6;
				}
				else if (cat.equals("Condimentos")) {
					tipoCat = 7;
				}
				else if (cat.equals("Pastas y Arroces")) {
					tipoCat = 8;
				}
				else if (cat.equals("Congelados")) {
					tipoCat = 9;
				}
				else if (cat.equals("Salsas")) {
					tipoCat = 10;
				}
				else if (cat.equals("Drogueria")) {
					tipoCat = 11;
				}
				else if (cat.equals("Varios")) {
					tipoCat = 12;
				}
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
				addManually(tipoCat,unit);
				break;
			case R.id.buttonAddVoice:
				addVoice = true;
				addProductVoice();
				break;	
			case R.id.buttonAddCode:
				addVoice = false;
				addBarCode();
				break;
			case R.id.button_decrement:
				decrementCant(arg0);
				break;
			case R.id.button_increment:
				incrementCant(arg0);
				break;
			case R.id.button_cancel:
				Intent i = new Intent(this,MostrarProductosLista.class);
				i.putExtra("idList",idList);
				startActivity(i);
				finish();
				break;
			case R.id.button_save:
				switch (catProduct) {
					case 1:
						ItemProducto prodDairy = productsDairy.get(posProduct);
						String nameLastDairy = prodDairy.getNombre();
						prodDairy.setNombre(nameProductModified.getText().toString());
						prodDairy.setCantidad(Integer.parseInt(cantProductModified.getText().toString()));
						productsDairy.set(posProduct,prodDairy);
						SQLiteDatabase tmpDairy = helper.open();
						if (tmpDairy != null) {
							helper.updateProduct(nameLastDairy,prodDairy.getNombre(),prodDairy.getCantidad(),"updateList",idList);
							helper.close();
						}
						Intent intentDairy = new Intent(this,MostrarProductosLista.class);
						intentDairy.putExtra("idList",idList);
						startActivity(intentDairy);
						finish();
						break;
					case 2:
						ItemProducto prodFruits = productsFruits.get(posProduct);
						String nameLastFruits = prodFruits.getNombre();
						prodFruits.setNombre(nameProductModified.getText().toString());
						prodFruits.setCantidad(Integer.parseInt(cantProductModified.getText().toString()));
						productsFruits.set(posProduct,prodFruits);
						SQLiteDatabase tmpFruits = helper.open();
						if (tmpFruits != null) {
							helper.updateProduct(nameLastFruits,prodFruits.getNombre(),prodFruits.getCantidad(),"updateList",idList);
							helper.close();
						}
						Intent intentFruits = new Intent(this,MostrarProductosLista.class);
						intentFruits.putExtra("idList",idList);
						startActivity(intentFruits);
						finish();
						break;
					case 3:
						ItemProducto prodBread = productsBread.get(posProduct);
						String nameLastBread = prodBread.getNombre();
						prodBread.setNombre(nameProductModified.getText().toString());
						prodBread.setCantidad(Integer.parseInt(cantProductModified.getText().toString()));
						productsBread.set(posProduct,prodBread);
						SQLiteDatabase tmpBread = helper.open();
						if (tmpBread != null) {
							helper.updateProduct(nameLastBread,prodBread.getNombre(),prodBread.getCantidad(),"updateList",idList);
							helper.close();
						}
						Intent intentBread = new Intent(this,MostrarProductosLista.class);
						intentBread.putExtra("idList",idList);
						startActivity(intentBread);
						finish();
						break;
					case 4:
						ItemProducto prodDrinks = productsDrinks.get(posProduct);
						String nameLastDrinks = prodDrinks.getNombre();
						prodDrinks.setNombre(nameProductModified.getText().toString());
						prodDrinks.setCantidad(Integer.parseInt(cantProductModified.getText().toString()));
						productsDrinks.set(posProduct,prodDrinks);
						SQLiteDatabase tmpDrinks = helper.open();
						if (tmpDrinks != null) {
							helper.updateProduct(nameLastDrinks,prodDrinks.getNombre(),prodDrinks.getCantidad(),"updateList",idList);
							helper.close();
						}
						Intent intentDrinks = new Intent(this,MostrarProductosLista.class);
						intentDrinks.putExtra("idList",idList);
						startActivity(intentDrinks);
						finish();
						break;
					case 5:
						ItemProducto prodMeat = productsMeat.get(posProduct);
						String nameLastMeat = prodMeat.getNombre();
						prodMeat.setNombre(nameProductModified.getText().toString());
						prodMeat.setCantidad(Integer.parseInt(cantProductModified.getText().toString()));
						productsMeat.set(posProduct,prodMeat);
						SQLiteDatabase tmpMeat = helper.open();
						if (tmpMeat != null) {
							helper.updateProduct(nameLastMeat,prodMeat.getNombre(),prodMeat.getCantidad(),"updateList",idList);
							helper.close();
						}
						Intent intentMeat = new Intent(this,MostrarProductosLista.class);
						intentMeat.putExtra("idList",idList);
						startActivity(intentMeat);
						finish();
						break;
					case 6:
						ItemProducto prodFish = productsFish.get(posProduct);
						String nameLastFish = prodFish.getNombre();
						prodFish.setNombre(nameProductModified.getText().toString());
						prodFish.setCantidad(Integer.parseInt(cantProductModified.getText().toString()));
						productsFish.set(posProduct,prodFish);
						SQLiteDatabase tmpFish = helper.open();
						if (tmpFish != null) {
							helper.updateProduct(nameLastFish,prodFish.getNombre(),prodFish.getCantidad(),"updateList",idList);
							helper.close();
						}
						Intent intentFish = new Intent(this,MostrarProductosLista.class);
						intentFish.putExtra("idList",idList);
						startActivity(intentFish);
						finish();
						break;
					case 7:
						ItemProducto prodCondiment = productsCondiment.get(posProduct);
						String nameLastCondiment = prodCondiment.getNombre();
						prodCondiment.setNombre(nameProductModified.getText().toString());
						prodCondiment.setCantidad(Integer.parseInt(cantProductModified.getText().toString()));
						productsCondiment.set(posProduct,prodCondiment);
						SQLiteDatabase tmpCondiment = helper.open();
						if (tmpCondiment != null) {
							helper.updateProduct(nameLastCondiment,prodCondiment.getNombre(),prodCondiment.getCantidad(),"updateList",idList);
							helper.close();
						}
						Intent intentCondiment = new Intent(this,MostrarProductosLista.class);
						intentCondiment.putExtra("idList",idList);
						startActivity(intentCondiment);
						finish();
						break;
					case 8:
						ItemProducto prodPasta = productsPasta.get(posProduct);
						String nameLastPasta = prodPasta.getNombre();
						prodPasta.setNombre(nameProductModified.getText().toString());
						prodPasta.setCantidad(Integer.parseInt(cantProductModified.getText().toString()));
						productsPasta.set(posProduct,prodPasta);
						SQLiteDatabase tmpPasta = helper.open();
						if (tmpPasta != null) {
							helper.updateProduct(nameLastPasta,prodPasta.getNombre(),prodPasta.getCantidad(),"updateList",idList);
							helper.close();
						}
						Intent intentPasta = new Intent(this,MostrarProductosLista.class);
						intentPasta.putExtra("idList",idList);
						startActivity(intentPasta);
						finish();
						break;
					case 9:
						ItemProducto prodFrozen = productsFrozen.get(posProduct);
						String nameLastFrozen = prodFrozen.getNombre();
						prodFrozen.setNombre(nameProductModified.getText().toString());
						prodFrozen.setCantidad(Integer.parseInt(cantProductModified.getText().toString()));
						productsFrozen.set(posProduct,prodFrozen);
						SQLiteDatabase tmpFrozen = helper.open();
						if (tmpFrozen != null) {
							helper.updateProduct(nameLastFrozen,prodFrozen.getNombre(),prodFrozen.getCantidad(),"updateList",idList);
							helper.close();
						}
						Intent intentFrozen = new Intent(this,MostrarProductosLista.class);
						intentFrozen.putExtra("idList",idList);
						startActivity(intentFrozen);
						finish();
						break;
					case 10:
						ItemProducto prodSauces = productsSauces.get(posProduct);
						String nameLastSauces = prodSauces.getNombre();
						prodSauces.setNombre(nameProductModified.getText().toString());
						prodSauces.setCantidad(Integer.parseInt(cantProductModified.getText().toString()));
						productsSauces.set(posProduct,prodSauces);
						SQLiteDatabase tmpSauces = helper.open();
						if (tmpSauces != null) {
							helper.updateProduct(nameLastSauces,prodSauces.getNombre(),prodSauces.getCantidad(),"updateList",idList);
							helper.close();
						}
						Intent intentSauces = new Intent(this,MostrarProductosLista.class);
						intentSauces.putExtra("idList",idList);
						startActivity(intentSauces);
						finish();
						break;
					case 11:
						ItemProducto prodStore = productsStore.get(posProduct);
						String nameLastStore = prodStore.getNombre();
						prodStore.setNombre(nameProductModified.getText().toString());
						prodStore.setCantidad(Integer.parseInt(cantProductModified.getText().toString()));
						productsStore.set(posProduct,prodStore);
						SQLiteDatabase tmpStore = helper.open();
						if (tmpStore != null) {
							helper.updateProduct(nameLastStore,prodStore.getNombre(),prodStore.getCantidad(),"updateList",idList);
							helper.close();
						}
						Intent intentStore = new Intent(this,MostrarProductosLista.class);
						intentStore.putExtra("idList",idList);
						startActivity(intentStore);
						finish();
						break;
					case 12:
						ItemProducto prodOthers = productsOthers.get(posProduct);
						String nameLastOthers = prodOthers.getNombre();
						prodOthers.setNombre(nameProductModified.getText().toString());
						prodOthers.setCantidad(Integer.parseInt(cantProductModified.getText().toString()));
						productsOthers.set(posProduct,prodOthers);
						SQLiteDatabase tmpOthers = helper.open();
						if (tmpOthers != null) {
							helper.updateProduct(nameLastOthers,prodOthers.getNombre(),prodOthers.getCantidad(),"updateList",idList);
							helper.close();
						}
						Intent intentOthers = new Intent(this,MostrarProductosLista.class);
						intentOthers.putExtra("idList",idList);
						startActivity(intentOthers);
						finish();
						break;
					default:
						break;
				}
				default:
					break;
		}
	}
	
	public void addManually(int tipoCat,String unit) {
		
		ItemProducto prodAdd = new ItemProducto(products.size(),nameProduct,cantProduct,tipoCat,unit,false);
		products.add(prodAdd);
		SQLiteDatabase tmp = helper.open();
		if (tmp != null) {
			helper.insertProducts(nameProduct, cantProduct, tipoCat, unit, barcodeProduct, "insertList", idList);
			helper.close();
		}
		switch (tipoCat) {
			case 1:
				productsDairy.add(prodAdd);
				break;
			case 2:
				productsFruits.add(prodAdd);
				break;
			case 3:
				productsBread.add(prodAdd);
				break;
			case 4:
				productsDrinks.add(prodAdd);
				break;
			case 5:
				productsMeat.add(prodAdd);
				break;
			case 6:
				productsFish.add(prodAdd);
				break;
			case 7:
				productsCondiment.add(prodAdd);
				break;
			case 8:
				productsPasta.add(prodAdd);
				break;
			case 9:
				productsFrozen.add(prodAdd);
				break;
			case 10:
				productsSauces.add(prodAdd);
				break;
			case 11:
				productsStore.add(prodAdd);
				break;
			case 12:
				productsOthers.add(prodAdd);
				break;
			default:
				break;
		}
		Intent i = new Intent(this,MostrarProductosLista.class);
		i.putExtra("idList", idList);
		startActivity(i);
		finish();
		
	}
	
	public void productExist() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		 
	    builder.setTitle("Informacion")
	            .setIcon(
	                    getResources().getDrawable(
	                            android.R.drawable.ic_dialog_info))
	            .setMessage("Producto ya existente en la lista")
	            .setNeutralButton(R.string.ok, new DialogInterface.OnClickListener() {
	 
	                @Override
	                public void onClick(DialogInterface arg0, int arg1) {
	                	arg0.cancel();
	                }
	            });
	 
	    builder.create();
	    builder.show();
	}
	
	public void errorAddProduct() {
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
	
	public void selectCategory() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		 
        // Get the layout inflater
        LayoutInflater inflater = this.getLayoutInflater();
 
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout

        View view = inflater.inflate(R.layout.activity_select_category, null);

    	category = (Spinner) view.findViewById(R.id.spinnerCategory);
    	ArrayAdapter<CharSequence> adapterCategory = ArrayAdapter.createFromResource(this, R.array.Categorias, android.R.layout.simple_spinner_item);
    	adapterCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    	category.setAdapter(adapterCategory);
    	category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
    		
    		public void onItemSelected (AdapterView<?> parent,
            android.view.View v, int position, long id) {
                cat = category.getItemAtPosition(position).toString();
    		}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
    	
    	unitsProduct = (Spinner) view.findViewById(R.id.spinnerUnitsList);
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
    	
        accept = (Button) view.findViewById(R.id.button_acceptList);
    	accept.setOnClickListener(this);
    	cancel = (Button) view.findViewById(R.id.buttonCancelList);
    	cancel.setOnClickListener(this);
    	
        builder.setView(view);
                
        
        builder.create();
        builder.show();
	}
	
	public boolean searchProduct() {
		EditText product = (EditText) findViewById(R.id.et1);
		String prod = product.getText().toString();
		if (prod.equals("")) {
			alertDialogReport("Es necesario escribir el nombre del producto con su cantidad, para poder añadir");
			return true;
		}
		else {
			String [] part = prod.split(" ");
			nameProduct = "";
			String oneM = "un";
			String oneF = "una";
			cantProduct = 0;
			for (int i = 0;i < part.length;i++) {
				if (oneM.equals(part[i]) || oneF.equals(part[i])) {
					cantProduct = 1;
				}
				else {
					try {
						cantProduct = Integer.parseInt(part[i]);
					}
					catch (NumberFormatException e) {
						nameProduct += part[i];
						nameProduct += " ";
					}
				}
			}
			if (cantProduct <= 0) {
				errorAddProduct();
				return true;
			}
			else {
				boolean find = false;
				int j = 0;
				while (j < products.size() && !find) {
					ItemProducto item = products.get(j);
					if (nameProduct.toLowerCase().equals(item.getNombre().toLowerCase())) {
						find = true;
					}
					j++;
				}
				if (find) {
					productExist();
					return true;
				}
				else {
					return false;
				}
			}
		}
	}
	
	public void showCheckBox() {
		ItemListAdapter adapter = new ItemListAdapter(this,listDataHeader,listDataChild,true,false);
		listProducts.setAdapter(adapter);
	}
	
	public void hideCheckBox() {
		if (pressedButtonUpdate) {
			ItemListAdapter adapter = new ItemListAdapter(this,listDataHeader,listDataChild,false,true);
			listProducts.setAdapter(adapter);
		}	
		else {
			ItemListAdapter adapter = new ItemListAdapter(this,listDataHeader,listDataChild,false,false);
			listProducts.setAdapter(adapter);
		}
	}
	
	public void deleteProducts() {
		for (int i=0;i<listDataHeader.size();i++) {
			List<ItemProducto> product = listDataChild.get(listDataHeader.get(i));
			for (int j=0;j<product.size();j++) {
				ItemProducto item = product.get(j);
				if (item.isSelected()) {
					product.remove(j);
					j--;
					if (product.size() == 0) {
						listDataChild.remove(listDataHeader.get(i));
						listDataHeader.remove(i);
						i--;
					}
					products.remove(item);
					SQLiteDatabase tmp = helper.open();
					if (tmp!=null) {
						helper.removeProduct(item.getNombre(),"deleteList",idList);
						helper.close();
					}
				}
			}	
		}
	
	}
	
	public void addProductVoice() {
		if(isConnected()){
	       	Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
	        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
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
		if (addVoice) {
			if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
	    	    
				match_text_dialog = new Dialog(MostrarProductosLista.this);
				match_text_dialog.setContentView(R.layout.dialog_matches);
				match_text_dialog.setTitle("Select Matching Text");
				textlist = (ListView)match_text_dialog.findViewById(R.id.listDialogVoice);
				matches_text = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
				ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, matches_text);
				textlist.setAdapter(adapter);
				textlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
	    	 
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
					addProductBarCode(scanningResult.getContents(),"barcode");
			else {
					Toast toast = Toast.makeText(getApplicationContext(), 
			   		        "No scan data received!", Toast.LENGTH_SHORT);
			   		    toast.show();
			}
		  }
     
	}
	
	public void addProduct(int position) {
		String[] prod = matches_text.get(position).split(" ");
	 	nameProduct = "";
	 	String oneCantM = "un";
	 	String oneCantF = "una";
	 	cantProduct = 0;
   	 	for (int i = 0;i < prod.length;i++) {
   	 			if ((prod[i].equals(oneCantM)) || (prod[i].equals(oneCantF))) {
						cantProduct = 1;
   	 			}
   	 			else {
   	 				try {
   	 					cantProduct = Integer.parseInt(prod[i]);
   	 				}
   	 				catch (NumberFormatException e) {
   	 					nameProduct += prod[i];
   	 					nameProduct += " ";
   	 				}
   	 			}
   	 	}
   	 	if (cantProduct < 0) {
   	 			errorAddProduct();
   	 	}
   	 	else {
   	 			initializeArrayList(idList);
   	 			boolean encontrado = false;
				int i = 0;
				while (i<products.size() && !encontrado){
					ItemProducto product = products.get(i);
					String nameP = product.getNombre().toLowerCase();
					if (nameP.equals(nameProduct.toLowerCase())) {
						encontrado = true;
					}
					i++;
				}
   	 				
   	 			if (!encontrado) {
   	 				selectCategory();
   	 			}
   	 			else {
   	 				productExist();
   	 					
   	 			}
   	 	
		
   	 		}
	}
	
	public void addBarCode() {
		IntentIntegrator scanIntegrator = new IntentIntegrator(this);
		scanIntegrator.initiateScan();
	}
	
	public void addProductBarCode(String barcode,String col) {
			if (!helper.exist(barcode,"productsList")){
   				if (!helper.exist(barcode,"productsTemporary")){
   					alertDialogReport("Producto no existente");
   				}
   				else{
   					
   					ArrayList<Object> tmp=helper.readProductsTemporary(barcode);
   					nameProduct = (String)tmp.get(0);
   					cantProduct = 1;
   					barcodeProduct = (String)tmp.get(1);
   					selectCategory();
   					
   				}
			}
   			else{
   				int idProduct = helper.getIDProduct(barcode,col);
   				if (!helper.existListHaveProducts(idProduct,idList)) {
   					ArrayList<Object> tmp=helper.readProductsTemporary(barcode);
   					nameProduct = (String)tmp.get(0);
   					cantProduct = 1;
   					barcodeProduct = (String)tmp.get(1);
   					selectCategory();
   				}
   				else {
   					Toast.makeText(this,"Producto ya existente",Toast.LENGTH_SHORT).show();
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
	                	arg0.cancel();
	                }
	            });
	 
	    builder.create();
	    builder.show();
	}
	
	public void arraySorted() {
		for (int i=0;i<listDataHeader.size();i++) {
			for (int j=0;j<listDataHeader.size()-1;j++) {
				int category = listDataHeader.get(j);
				int categorySig = listDataHeader.get(j+1);
				if (category > categorySig) {
					listDataHeader.set(j,categorySig);
					listDataHeader.set(j+1,category);
				}
			}
		}
	}
	
	public void updatePantry() {
		for (int i=0;i<listDataHeader.size();i++) {
			List<ItemProducto> product = listDataChild.get(listDataHeader.get(i));
			for (int j=0;j<product.size();j++) {
				ItemProducto item = product.get(j);
				if (item.isSelected()) {
					SQLiteDatabase tmp = helper.open();
					if (tmp != null) {
						if (helper.existProductAdded(item.getNombre())) {
							int cant = helper.getCant(item.getNombre());
							helper.updateProduct(item.getNombre(), item.getNombre(), item.getCantidad() + cant,"updatePantry",idList);
						}
						else {
							String barcode = helper.getBarcode(item.getNombre());
							helper.insertProducts(item.getNombre(), item.getCantidad(), item.getCategory(), item.getUnits(), barcode, "insertPantry", idList);
						}
						helper.close();
					}
				}
			}
		}
	}
	
	public void modificarProducto(View view,int positionGroup,int positionChild) {
		
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		 
        // Get the layout inflater
        LayoutInflater inflater = this.getLayoutInflater();
 
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout

        view = inflater.inflate(R.layout.activity_modificar_producto_despensa, null);
        
        nameProductModified = (EditText) view.findViewById(R.id.nameProductModify);
        cantProductModified = (EditText) view.findViewById(R.id.cantProduct);
     
        switch (listDataHeader.get(positionGroup)) {
        	case 1:
        		nameProductModified.setText(productsDairy.get(positionChild).getNombre());
                cantProductModified.setText(productsDairy.get(positionChild).getCantidad() + "");
                break;
        	case 2:
        		nameProductModified.setText(productsFruits.get(positionChild).getNombre());
                cantProductModified.setText(productsFruits.get(positionChild).getCantidad() + "");
                break;
        	case 3:
        		nameProductModified.setText(productsBread.get(positionChild).getNombre());
                cantProductModified.setText(productsBread.get(positionChild).getCantidad() + "");
                break;
        	case 4:
        		nameProductModified.setText(productsDrinks.get(positionChild).getNombre());
                cantProductModified.setText(productsDrinks.get(positionChild).getCantidad() + "");
                break;
        	case 5:
        		nameProductModified.setText(productsMeat.get(positionChild).getNombre());
                cantProductModified.setText(productsMeat.get(positionChild).getCantidad() + "");
                break;
        	case 6:
        		nameProductModified.setText(productsFish.get(positionChild).getNombre());
                cantProductModified.setText(productsFish.get(positionChild).getCantidad() + "");
                break;
        	case 7:
        		nameProductModified.setText(productsCondiment.get(positionChild).getNombre());
                cantProductModified.setText(productsCondiment.get(positionChild).getCantidad() + "");
                break;
        	case 8:
        		nameProductModified.setText(productsPasta.get(positionChild).getNombre());
                cantProductModified.setText(productsPasta.get(positionChild).getCantidad() + "");
                break;
        	case 9:
        		nameProductModified.setText(productsFrozen.get(positionChild).getNombre());
                cantProductModified.setText(productsFrozen.get(positionChild).getCantidad() + "");
                break;
        	case 10:
        		nameProductModified.setText(productsSauces.get(positionChild).getNombre());
                cantProductModified.setText(productsSauces.get(positionChild).getCantidad() + "");
                break;
        	case 11:
        		nameProductModified.setText(productsStore.get(positionChild).getNombre());
                cantProductModified.setText(productsStore.get(positionChild).getCantidad() + "");
                break;
        	case 12:
        		nameProductModified.setText(productsOthers.get(positionChild).getNombre());
                cantProductModified.setText(productsOthers.get(positionChild).getCantidad() + "");
                break;
            default:
            	break;
        }
    	save = (Button) view.findViewById(R.id.button_save);
    	save.setOnClickListener(this);
    	cancelModified = (Button) view.findViewById(R.id.button_cancel);
    	cancelModified.setOnClickListener(this);
    	decrement = (Button) view.findViewById(R.id.button_decrement);
    	decrement.setOnClickListener(this);
    	increment = (Button) view.findViewById(R.id.button_increment);
    	increment.setOnClickListener(this);
    	posProduct = positionChild;
    	catProduct = listDataHeader.get(positionGroup);
        builder.setView(view);
                
        
        builder.create();
        builder.show();
 
	}
	
	public void decrementCant(View view) {
		
		if (Integer.parseInt(cantProductModified.getText().toString()) > 0) {
	        cantProductModified.setText(Integer.parseInt(cantProductModified.getText().toString())-1 + "");

		}
		
	}
	
	public void incrementCant(View view) {
		
        cantProductModified.setText(Integer.parseInt(cantProductModified.getText().toString())+1 + "");

		
	}
	
	public void onBackPressed() {
		Intent j = new Intent(this, ShoppingLists.class);
		startActivity(j);
		super.finish();
	}
	
}
