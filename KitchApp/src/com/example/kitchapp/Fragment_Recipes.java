package com.example.kitchapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Fragment_Recipes extends Fragment {
<<<<<<< HEAD
	
	private ArrayList<ItemReceta> options=new ArrayList<ItemReceta>();
	private ListView list;
=======

>>>>>>> Rama-Vivi-Android
	View rootView;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
<<<<<<< HEAD
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
		options.add(new ItemReceta(5,"Modo de cocina (Rapidas)"));
		options.add(new ItemReceta(6,"Intolerancias"));
		options.add(new ItemReceta(7,"Tipo de plato"));
	}
	
=======
		rootView = inflater.inflate(R.layout.activity_fragment_recipes, container, false);
		
		TextView texto = (TextView) rootView.findViewById(R.id.texto_recipes);
		
		texto.setText("Tab seleccionada" + "\n\n" + "Recetas");;
		
		return rootView;
	}
>>>>>>> Rama-Vivi-Android
}
