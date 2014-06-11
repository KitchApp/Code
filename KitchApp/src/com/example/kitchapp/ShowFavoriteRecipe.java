<<<<<<< HEAD
package com.example.kitchapp;

import java.util.ArrayList;

import com.squareup.picasso.Picasso;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class ShowFavoriteRecipe extends ActionBarActivity  implements OnClickListener {

	private String titleBundle;
	private String imageBundle;
	//private Integer idRecipeBundle;
	private TextView titleRef;
	private ImageView imageRef;
	private TextView numPersRef;
	private ListView ingredientsRef;
	private TextView preparationRef;
	private TextView nationalityRef;
	private TextView headerIngredients;
	private TextView headerPreparation;
	private Handler_Sqlite helper;
	private ArrayList<ItemReceta> ingredients;
	private ArrayList<ItemReceta> quantities;
	private ArrayList<ItemReceta> units;
	private ItemIngredientsAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_recipe);
		
		helper=new Handler_Sqlite(this);
		Bundle extras1= this.getIntent().getExtras();
		if(extras1!=null){
			titleBundle=extras1.getString("titulo");
			//idRecipeBundle=extras1.getInt("idRecipe");
			imageBundle=extras1.getString("imagen");
			
		}
		ingredients=new ArrayList<ItemReceta>();
		quantities=new ArrayList<ItemReceta>();
		units=new ArrayList<ItemReceta>();
		titleRef=(TextView)findViewById(R.id.title);
		imageRef=(ImageView)findViewById(R.id.image);
		numPersRef=(TextView)findViewById(R.id.numPersons);
		headerIngredients=(TextView)findViewById(R.id.headerIngredients);
		nationalityRef=(TextView)findViewById(R.id.nationality);
		headerPreparation=(TextView)findViewById(R.id.headerPreparation);
		preparationRef=(TextView)findViewById(R.id.preparation);
		ingredientsRef=(ListView)findViewById(R.id.ingredients);
		
		//mostrar el titulo
		titleRef.setText(titleBundle);	
		//mostrar la imagen
		Picasso.with(getApplicationContext()).load(imageBundle).into(imageRef);
		//Picasso.with(getApplicationContext().)
		//mostrar el número de personas
		helper.open();
		numPersRef.setText(helper.getNumPersRecipe(titleBundle)+" Personas");
		headerIngredients.setText("Ingredientes");
		//mostrar la nacionalidad
		nationalityRef.setText(helper.getNationalityRecipe(titleBundle));
		//result[2] contiene los ingredientes
		initializeArrayIngredients();
		initializeArrayQuantities();
		initializeArrayUnits();
		// Inicializamos el adapter.
		int tam;
		if(ingredients.size()<quantities.size()){
			tam=ingredients.size();
			for (int i = 0; i < quantities.size()-tam; i++) {
				ItemReceta item=new ItemReceta(tam+i,"");
				ingredients.add(item);
			}
		}
		
		if(quantities.size()<ingredients.size()){
			tam=quantities.size();
			for (int i = 0; i < ingredients.size()-tam; i++) {
				ItemReceta item=new ItemReceta(tam+i,"");
				quantities.add(item);
			}
		}
		
		if(units.size()<ingredients.size()){
			tam=units.size();
			for (int i = 0; i < ingredients.size()-tam; i++) {
				ItemReceta item=new ItemReceta(tam+i,"");
				units.add(item);
			}
		}
		
		adapter = new ItemIngredientsAdapter(this,ingredients,quantities,units);
		// Asignamos el Adapter al ListView, en este punto hacemos que el
     	// ListView muestre los datos que queremos.
		ingredientsRef.setAdapter(adapter);
		//mostrar la preparación
		headerPreparation.setText("Preparación");
		preparationRef.setText(helper.getPreparationRecipe(titleBundle));
		helper.close();
	}
    
	public void initializeArrayIngredients(){
		String tmp1=helper.getIngredientsRecipe(titleBundle);
		String [] tmp2=tmp1.split(",");
		
		for (int i = 0; i < tmp2.length; i++) {	
			String aux2=tmp2[i];
			ItemReceta aux=new ItemReceta(i,aux2);
			ingredients.add(aux);
		}
		
	}
	
	public void initializeArrayQuantities(){
		String tmp1=helper.getQuantitiesRecipe(titleBundle);
		String [] tmp2=tmp1.split(",");
				
		for (int i = 0; i < tmp2.length; i++) {	
			ItemReceta aux=new ItemReceta(i,tmp2[i]);
			quantities.add(aux);
		}
		
	}
	
	public void initializeArrayUnits(){
		String tmp1=helper.getUnitsRecipe(titleBundle);
		String [] tmp2=tmp1.split(",");
				
		for (int i = 0; i < tmp2.length; i++) {	
			ItemReceta aux=new ItemReceta(i,tmp2[i]);
			units.add(aux);
		}	
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
	
	

}
=======
package com.example.kitchapp;

