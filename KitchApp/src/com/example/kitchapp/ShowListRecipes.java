<<<<<<< HEAD
package com.example.kitchapp;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.text.translate.UnicodeUnescaper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.squareup.picasso.Picasso;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

public class ShowListRecipes extends ActionBarActivity  implements OnClickListener {
	
	
	private ListView list;
	private ItemAdapter adapter;
	private ArrayList<String> infoBundle;
	private String recipeimages;
	
	private ArrayList<String> titulos;
	private ArrayList<String> imagenes;
	private ArrayList<Integer> idRecipes;
	private ArrayList<String> infoBundlePantry;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_recipes_listview);
				
		idRecipes=new ArrayList<Integer>();
		list = (ListView)findViewById(R.id.listViewRecipe);	
		Bundle extras1= this.getIntent().getExtras();
		if(extras1!=null){
			infoBundle=extras1.getStringArrayList("recipes");
			infoBundlePantry=extras1.getStringArrayList("pantry");
		}
		if(infoBundle!=null){
			titulos=initializeArrayTitleRecipes();
			imagenes=initializeArrayImagesRecipes();
		}
		if(infoBundlePantry!=null){
			titulos=initializeArrayTitleRecipesByPantry();
			imagenes=initializeArrayImagesRecipesByPantry();
			
		}
		
		List<ItemRecipeWithImage> items = new ArrayList<ItemRecipeWithImage>();
		for(int i=0;i<titulos.size();i++){
			items.add(new ItemRecipeWithImage(imagenes.get(i),titulos.get(i)));
			
		}
		
		// Sets the data behind this ListView
		this.list.setAdapter(new ItemAdapterRecipeWithImage(this, items));
		if(titulos.size()==0)
			Toast.makeText(getApplicationContext(), "No se encontraron resultados ", Toast.LENGTH_SHORT).show();
		list.setOnItemClickListener(new OnItemClickListener(){
			 
		    @Override
		    public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
		        // TODO Auto-generated method stub
		        //Toast.makeText(getApplicationContext(), "Ha pulsado el item " + position, Toast.LENGTH_SHORT).show();
		    	Intent intent = new Intent(getApplicationContext(),ShowRecipe.class);
		    	intent.putExtra("titulo",titulos.get(position));
		    	intent.putExtra("idRecipe", idRecipes.get(position));
		    	intent.putExtra("imagen",imagenes.get(position));
				startActivity(intent);
		    
		    }
		 
		}); 
	
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
	
	public ArrayList<String> initializeArrayTitleRecipes(){
		ArrayList<String>items = new ArrayList<String>();
		String[] titleTmp;
		String[] tmp=infoBundle.get(0).split("\\{"+"\""+"title"+"\""+":"+"\"");
		if(tmp.length>=2){
			if(!tmp[1].equals("")){
				for (int i = 1; i < tmp.length; i++) {
					titleTmp=tmp[i].split("\""+","+"\""+"nid"+"\""+":"+"\"");
					idRecipes.add(Integer.parseInt(titleTmp[1].split("\"")[0]));
					//titleTmp=tmp[i].split("\""+"\\}");
					UnicodeUnescaper unescaper = new UnicodeUnescaper();
					items.add(unescaper.translate(titleTmp[0]));
				}
			}
		}
		return items;
	}
	
	public ArrayList<String> initializeArrayTitleRecipesByPantry(){
		ArrayList<String>items = new ArrayList<String>();

		String[] idTmp=infoBundlePantry.get(1).split(",");
		if(!idTmp[0].equals("")){
			String[] tmp=infoBundlePantry.get(0).split(",");
			for (int i = 0; i < tmp.length; i++) {
				//titleTmp=tmp[i].split("\""+","+"\""+"nid"+"\""+":"+"\"");
				idRecipes.add(Integer.parseInt(idTmp[i]));
				//titleTmp=tmp[i].split("\""+"\\}");
				UnicodeUnescaper unescaper = new UnicodeUnescaper();
				items.add(unescaper.translate(tmp[i]));
			}
		}
		return items;
	}
	
	public ArrayList<String> initializeArrayImagesRecipes(){
		ArrayList<String>items = new ArrayList<String>();
		String[] titleTmp;
		String[] tmp=infoBundle.get(1).split("public://");
		if(tmp.length>=2){
			if(!tmp[1].equals("")){
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
			}
		}
		return items;
	}
	
	public ArrayList<String> initializeArrayImagesRecipesByPantry(){
		ArrayList<String>items = new ArrayList<String>();
		String[] titleTmp;
		String[] tmp=infoBundlePantry.get(2).split("public://");
		if(tmp.length>=2){
			if(!tmp[1].equals("")){
				for (int i = 1; i < tmp.length; i++) {
					titleTmp=tmp[i].split(",");
					titleTmp=titleTmp[0].split("Recetas"+"\\/");
					titleTmp=titleTmp[1].split(" ");
					String aux="";
					for (int j = 0; j < titleTmp.length-1; j++) {
						aux=aux+titleTmp[j]+"%20";
					}
					aux=aux+titleTmp[titleTmp.length-1];
					items.add("http://www.kitchapp.es/sites/default/files/Recetas/"+aux);
					/*titleTmp=tmp[i].split(",");
					items.add("http://www.kitchapp.es/sites/default/files/Recetas/"+titleTmp[0]);*/
				}
			}
		}
		return items;
	}
		
	

}
=======
package com.example.kitchapp;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.text.translate.UnicodeUnescaper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.squareup.picasso.Picasso;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

