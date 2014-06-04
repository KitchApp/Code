package com.example.kitchapp;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.text.translate.UnicodeEscaper;
import org.apache.commons.lang3.text.translate.UnicodeUnescaper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Fragment_TopTen extends Fragment implements OnClickListener{
	View rootView;
	private ListView list;
	private ItemAdapterRecipeWithImage adapter;
	private static ArrayList<String> infoBundle;
	private ArrayList<String> titulos;
	private ArrayList<String> imagenes;
	private ArrayList<Integer> idRecipes;
	
	public static Fragment_TopTen newInstance(ArrayList<String> info) {
		Fragment_TopTen frag = new Fragment_TopTen();
		Bundle args = new Bundle();
         
        args.putStringArrayList("infoTopTen",info);
         
        frag.setArguments(args);
         
        return frag;
    }
	
	@Override  
    public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
    }  
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		rootView = inflater.inflate(R.layout.activity_show_recipes_listview,container, false);
		idRecipes=new ArrayList<Integer>();
		list = (ListView)rootView.findViewById(R.id.listViewRecipe);
		
		Bundle extras1= getArguments();
		if(extras1!=null){
			infoBundle=extras1.getStringArrayList("infoTopTen");
			
		}
		titulos=initializeArrayTitleRecipes();
		imagenes=initializeArrayImagesRecipes();
		List<ItemRecipeWithImage> items = new ArrayList<ItemRecipeWithImage>();
		for(int i=0;i<titulos.size();i++){
			//items.add(new ItemRecipeWithImage(imagenes.get(i),titulos.get(i)));
			items.add(new ItemRecipeWithImage((String)imagenes.get(i),(String)titulos.get(i)));
			
		}		
		// Inicializamos el adapter.
		adapter = new ItemAdapterRecipeWithImage(getActivity(), items);
		// Asignamos el Adapter al ListView, en este punto hacemos que el
		// ListView muestre los datos que queremos.
		list.setAdapter(adapter);
		if(titulos.size()==0)
			Toast.makeText(getActivity(), "No se encontraron resultados ", Toast.LENGTH_SHORT).show();
		list.setOnItemClickListener(new OnItemClickListener(){
			 
		    @Override
		    public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
		        // TODO Auto-generated method stub
		        //Toast.makeText(getApplicationContext(), "Ha pulsado el item " + position, Toast.LENGTH_SHORT).show();
		    	Intent intent = new Intent(getActivity(),ShowRecipe.class);
		    	intent.putExtra("titulo",titulos.get(position));
		    	intent.putExtra("idRecipe", idRecipes.get(position));
		    	intent.putExtra("imagen",imagenes.get(position));
				startActivity(intent);
		    
		    }
		 
		}); 

		
		return rootView;
	}
	

	public ArrayList<String> initializeArrayTitleRecipes(){
		ArrayList<String>items = new ArrayList<String>();
		String[] titleTmp;
		String[] tmp=infoBundle.get(0).split("\\{"+"\""+"title"+"\""+":"+"\"");
		for (int i = 1; i < tmp.length; i++) {
			titleTmp=tmp[i].split("\""+","+"\""+"nid"+"\""+":"+"\"");
			idRecipes.add(Integer.parseInt(titleTmp[1].split("\"")[0]));
			 UnicodeEscaper escaper = UnicodeEscaper.above(127);
    	     UnicodeUnescaper unescaper     = new UnicodeUnescaper();
    	  
    	     //String textoProblematico = "Música";
    	  
    	     //String textoEscapado = escaper.translate(textoProblematico);
    	     // textoEscapado == "M\\u00FAsica", que se imprime como "M\u00FAsica"
    	  
    	     unescaper.translate(titleTmp[0]);
    	  
    	     items.add(unescaper.translate(titleTmp[0]));
			//items.add(titleTmp[0]);
		}
		return items;
	}
	
		
	public ArrayList<String> initializeArrayImagesRecipes(){
		ArrayList<String>items = new ArrayList<String>();
		String[] titleTmp;
		String[] tmp=infoBundle.get(1).split("public://");
		for (int i = 1; i < tmp.length; i++) {
			titleTmp=tmp[i].split("\""+"\\}");
			titleTmp=titleTmp[0].split("Recetas"+"\\/");
			titleTmp=titleTmp[1].split(" ");
			String aux="";
			for (int j = 0; j < titleTmp.length-1; j++) {
				aux=aux+titleTmp[j]+"%20";
			}
			aux=aux+titleTmp[titleTmp.length-1];
			items.add("http://www.kitchapp.es/sites/default/files/Recetas/"+aux);
			
		}
		return items;
	}




	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
}