import java.util.ArrayList;

import com.squareup.picasso.Picasso;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class ShowFavoriteRecipe extends ActionBarActivity  implements OnClickListener {

	private String titleBundle;
	private String imageBundle;
	//private Integer idRecipeBundle;
	private TextView titleRef;
	private ImageView imageRef;
	private TextView numPersRef;
	private ListView ingredientsRef;
	private TextView preparationRef;
	private TextView nationalityRef;
	private TextView headerIngredients;
	private TextView headerPreparation;
	private Handler_Sqlite helper;
	private ArrayList<ItemReceta> ingredients;
	private ArrayList<ItemReceta> quantities;
	private ArrayList<ItemReceta> units;
	private ItemIngredientsAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_recipe);
		
		helper=new Handler_Sqlite(this);
		Bundle extras1= this.getIntent().getExtras();
		if(extras1!=null){
			titleBundle=extras1.getString("titulo");
			//idRecipeBundle=extras1.getInt("idRecipe");
			imageBundle=extras1.getString("imagen");
			
		}
		ingredients=new ArrayList<ItemReceta>();
		quantities=new ArrayList<ItemReceta>();
		units=new ArrayList<ItemReceta>();
		titleRef=(TextView)findViewById(R.id.title);
		imageRef=(ImageView)findViewById(R.id.image);
		numPersRef=(TextView)findViewById(R.id.numPersons);
		headerIngredients=(TextView)findViewById(R.id.headerIngredients);
		nationalityRef=(TextView)findViewById(R.id.nationality);
		headerPreparation=(TextView)findViewById(R.id.headerPreparation);
		preparationRef=(TextView)findViewById(R.id.preparation);
		ingredientsRef=(ListView)findViewById(R.id.ingredients);
		
		//mostrar el titulo
		titleRef.setText(titleBundle);	
		//mostrar la imagen
		Picasso.with(getApplicationContext()).load(imageBundle).into(imageRef);
		//Picasso.with(getApplicationContext().)
		//mostrar el número de personas
		helper.open();
		numPersRef.setText(helper.getNumPersRecipe(titleBundle)+" Personas");
		headerIngredients.setText("Ingredientes");
		//mostrar la nacionalidad
		nationalityRef.setText(helper.getNationalityRecipe(titleBundle));
		//result[2] contiene los ingredientes
		initializeArrayIngredients();
		initializeArrayQuantities();
		initializeArrayUnits();
		// Inicializamos el adapter.
		int tam;
		if(ingredients.size()<quantities.size()){
			tam=ingredients.size();
			for (int i = 0; i < quantities.size()-tam; i++) {
				ItemReceta item=new ItemReceta(tam+i,"");
				ingredients.add(item);
			}
		}
		
		if(quantities.size()<ingredients.size()){
			tam=quantities.size();
			for (int i = 0; i < ingredients.size()-tam; i++) {
				ItemReceta item=new ItemReceta(tam+i,"");
				quantities.add(item);
			}
		}
		
		if(units.size()<ingredients.size()){
			tam=units.size();
			for (int i = 0; i < ingredients.size()-tam; i++) {
				ItemReceta item=new ItemReceta(tam+i,"");
				units.add(item);
			}
		}
		
		adapter = new ItemIngredientsAdapter(this,ingredients,quantities,units);
		// Asignamos el Adapter al ListView, en este punto hacemos que el
     	// ListView muestre los datos que queremos.
		ingredientsRef.setAdapter(adapter);
		//mostrar la preparación
		headerPreparation.setText("Preparación");
		preparationRef.setText(helper.getPreparationRecipe(titleBundle));
		helper.close();
	}
    
	public void initializeArrayIngredients(){
		String tmp1=helper.getIngredientsRecipe(titleBundle);
		String [] tmp2=tmp1.split(",");
		
		for (int i = 0; i < tmp2.length; i++) {	
			String aux2=tmp2[i];
			ItemReceta aux=new ItemReceta(i,aux2);
			ingredients.add(aux);
		}
		
	}
	
	public void initializeArrayQuantities(){
		String tmp1=helper.getQuantitiesRecipe(titleBundle);
		String [] tmp2=tmp1.split(",");
				
		for (int i = 0; i < tmp2.length; i++) {	
			ItemReceta aux=new ItemReceta(i,tmp2[i]);
			quantities.add(aux);
		}
		
	}
	
	public void initializeArrayUnits(){
		String tmp1=helper.getUnitsRecipe(titleBundle);
		String [] tmp2=tmp1.split(",");
				
		for (int i = 0; i < tmp2.length; i++) {	
			ItemReceta aux=new ItemReceta(i,tmp2[i]);
			units.add(aux);
		}	
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
	
	

}
>>>>>>> Rama-Mayra_Android
