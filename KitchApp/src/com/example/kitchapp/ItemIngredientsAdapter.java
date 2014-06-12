<<<<<<< HEAD
package com.example.kitchapp;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ItemIngredientsAdapter extends BaseAdapter{

		  protected Activity activity;
		  protected ArrayList<ItemReceta> itemsIngredients;
		  protected ArrayList<ItemReceta> itemsQuantity;
		  protected ArrayList<ItemReceta> itemsUnits;
		         
		  public ItemIngredientsAdapter(Activity activity,ArrayList<ItemReceta> ingredients, ArrayList<ItemReceta> quantity, ArrayList<ItemReceta> units) {
		    this.activity = activity;
		    this.itemsIngredients = ingredients;
		    this.itemsQuantity=quantity;
		    this.itemsUnits=units;
		  }
		  
		 
		  @Override
		  public int getCount() {
		    return itemsIngredients.size();
			  
		  }
		 
		  @Override
		  public Object getItem(int position) {
		    return itemsIngredients.get(position);
			  
		  }
		 
		  @Override
		  public long getItemId(int position) {
		    return itemsIngredients.get(position).getId();
		  }
		 
		  @Override
		  public View getView(int position, View contentView, ViewGroup parent) {
		    View vi=contentView;
		         
		    if(contentView == null) {
		      LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		      vi = inflater.inflate(R.layout.activity_item_ingredients_listviewrec, null);
		    }
		    
		             
		    ItemReceta ingredients = itemsIngredients.get(position);
		    ItemReceta quantity = itemsQuantity.get(position);
		    ItemReceta units=itemsUnits.get(position);     
		    /*ImageView image = (ImageView) vi.findViewById(R.id.imagen);
		    int imageResource = activity.getResources().getIdentifier(item.getRutaImagen(), null, activity.getPackageName());
		    image.setImageDrawable(activity.getResources().getDrawable(imageResource));*/
		         
		    TextView ingredient = (TextView) vi.findViewById(R.id.ingredients);
		    ingredient.setText(ingredients.getNombre());
		    TextView quant = (TextView) vi.findViewById(R.id.quantity);   
		    quant.setText(quantity.getNombre());
		    TextView unit = (TextView) vi.findViewById(R.id.units);  
		    unit.setText(units.getNombre());
		    
		    return vi;
		  }
	
}
=======
package com.example.kitchapp;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ItemIngredientsAdapter extends BaseAdapter{

		  protected Activity activity;
		  protected ArrayList<ItemReceta> itemsIngredients;
		  protected ArrayList<ItemReceta> itemsQuantity;
		  
		         
		  public ItemIngredientsAdapter(Activity activity,ArrayList<ItemReceta> ingredients, ArrayList<ItemReceta> quantity) {
		    this.activity = activity;
		    this.itemsIngredients = ingredients;
		    this.itemsQuantity=quantity;
		  }
		  
		 
		  @Override
		  public int getCount() {
		    return itemsIngredients.size();
			  
		  }
		 
		  @Override
		  public Object getItem(int position) {
		    return itemsIngredients.get(position);
			  
		  }
		 
		  @Override
		  public long getItemId(int position) {
		    return itemsIngredients.get(position).getId();
		  }
		 
		  @Override
		  public View getView(int position, View contentView, ViewGroup parent) {
		    View vi=contentView;
		         
		    if(contentView == null) {
		      LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		      vi = inflater.inflate(R.layout.activity_item_ingredients_listviewrec, null);
		    }
		    
		             
		    ItemReceta ingredients = itemsIngredients.get(position);
		    ItemReceta quantity = itemsQuantity.get(position);
		         
		    /*ImageView image = (ImageView) vi.findViewById(R.id.imagen);
		    int imageResource = activity.getResources().getIdentifier(item.getRutaImagen(), null, activity.getPackageName());
		    image.setImageDrawable(activity.getResources().getDrawable(imageResource));*/
		         
		    TextView ingredient = (TextView) vi.findViewById(R.id.ingredients);
		    ingredient.setText(ingredients.getNombre());
		    TextView quant = (TextView) vi.findViewById(R.id.quantity);   
		    quant.setText(quantity.getNombre());
		    
		   
		    return vi;
		  }
	
}
>>>>>>> Rama-Lorena-Android
