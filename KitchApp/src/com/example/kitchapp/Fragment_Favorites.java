package com.example.kitchapp;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Fragment_Favorites extends Fragment{
	View rootView;
	private static ArrayList<String> infoBundle1;
	private static ArrayList<String> infoBundle2;
	private ArrayList<String> titulos;
	private ArrayList<String> imagenes;
	//private ArrayList<Integer> idRecipes;
	private ListView list;
	private ItemAdapterRecipeWithImage adapter;
	private Handler_Sqlite helper;
	
	public static Fragment_Favorites newInstance(ArrayList<String> titles,ArrayList<String> images) {
		Fragment_Favorites frag = new Fragment_Favorites();
		Bundle args = new Bundle();
         
        args.putStringArrayList("titleFavorite",titles);
        args.putStringArrayList("imagesFavorite",images);
         
        frag.setArguments(args);
         
        return frag;
    }
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		//rootView = inflater.inflate(R.layout.activity_fragment_favorites, container, false);
		rootView = inflater.inflate(R.layout.activity_show_recipes_listview, container, false);
		titulos=new ArrayList<String>();
		imagenes=new ArrayList<String>();
		helper=new Handler_Sqlite(getActivity());
		//TextView texto = (TextView) rootView.findViewById(R.id.texto_favorites);
		
		//texto.setText("Tab seleccionada" + "\n\n" + "Favoritos");;
		
		//idRecipes=new ArrayList<Integer>();
		list = (ListView)rootView.findViewById(R.id.listViewRecipe);
		//((BaseAdapter) list.getAdapter()).notifyDataSetChanged();
		/*Bundle extras1= getArguments();
		if(extras1!=null){
			titulos=extras1.getStringArrayList("titleFavorite");
			imagenes=extras1.getStringArrayList("imagesFavorite");
		}*/
		helper=new Handler_Sqlite(getActivity());
		helper.open();
		titulos=initializeArrayTitleRecipesFromLocalBBDD();
		imagenes=initializeArrayImagesRecipesFromLocalBBDD();
		
		List<ItemRecipeWithImage> items = new ArrayList<ItemRecipeWithImage>();
		
			for(int i=0;i<titulos.size();i++){
				items.add(new ItemRecipeWithImage((String)imagenes.get(i),(String)titulos.get(i)));
				
			}		
		
		// Inicializamos el adapter.
		adapter = new ItemAdapterRecipeWithImage(getActivity(), items);
		// Asignamos el Adapter al ListView, en este punto hacemos que el
		// ListView muestre los datos que queremos.
		
		list.setAdapter(adapter);
		adapter.notifyDataSetChanged(); 
		if(titulos.size()==0)
			Toast.makeText(getActivity(), "No se encontraron resultados ", Toast.LENGTH_SHORT).show();
		list.setOnItemClickListener(new OnItemClickListener(){
			 
		    @Override
		    public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
		        // TODO Auto-generated method stub
		        //Toast.makeText(getApplicationContext(), "Ha pulsado el item " + position, Toast.LENGTH_SHORT).show();
		    	Intent intent = new Intent(getActivity(),ShowFavoriteRecipe.class);
		    	intent.putExtra("titulo",titulos.get(position));
		    	//intent.putExtra("idRecipe", idRecipes.get(position));
		    	intent.putExtra("imagen",imagenes.get(position));
				startActivity(intent);
		    
		    }
		 
		}); 

		
		return rootView;
	}
	
	
	public ArrayList<String> initializeArrayTitleRecipesFromLocalBBDD(){
		ArrayList<String>items = new ArrayList<String>();
	
		//Leer de la base de datos local los titulos de las recetas favoritas
		items=helper.readInfoFavoriteRecipes("title");
		return items;
	}
	
		
	public ArrayList<String> initializeArrayImagesRecipesFromLocalBBDD(){
		ArrayList<String>items = new ArrayList<String>();
		
		//Leer de la base de datos local los titulos de las recetas favoritas
		items=helper.readInfoFavoriteRecipes("image");
		return items;
	
	}

}
