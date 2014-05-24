package com.example.kitchapp;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class Fragment_Recipes extends Fragment {
	private ArrayList<ItemReceta> options=new ArrayList<ItemReceta>();
	private ListView list;
	View rootView;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		rootView = inflater.inflate(R.layout.activity_mostrar_categoria_recetas, container, false);	
		initializeArrayListRecipes();
		list = (ListView)rootView.findViewById(R.id.listViewCatRec);
		ItemRecetaAdapter adapter;
		// Inicializamos el adapter.
		adapter = new ItemRecetaAdapter(getActivity(),options);
		// Asignamos el Adapter al ListView, en este punto hacemos que el
		// ListView muestre los datos que queremos.
		list.setAdapter(adapter);
		list.setOnItemClickListener(new OnItemClickListener(){
			 
		    @Override
		    public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
		        // TODO Auto-generated method stub
		        //Toast.makeText(getApplicationContext(), "Ha pulsado el item " + position, Toast.LENGTH_SHORT).show();
		        
		        	//cantFinal = products.get(position).getCantidad();
		        	//modificarProducto(arg1,position);
		 
		    }
		 
		}); 
	
		return rootView;
	}	
	
	private void initializeArrayListRecipes() {
		options.add(new ItemReceta(1,"Por ingrediente"));
		options.add(new ItemReceta(2,"Nacionalidad"));
		options.add(new ItemReceta(3,"Dieta"));
		options.add(new ItemReceta(4,"Con mi despensa"));
		options.add(new ItemReceta(5,"Rápidas"));
		options.add(new ItemReceta(6,"Especiales"));
		options.add(new ItemReceta(7,"Tipo de plato"));
		
	
	}


}