public class ShowListRecipes extends ActionBarActivity  implements OnClickListener {
	
	
	private ListView list;
	private ItemAdapter adapter;
	private ArrayList<String> infoBundle;
	private String recipeimages;
	
	private ArrayList<String> titulos;
	private ArrayList<String> imagenes;
	private ArrayList<Integer> idRecipes;
	private ArrayList<String> infoBundlePantry;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_recipes_listview);
				
		idRecipes=new ArrayList<Integer>();
		list = (ListView)findViewById(R.id.listViewRecipe);	
		Bundle extras1= this.getIntent().getExtras();
		if(extras1!=null){
			infoBundle=extras1.getStringArrayList("recipes");
			infoBundlePantry=extras1.getStringArrayList("pantry");
		}
		if(infoBundle!=null){
			titulos=initializeArrayTitleRecipes();
			imagenes=initializeArrayImagesRecipes();
		}
		if(infoBundlePantry!=null){
			titulos=initializeArrayTitleRecipesByPantry();
			imagenes=initializeArrayImagesRecipesByPantry();
			
		}
		
		List<ItemRecipeWithImage> items = new ArrayList<ItemRecipeWithImage>();
		for(int i=0;i<titulos.size();i++){
			items.add(new ItemRecipeWithImage(imagenes.get(i),titulos.get(i)));
			
		}
		
		// Sets the data behind this ListView
		this.list.setAdapter(new ItemAdapterRecipeWithImage(this, items));
		if(titulos.size()==0)
			Toast.makeText(getApplicationContext(), "No se encontraron resultados ", Toast.LENGTH_SHORT).show();
		list.setOnItemClickListener(new OnItemClickListener(){
			 
		    @Override
		    public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
		        // TODO Auto-generated method stub
		        //Toast.makeText(getApplicationContext(), "Ha pulsado el item " + position, Toast.LENGTH_SHORT).show();
		    	Intent intent = new Intent(getApplicationContext(),ShowRecipe.class);
		    	intent.putExtra("titulo",titulos.get(position));
		    	intent.putExtra("idRecipe", idRecipes.get(position));
		    	intent.putExtra("imagen",imagenes.get(position));
				startActivity(intent);
		    
		    }
		 
		}); 
	
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
	
	public ArrayList<String> initializeArrayTitleRecipes(){
		ArrayList<String>items = new ArrayList<String>();
		String[] titleTmp;
		String[] tmp=infoBundle.get(0).split("\\{"+"\""+"title"+"\""+":"+"\"");
		if(tmp.length>=2){
			if(!tmp[1].equals("")){
				for (int i = 1; i < tmp.length; i++) {
					titleTmp=tmp[i].split("\""+","+"\""+"nid"+"\""+":"+"\"");
					idRecipes.add(Integer.parseInt(titleTmp[1].split("\"")[0]));
					//titleTmp=tmp[i].split("\""+"\\}");
					UnicodeUnescaper unescaper = new UnicodeUnescaper();
					items.add(unescaper.translate(titleTmp[0]));
				}
			}
		}
		return items;
	}
	
	public ArrayList<String> initializeArrayTitleRecipesByPantry(){
		ArrayList<String>items = new ArrayList<String>();

		String[] idTmp=infoBundlePantry.get(1).split(",");
		if(!idTmp[0].equals("")){
			String[] tmp=infoBundlePantry.get(0).split(",");
			for (int i = 0; i < tmp.length; i++) {
				//titleTmp=tmp[i].split("\""+","+"\""+"nid"+"\""+":"+"\"");
				idRecipes.add(Integer.parseInt(idTmp[i]));
				//titleTmp=tmp[i].split("\""+"\\}");
				UnicodeUnescaper unescaper = new UnicodeUnescaper();
				items.add(unescaper.translate(tmp[i]));
			}
		}
		return items;
	}
	
	public ArrayList<String> initializeArrayImagesRecipes(){
		ArrayList<String>items = new ArrayList<String>();
		String[] titleTmp;
		String[] tmp=infoBundle.get(1).split("public://");
		if(tmp.length>=2){
			if(!tmp[1].equals("")){
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
			}
		}
		return items;
	}
	
	public ArrayList<String> initializeArrayImagesRecipesByPantry(){
		ArrayList<String>items = new ArrayList<String>();
		String[] titleTmp;
		String[] tmp=infoBundlePantry.get(2).split("public://");
		if(tmp.length>=2){
			if(!tmp[1].equals("")){
				for (int i = 1; i < tmp.length; i++) {
					titleTmp=tmp[i].split(",");
					titleTmp=titleTmp[0].split("Recetas"+"\\/");
					titleTmp=titleTmp[1].split(" ");
					String aux="";
					for (int j = 0; j < titleTmp.length-1; j++) {
						aux=aux+titleTmp[j]+"%20";
					}
					aux=aux+titleTmp[titleTmp.length-1];
					items.add("http://www.kitchapp.es/sites/default/files/Recetas/"+aux);
					/*titleTmp=tmp[i].split(",");
					items.add("http://www.kitchapp.es/sites/default/files/Recetas/"+titleTmp[0]);*/
				}
			}
		}
		return items;
	}
		
	

}
>>>>>>> Rama-Mayra_Android
