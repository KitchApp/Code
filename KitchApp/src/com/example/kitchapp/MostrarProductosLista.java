package com.example.kitchapp;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class MostrarProductosLista extends Activity implements OnClickListener {
	
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
	private ListView listDairy;
	private ListView listFruits;
	private ListView listBread;
	private ListView listDrinks;
	private ListView listMeat;
	private ListView listFish;
	private ListView listCondiment;
	private ListView listPasta;
	private ListView listFrozen;
	private ListView listSauces;
	private ListView listStore;
	private ListView listOthers;
	private Button accept;
	private Button cancel;
	private Spinner category;
	private String cat;
	private String nameProduct;
	private int cantProduct;
	private String barcodeProduct;
	private boolean pressedButtonDelete;
	private static final int REQUEST_CODE = 1234;
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
		
		listDairy = (ListView)findViewById(R.id.listViewDairy);
		ItemProductoAdapter adapterDairy;
		adapterDairy = new ItemProductoAdapter(this,productsDairy);
		listDairy.setAdapter(adapterDairy);
		
		listFruits = (ListView)findViewById(R.id.listViewFruits);
		ItemProductoAdapter adapterFruits;
		adapterFruits = new ItemProductoAdapter(this,productsFruits);
		listFruits.setAdapter(adapterFruits);
		
		listBread = (ListView)findViewById(R.id.listViewBread);
		ItemProductoAdapter adapterBread;
		adapterBread = new ItemProductoAdapter(this,productsBread);
		listBread.setAdapter(adapterBread);
		
		listDrinks = (ListView)findViewById(R.id.listViewDrinks);
		ItemProductoAdapter adapterDrinks;
		adapterDrinks = new ItemProductoAdapter(this,productsDrinks);
		listDrinks.setAdapter(adapterDrinks);
		
		listMeat = (ListView)findViewById(R.id.listViewMeat);
		ItemProductoAdapter adapterMeat;
		adapterMeat = new ItemProductoAdapter(this,productsMeat);
		listMeat.setAdapter(adapterMeat);
		
		listFish = (ListView)findViewById(R.id.listViewFish);
		ItemProductoAdapter adapterFish;
		adapterFish = new ItemProductoAdapter(this,productsFish);
		listFish.setAdapter(adapterFish);
		
		listCondiment = (ListView)findViewById(R.id.listViewCondiment);
		ItemProductoAdapter adapterCondiment;
		adapterCondiment = new ItemProductoAdapter(this,productsCondiment);
		listCondiment.setAdapter(adapterCondiment);
		
		listPasta = (ListView)findViewById(R.id.listViewPasta);
		ItemProductoAdapter adapterPasta;
		adapterPasta = new ItemProductoAdapter(this,productsPasta);
		listPasta.setAdapter(adapterPasta);
		
		listFrozen = (ListView)findViewById(R.id.listViewFrozen);
		ItemProductoAdapter adapterFrozen;
		adapterFrozen = new ItemProductoAdapter(this,productsFrozen);
		listFrozen.setAdapter(adapterFrozen);
		
		listSauces = (ListView)findViewById(R.id.listViewSauces);
		ItemProductoAdapter adapterSauces;
		adapterSauces = new ItemProductoAdapter(this,productsSauces);
		listSauces.setAdapter(adapterSauces);
		
		listStore = (ListView)findViewById(R.id.listViewStore);
		ItemProductoAdapter adapterStore;
		adapterStore = new ItemProductoAdapter(this,productsStore);
		listStore.setAdapter(adapterStore);
		
		listOthers = (ListView)findViewById(R.id.listViewOthers);
		ItemProductoAdapter adapterOthers;
		adapterOthers = new ItemProductoAdapter(this,productsOthers);
		listOthers.setAdapter(adapterOthers);
		
		Button button_addManually = (Button) findViewById(R.id.buttonAddManually);
		button_addManually.setOnClickListener(this);
		
		Button button_delete = (Button) findViewById(R.id.buttonDelete);
		button_delete.setOnClickListener(this);
		
		Button button_addVoice = (Button) findViewById(R.id.buttonAddVoice);
		button_addVoice.setOnClickListener(this);
		
		Button button_addCode = (Button) findViewById(R.id.buttonAddCode);
		button_addCode.setOnClickListener(this);
		
		
	}
	
	private void initializeArrayList(Integer idList) {
		
		products=helper.readProducts(idList,"readList");
		for (int i = 0; i < products.size(); i++) {
			ItemProducto item = products.get(i);
			switch (item.getCategory()) {
				case 1:
					productsDairy.add(item);
					break;
				case 2:
					productsFruits.add(item);
					break;
				case 3:
					productsBread.add(item);
					break;
				case 4:
					productsDrinks.add(item);
					break;
				case 5:
					productsMeat.add(item);
					break;
				case 6:
					productsFish.add(item);
					break;
				case 7:
					productsCondiment.add(item);
					break;
				case 8:
					productsPasta.add(item);
					break;
				case 9:
					productsFrozen.add(item);
					break;
				case 10:
					productsSauces.add(item);
					break;
				case 11:
					productsStore.add(item);
					break;
				case 12:
					productsOthers.add(item);
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
			case R.id.buttonCancel:
				Intent intent = new Intent(this,MostrarProductosLista.class);
				intent.putExtra("idList", idList);
				startActivity(intent);
				break;
			case R.id.button_accept:
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
				addManually(tipoCat);
				break;
			case R.id.buttonDelete:
				if (pressedButtonDelete) {
					//item_add.setEnabled(true);
					deleteProducts();
					hideCheckBox();
					pressedButtonDelete = false;
				}
				else {
					//item_add.setEnabled(false);
					showCheckBox();
					pressedButtonDelete = true;
				}
				break;
			case R.id.buttonAddVoice:
				addProductVoice();
				break;	
			case R.id.buttonAddCode:
				addBarCode();
				break;
			default:
				break;
		}
	}
	
	public void addManually(int tipoCat) {
		
		ItemProducto prodAdd = new ItemProducto(products.size(),nameProduct,cantProduct,tipoCat,false);
		products.add(prodAdd);
		SQLiteDatabase tmp = helper.open();
		if (tmp != null) {
			helper.insertProducts(nameProduct, cantProduct, tipoCat, barcodeProduct, "insertList", idList);
			helper.close();
		}
		switch (tipoCat) {
			case 1:
				productsDairy.add(prodAdd);
				listDairy = (ListView)findViewById(R.id.listViewDairy);
				ItemProductoAdapter adapterDairy;
				adapterDairy = new ItemProductoAdapter(this,productsDairy);
				listDairy.setAdapter(adapterDairy);
				break;
			case 2:
				productsFruits.add(prodAdd);
				listFruits = (ListView)findViewById(R.id.listViewFruits);
				ItemProductoAdapter adapterFruits;
				adapterFruits = new ItemProductoAdapter(this,productsFruits);
				listFruits.setAdapter(adapterFruits);
				break;
			case 3:
				productsBread.add(prodAdd);
				listBread = (ListView)findViewById(R.id.listViewBread);
				ItemProductoAdapter adapterBread;
				adapterBread = new ItemProductoAdapter(this,productsBread);
				listBread.setAdapter(adapterBread);
				break;
			case 4:
				productsDrinks.add(prodAdd);
				listDrinks = (ListView)findViewById(R.id.listViewDrinks);
				ItemProductoAdapter adapterDrinks;
				adapterDrinks = new ItemProductoAdapter(this,productsDrinks);
				listDrinks.setAdapter(adapterDrinks);
				break;
			case 5:
				productsMeat.add(prodAdd);
				listMeat = (ListView)findViewById(R.id.listViewMeat);
				ItemProductoAdapter adapterMeat;
				adapterMeat = new ItemProductoAdapter(this,productsMeat);
				listMeat.setAdapter(adapterMeat);
				break;
			case 6:
				productsFish.add(prodAdd);
				listFish = (ListView)findViewById(R.id.listViewFish);
				ItemProductoAdapter adapterFish;
				adapterFish = new ItemProductoAdapter(this,productsFish);
				listFish.setAdapter(adapterFish);
				break;
			case 7:
				productsCondiment.add(prodAdd);
				listCondiment = (ListView)findViewById(R.id.listViewCondiment);
				ItemProductoAdapter adapterCondiment;
				adapterCondiment = new ItemProductoAdapter(this,productsCondiment);
				listCondiment.setAdapter(adapterCondiment);
				break;
			case 8:
				productsPasta.add(prodAdd);
				listPasta = (ListView)findViewById(R.id.listViewPasta);
				ItemProductoAdapter adapterPasta;
				adapterPasta = new ItemProductoAdapter(this,productsPasta);
				listPasta.setAdapter(adapterPasta);
				break;
			case 9:
				productsFrozen.add(prodAdd);
				listFrozen = (ListView)findViewById(R.id.listViewFrozen);
				ItemProductoAdapter adapterFrozen;
				adapterFrozen = new ItemProductoAdapter(this,productsFrozen);
				listFrozen.setAdapter(adapterFrozen);
				break;
			case 10:
				productsSauces.add(prodAdd);
				listSauces = (ListView)findViewById(R.id.listViewSauces);
				ItemProductoAdapter adapterSauces;
				adapterSauces = new ItemProductoAdapter(this,productsSauces);
				listSauces.setAdapter(adapterSauces);
				break;
			case 11:
				productsStore.add(prodAdd);
				listStore = (ListView)findViewById(R.id.listViewStore);
				ItemProductoAdapter adapterStore;
				adapterStore = new ItemProductoAdapter(this,productsStore);
				listStore.setAdapter(adapterStore);
				break;
			case 12:
				productsOthers.add(prodAdd);
				listOthers = (ListView)findViewById(R.id.listViewOthers);
				ItemProductoAdapter adapterOthers;
				adapterOthers = new ItemProductoAdapter(this,productsOthers);
				listOthers.setAdapter(adapterOthers);
				break;
		}
		Intent i = new Intent(this,MostrarProductosLista.class);
		i.putExtra("idList", idList);
		startActivity(i);
		
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

        View view = inflater.inflate(R.layout.activity_seleccionar_categoria, null);

    	category = (Spinner) view.findViewById(R.id.spinnerCategory);
    	ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.Categorias, android.R.layout.simple_spinner_item);
    	adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    	category.setAdapter(adapter);
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
    	
        accept = (Button) view.findViewById(R.id.button_accept);
    	accept.setOnClickListener(this);
    	cancel = (Button) view.findViewById(R.id.buttonCancel);
    	cancel.setOnClickListener(this);
    	
        builder.setView(view);
                
        
        builder.create();
        builder.show();
	}
	
	public boolean searchProduct() {
		EditText product = (EditText) findViewById(R.id.et1);
		String prod = product.getText().toString();
		if (prod.equals("")) {
			alertDialogReport("Es necesario escribir el nombre del producto con su cantidad si se desea, para poder añadir");
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
			if (cantProduct < 0) {
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
		for (int i=0;i<listDairy.getChildCount();i++) {
			View v = listDairy.getChildAt(i);
			CheckBox check = (CheckBox) v.findViewById(R.id.checkBox_Product);
			check.setVisibility(View.VISIBLE);
		}
		
		for (int i=0;i<listFruits.getChildCount();i++) {
			View v = listFruits.getChildAt(i);
			CheckBox check = (CheckBox) v.findViewById(R.id.checkBox_Product);
			check.setVisibility(View.VISIBLE);
		}
		
		for (int i=0;i<listBread.getChildCount();i++) {
			View v = listBread.getChildAt(i);
			CheckBox check = (CheckBox) v.findViewById(R.id.checkBox_Product);
			check.setVisibility(View.VISIBLE);
		}
		
		for (int i=0;i<listDrinks.getChildCount();i++) {
			View v = listDrinks.getChildAt(i);
			CheckBox check = (CheckBox) v.findViewById(R.id.checkBox_Product);
			check.setVisibility(View.VISIBLE);
		}
		
		for (int i=0;i<listMeat.getChildCount();i++) {
			View v = listMeat.getChildAt(i);
			CheckBox check = (CheckBox) v.findViewById(R.id.checkBox_Product);
			check.setVisibility(View.VISIBLE);
		}
		
		for (int i=0;i<listFish.getChildCount();i++) {
			View v = listFish.getChildAt(i);
			CheckBox check = (CheckBox) v.findViewById(R.id.checkBox_Product);
			check.setVisibility(View.VISIBLE);
		}
		
		for (int i=0;i<listCondiment.getChildCount();i++) {
			View v = listCondiment.getChildAt(i);
			CheckBox check = (CheckBox) v.findViewById(R.id.checkBox_Product);
			check.setVisibility(View.VISIBLE);
		}
		
		for (int i=0;i<listPasta.getChildCount();i++) {
			View v = listPasta.getChildAt(i);
			CheckBox check = (CheckBox) v.findViewById(R.id.checkBox_Product);
			check.setVisibility(View.VISIBLE);
		}
		
		for (int i=0;i<listFrozen.getChildCount();i++) {
			View v = listFrozen.getChildAt(i);
			CheckBox check = (CheckBox) v.findViewById(R.id.checkBox_Product);
			check.setVisibility(View.VISIBLE);
		}
		
		for (int i=0;i<listSauces.getChildCount();i++) {
			View v = listSauces.getChildAt(i);
			CheckBox check = (CheckBox) v.findViewById(R.id.checkBox_Product);
			check.setVisibility(View.VISIBLE);
		}
		
		for (int i=0;i<listStore.getChildCount();i++) {
			View v = listStore.getChildAt(i);
			CheckBox check = (CheckBox) v.findViewById(R.id.checkBox_Product);
			check.setVisibility(View.VISIBLE);
		}
		
		for (int i=0;i<listOthers.getChildCount();i++) {
			View v = listOthers.getChildAt(i);
			CheckBox check = (CheckBox) v.findViewById(R.id.checkBox_Product);
			check.setVisibility(View.VISIBLE);
		}
	}
	
	public void hideCheckBox() {
		for (int i=0;i<listDairy.getChildCount();i++) {
			View v = listDairy.getChildAt(i);
			CheckBox check = (CheckBox) v.findViewById(R.id.checkBox_Product);
			check.setVisibility(View.INVISIBLE);
		}
		
		for (int i=0;i<listFruits.getChildCount();i++) {
			View v = listFruits.getChildAt(i);
			CheckBox check = (CheckBox) v.findViewById(R.id.checkBox_Product);
			check.setVisibility(View.INVISIBLE);
		}
		
		for (int i=0;i<listBread.getChildCount();i++) {
			View v = listBread.getChildAt(i);
			CheckBox check = (CheckBox) v.findViewById(R.id.checkBox_Product);
			check.setVisibility(View.INVISIBLE);
		}
		
		for (int i=0;i<listDrinks.getChildCount();i++) {
			View v = listDrinks.getChildAt(i);
			CheckBox check = (CheckBox) v.findViewById(R.id.checkBox_Product);
			check.setVisibility(View.INVISIBLE);
		}
		
		for (int i=0;i<listMeat.getChildCount();i++) {
			View v = listMeat.getChildAt(i);
			CheckBox check = (CheckBox) v.findViewById(R.id.checkBox_Product);
			check.setVisibility(View.INVISIBLE);
		}
		
		for (int i=0;i<listFish.getChildCount();i++) {
			View v = listFish.getChildAt(i);
			CheckBox check = (CheckBox) v.findViewById(R.id.checkBox_Product);
			check.setVisibility(View.INVISIBLE);
		}
		
		for (int i=0;i<listCondiment.getChildCount();i++) {
			View v = listCondiment.getChildAt(i);
			CheckBox check = (CheckBox) v.findViewById(R.id.checkBox_Product);
			check.setVisibility(View.INVISIBLE);
		}
		
		for (int i=0;i<listPasta.getChildCount();i++) {
			View v = listPasta.getChildAt(i);
			CheckBox check = (CheckBox) v.findViewById(R.id.checkBox_Product);
			check.setVisibility(View.INVISIBLE);
		}
		
		for (int i=0;i<listFrozen.getChildCount();i++) {
			View v = listFrozen.getChildAt(i);
			CheckBox check = (CheckBox) v.findViewById(R.id.checkBox_Product);
			check.setVisibility(View.INVISIBLE);
		}
		
		for (int i=0;i<listSauces.getChildCount();i++) {
			View v = listSauces.getChildAt(i);
			CheckBox check = (CheckBox) v.findViewById(R.id.checkBox_Product);
			check.setVisibility(View.INVISIBLE);
		}
		
		for (int i=0;i<listStore.getChildCount();i++) {
			View v = listStore.getChildAt(i);
			CheckBox check = (CheckBox) v.findViewById(R.id.checkBox_Product);
			check.setVisibility(View.INVISIBLE);
		}
		
		for (int i=0;i<listOthers.getChildCount();i++) {
			View v = listOthers.getChildAt(i);
			CheckBox check = (CheckBox) v.findViewById(R.id.checkBox_Product);
			check.setVisibility(View.INVISIBLE);
		}
	}
	
	public void deleteProducts() {
		for (int i=0;i<productsDairy.size();i++) {
			ItemProducto item = productsDairy.get(i);
			if (item.isSelected()) {
				productsDairy.remove(i);
				products.remove(item);
				i--;
				SQLiteDatabase tmp = helper.open();
				if (tmp!=null) {
					helper.removeProduct(item.getNombre(),"deleteList",idList);
					helper.close();
				}
			}
		}
		
		for (int i=0;i<productsFruits.size();i++) {
			ItemProducto item = productsFruits.get(i);
			if (item.isSelected()) {
				productsFruits.remove(i);
				products.remove(item);
				i--;
				SQLiteDatabase tmp = helper.open();
				if (tmp!=null) {
					helper.removeProduct(item.getNombre(),"deleteList",idList);
					helper.close();
				}
			}
		}
		
		for (int i=0;i<productsBread.size();i++) {
			ItemProducto item = productsBread.get(i);
			if (item.isSelected()) {
				productsBread.remove(i);
				products.remove(item);
				i--;
				SQLiteDatabase tmp = helper.open();
				if (tmp!=null) {
					helper.removeProduct(item.getNombre(),"deleteList",idList);
					helper.close();
				}
			}
		}
		
		for (int i=0;i<productsDrinks.size();i++) {
			ItemProducto item = productsDrinks.get(i);
			if (item.isSelected()) {
				productsDrinks.remove(i);
				products.remove(item);
				i--;
				SQLiteDatabase tmp = helper.open();
				if (tmp!=null) {
					helper.removeProduct(item.getNombre(),"deleteList",idList);
					helper.close();
				}
			}
		}
		
		for (int i=0;i<productsMeat.size();i++) {
			ItemProducto item = productsMeat.get(i);
			if (item.isSelected()) {
				productsMeat.remove(i);
				products.remove(item);
				i--;
				SQLiteDatabase tmp = helper.open();
				if (tmp!=null) {
					helper.removeProduct(item.getNombre(),"deleteList",idList);
					helper.close();
				}
			}
		}
		
		for (int i=0;i<productsFish.size();i++) {
			ItemProducto item = productsFish.get(i);
			if (item.isSelected()) {
				productsFish.remove(i);
				products.remove(item);
				i--;
				SQLiteDatabase tmp = helper.open();
				if (tmp!=null) {
					helper.removeProduct(item.getNombre(),"deleteList",idList);
					helper.close();
				}
			}
		}
		
		for (int i=0;i<productsCondiment.size();i++) {
			ItemProducto item = productsCondiment.get(i);
			if (item.isSelected()) {
				productsCondiment.remove(i);
				products.remove(item);
				i--;
				SQLiteDatabase tmp = helper.open();
				if (tmp!=null) {
					helper.removeProduct(item.getNombre(),"deleteList",idList);
					helper.close();
				}
			}
		}
		
		for (int i=0;i<productsPasta.size();i++) {
			ItemProducto item = productsPasta.get(i);
			if (item.isSelected()) {
				productsPasta.remove(i);
				products.remove(item);
				i--;
				SQLiteDatabase tmp = helper.open();
				if (tmp!=null) {
					helper.removeProduct(item.getNombre(),"deleteList",idList);
					helper.close();
				}
			}
		}
		
		for (int i=0;i<productsFrozen.size();i++) {
			ItemProducto item = productsFrozen.get(i);
			if (item.isSelected()) {
				productsFrozen.remove(i);
				products.remove(item);
				i--;
				SQLiteDatabase tmp = helper.open();
				if (tmp!=null) {
					helper.removeProduct(item.getNombre(),"deleteList",idList);
					helper.close();
				}
			}
		}
		
		for (int i=0;i<productsSauces.size();i++) {
			ItemProducto item = productsSauces.get(i);
			if (item.isSelected()) {
				productsSauces.remove(i);
				products.remove(item);
				i--;
				SQLiteDatabase tmp = helper.open();
				if (tmp!=null) {
					helper.removeProduct(item.getNombre(),"deleteList",idList);
					helper.close();
				}
			}
		}
		
		for (int i=0;i<productsStore.size();i++) {
			ItemProducto item = productsStore.get(i);
			if (item.isSelected()) {
				productsStore.remove(i);
				products.remove(item);
				i--;
				SQLiteDatabase tmp = helper.open();
				if (tmp!=null) {
					helper.removeProduct(item.getNombre(),"deleteList",idList);
					helper.close();
				}
			}
		}
		
		for (int i=0;i<productsOthers.size();i++) {
			ItemProducto item = productsOthers.get(i);
			if (item.isSelected()) {
				productsOthers.remove(i);
				products.remove(item);
				i--;
				SQLiteDatabase tmp = helper.open();
				if (tmp!=null) {
					helper.removeProduct(item.getNombre(),"deleteList",idList);
					helper.close();
				}
			}
		}
		listDairy = (ListView) findViewById(R.id.listViewDairy);
		ItemProductoAdapter adapterDairy = new ItemProductoAdapter(this,productsDairy);
		listDairy.setAdapter(adapterDairy);
		
		listFruits = (ListView) findViewById(R.id.listViewFruits);
		ItemProductoAdapter adapterFruits = new ItemProductoAdapter(this,productsFruits);
		listFruits.setAdapter(adapterFruits);
		
		listBread = (ListView) findViewById(R.id.listViewBread);
		ItemProductoAdapter adapterBread = new ItemProductoAdapter(this,productsBread);
		listBread.setAdapter(adapterBread);
		
		listDrinks = (ListView) findViewById(R.id.listViewDrinks);
		ItemProductoAdapter adapterDrinks = new ItemProductoAdapter(this,productsDrinks);
		listDrinks.setAdapter(adapterDrinks);
		
		listMeat = (ListView) findViewById(R.id.listViewMeat);
		ItemProductoAdapter adapterMeat = new ItemProductoAdapter(this,productsMeat);
		listMeat.setAdapter(adapterMeat);
		
		listFish = (ListView) findViewById(R.id.listViewFish);
		ItemProductoAdapter adapterFish = new ItemProductoAdapter(this,productsFish);
		listFish.setAdapter(adapterFish);
		
		listCondiment = (ListView) findViewById(R.id.listViewCondiment);
		ItemProductoAdapter adapterCondiment = new ItemProductoAdapter(this,productsCondiment);
		listCondiment.setAdapter(adapterCondiment);
		
		listPasta = (ListView) findViewById(R.id.listViewPasta);
		ItemProductoAdapter adapterPasta = new ItemProductoAdapter(this,productsPasta);
		listPasta.setAdapter(adapterPasta);
		
		listFrozen = (ListView) findViewById(R.id.listViewFrozen);
		ItemProductoAdapter adapterFrozen = new ItemProductoAdapter(this,productsFrozen);
		listFrozen.setAdapter(adapterFrozen);
		
		listSauces = (ListView) findViewById(R.id.listViewSauces);
		ItemProductoAdapter adapterSauces = new ItemProductoAdapter(this,productsSauces);
		listSauces.setAdapter(adapterSauces);
		
		listStore = (ListView) findViewById(R.id.listViewStore);
		ItemProductoAdapter adapterStore = new ItemProductoAdapter(this,productsStore);
		listStore.setAdapter(adapterStore);
		
		listOthers = (ListView) findViewById(R.id.listViewOthers);
		ItemProductoAdapter adapterOthers = new ItemProductoAdapter(this,productsOthers);
		listOthers.setAdapter(adapterOthers);
		
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
		BarcodeScanner b = new BarcodeScanner(this);
		addProductBarCode(b.getBarcode(), "barCode");
	}
	
	public void addProductBarCode(String barcode,String col) {
		if (barcode != "") {
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
   		else{
   		    Toast toast = Toast.makeText(getApplicationContext(), 
   		        "No scan data received!", Toast.LENGTH_SHORT);
   		    toast.show();
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
	
}
