package com.example.kitchapp;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringEscapeUtils;
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

import com.squareup.picasso.Picasso;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class ShowRecipe extends ActionBarActivity  implements OnClickListener {

	
	private String titleBundle;
	private String imageBundle;
	private Integer idRecipeBundle;
	private TextView titleRef;
	private ImageView imageRef;
	private TextView numPersRef;
	private ListView ingredientsRef;
	private TextView preparationRef;
	private TextView nationalityRef;
	private TextView headerIngredients;
	private TextView headerPreparation;
	private Handler_Sqlite helper;
	private String ingred="";
	private String quant="";
	private String unit="";
	private ArrayList<ItemReceta> quantities=new ArrayList<ItemReceta>();
	private ArrayList<ItemReceta> ingredients=new ArrayList<ItemReceta>();
	private ArrayList<ItemReceta> units=new ArrayList<ItemReceta>();
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_recipes_favourites, menu);
        return true;
    }
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_recipe);
	    helper=new Handler_Sqlite(this);
		Bundle extras1= this.getIntent().getExtras();
		if(extras1!=null){
			titleBundle=extras1.getString("titulo");
			idRecipeBundle=extras1.getInt("idRecipe");
			imageBundle=extras1.getString("imagen");
			
		}
		new GetInfoRecipeById(this).execute();
		titleRef=(TextView)findViewById(R.id.title);
		imageRef=(ImageView)findViewById(R.id.image);
		numPersRef=(TextView)findViewById(R.id.numPersons);
		headerIngredients=(TextView)findViewById(R.id.headerIngredients);
		nationalityRef=(TextView)findViewById(R.id.nationality);
		headerPreparation=(TextView)findViewById(R.id.headerPreparation);
		preparationRef=(TextView)findViewById(R.id.preparation);
		
		
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	        case R.id.favourite:
	        	
	        	SQLiteDatabase tmp = helper.open();
	        	if(tmp!=null){
	        		initializeStringIngredients();
	        		initializeStringQuantities();
	        		initializeArrayUnits();
	        	    String[] num=numPersRef.getText().toString().split(" Personas");
	        	    helper.addFavoriteRecipe(idRecipeBundle,titleBundle,imageBundle,Integer.parseInt(num[0]),nationalityRef.getText().toString(),ingred,quant,unit, preparationRef.getText().toString());
	        	    Toast.makeText(getApplicationContext(), "Receta añadida a favoritos", Toast.LENGTH_LONG).show();
	        	    //Log.i("ActionBar", "Nuevo!");
	        	}
	        	    return true;
	        default:
	            return super.onOptionsItemSelected(item);
	   }
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
	
	public void initializeStringIngredients(){
		for(int i=0;i<ingredients.size();i++){
			
			ingred+=ingredients.get(i).getNombre()+",";
		}
	}
	
	public void initializeStringQuantities(){
		for(int i=0;i<quantities.size();i++){
			quant+=quantities.get(i).getNombre()+",";
		}
		
		
	}
	
	public void initializeArrayUnits(){
		for(int i=0;i<units.size();i++){
			unit+=units.get(i).getNombre()+",";
		}	
	}
	

	
	private class GetInfoRecipeById extends AsyncTask<String, Integer, ArrayList<String>>{
		Activity activity;
		ArrayList<String> resp=new ArrayList<String>();
		//ArrayList<ItemReceta> ingredients=new ArrayList<ItemReceta>();
		//ArrayList<ItemReceta> quantities=new ArrayList<ItemReceta>();
		ArrayList<ItemReceta> units=new ArrayList<ItemReceta>();
		ListView list;
		ItemIngredientsAdapter adapter;
		public GetInfoRecipeById(Activity activity){
	        this.activity = activity;
	    }
		
		@Override
	    protected ArrayList<String> doInBackground(String... urls) {
	    	
			HttpClient httpclient = new DefaultHttpClient();
		    HttpPost httppost1 = new HttpPost("http://www.kitchapp.es/getNumPersByIdRecipe.php?entity_id="+idRecipeBundle);
		    HttpPost httppost2 = new HttpPost("http://www.kitchapp.es/getNationalityByIdRecipe.php?entity_id="+idRecipeBundle);
		    HttpPost httppost3 = new HttpPost("http://www.kitchapp.es/getIngredientsbyIdRecipe.php?entity_id="+idRecipeBundle);
		    HttpPost httppost4 = new HttpPost("http://www.kitchapp.es/getPreparationByIdRecipe.php?entity_id="+idRecipeBundle);
		    HttpPost httppost5 = new HttpPost("http://www.kitchapp.es/getUnitsAndQuantityIngredientsByIdRecipe.php?entity_id="+idRecipeBundle);
		    
		    try {
		
		        JSONObject json1 = new JSONObject();
		        JSONObject json2 = new JSONObject();
		        JSONObject json3 = new JSONObject();
		        JSONObject json4 = new JSONObject();
		        JSONObject json5 = new JSONObject();
		        //add serialised JSON object into POST request
		        StringEntity se1 = new StringEntity(json1.toString());
		        StringEntity se2 = new StringEntity(json2.toString());
		        StringEntity se3 = new StringEntity(json3.toString());
		        StringEntity se4 = new StringEntity(json4.toString());
		        StringEntity se5 = new StringEntity(json5.toString());
		        //set request content type
		        se1.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
		        httppost1.setEntity(se1);
		        se2.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
		        httppost2.setEntity(se2);
		        se3.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
		        httppost3.setEntity(se3);
		        se4.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
		        httppost4.setEntity(se4);
		        se5.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
		        httppost5.setEntity(se5);
		
		
		        //send the POST request
		        HttpResponse response1 = httpclient.execute(httppost1);
		        HttpResponse response2 = httpclient.execute(httppost2);
		        HttpResponse response3 = httpclient.execute(httppost3);
		        HttpResponse response4 = httpclient.execute(httppost4);
		        HttpResponse response5 = httpclient.execute(httppost5);
		
		        //read the response from Services endpoint
		        String jsonResponse1 = EntityUtils.toString(response1.getEntity());
		        String jsonResponse2 = EntityUtils.toString(response2.getEntity());
		        String jsonResponse3 = EntityUtils.toString(response3.getEntity());
		        String jsonResponse4 = EntityUtils.toString(response4.getEntity());
		        String jsonResponse5 = EntityUtils.toString(response5.getEntity());
		        if (!jsonResponse1.equals("")){
		        	//número de personas
		        	resp.add(jsonResponse1);
		        	//nacionalidad
		        	resp.add(jsonResponse2);
		        	//ingredientes
		        	resp.add(jsonResponse3);
		        	//preparación
		        	resp.add(jsonResponse4);
		        	//cantidades de los ingredientes
		        	resp.add(jsonResponse5);
		        }
		        return resp;
		        
		
		    }catch (Exception e) {
		        Log.v("Error adding article", e.getMessage());
		    }
		
		    return null;
		}
	
		// onPostExecute displays the results of the AsyncTask.
		@Override
		protected void onPostExecute(ArrayList<String> result) {
			
			//Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
			titleRef.setText(titleBundle);			
			String aux=imageBundle;
			Picasso.with(getApplicationContext()).load(imageBundle).into(imageRef);
			//result[0] contiene el numero de personas
			String tmp=result.get(0);
			result.set(0, parseNumPersons(result.get(0)));
			numPersRef.setText(result.get(0)+" Personas");
			headerIngredients.setText("Ingredientes");
			//result[1] contiene la nacionalidad
			result.set(1, parseNationality(result.get(1)));
			nationalityRef.setText(result.get(1));
			//result[2] contiene los ingredientes
			initializeArrayIngredients();
			initializeArrayQuantities();
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
			
			adapter = new ItemIngredientsAdapter(activity,ingredients,quantities,units);
			// Asignamos el Adapter al ListView, en este punto hacemos que el
	     	// ListView muestre los datos que queremos.
			ingredientsRef=(ListView)findViewById(R.id.ingredients);
			ingredientsRef.setAdapter(adapter);
			//result[3] contiene la preparación
			headerPreparation.setText("Preparación");
			String aux1=StringEscapeUtils.unescapeJava(result.get(3));
			result.set(3,parsePreparation(aux1));
			preparationRef.setText(result.get(3));
			
		}
		
		
		
		public String parseNumPersons(String result){
			String [] tmp1=result.split("\\[\"");
			tmp1=tmp1[1].split("\""+"\\]");
			return  tmp1[0];
		}
		
		public String parseNationality(String result){
			if(!result.equals("[null]")){
				String [] tmp1=result.split("\\[\"");
				tmp1=tmp1[1].split("\""+"\\]");
	    	    UnicodeUnescaper unescaper = new UnicodeUnescaper();
	    	    return unescaper.translate(tmp1[0]);
			}
			return "";
		}
		
		public String parsePreparation(String result){
			if(!result.equals("[null]")){
				String aux1=result.replaceAll("<ol>", "");
				String aux2=aux1.replaceAll("<li>", "<p>");
				String aux3=aux2.replaceAll("</li>", "</p>");
				String aux4=aux3.replaceAll("</ol>", "");
				String aux5=aux4.replaceAll("<strong>", "");
				String aux6=aux5.replaceAll("</strong>", "");
				String aux7=aux6.replaceAll("&nbsp", "");
				String [] tmp1=aux7.split("<p>");
				String [] tmp2=null;
				String resp="";
				UnicodeUnescaper unescaper = new UnicodeUnescaper();
				//if(tmp1[0].equals("["+"\"")){
					for (int i = 1; i < tmp1.length; i++) {
						tmp2=tmp1[i].split("</p>");
						tmp2[0]=unescaper.translate(tmp2[0]);
						resp=resp+tmp2[0];
					}
				//}
				
				return resp;
			}
			return "";
		}
		
		public void initializeArrayIngredients(){
			String [] tmp1;
			String [] tmp2;
			
			tmp1=resp.get(2).split("\\["+"\"");
			for (int i = 1; i < tmp1.length; i++) {
				tmp2=tmp1[i].split("\"]");
	    	    UnicodeUnescaper unescaper = new UnicodeUnescaper();
				ingredients.add(new ItemReceta(i,unescaper.translate(tmp2[0])));
			}
			
		}
		
		
		public void initializeArrayQuantities(){
			String [] tmp1;
			String [] tmp2;
			String [] tmp3;
			
			tmp1=resp.get(4).split("\\["+"\"");
			for (int i = 1; i < tmp1.length; i=i+2) {
				tmp2=tmp1[i].split("\"]");
				quantities.add(new ItemReceta(i,tmp2[0]));
			}
			
			for (int j = 2; j < tmp1.length; j=j+2) {
				tmp3=tmp1[j].split("\"]");
				units.add(new ItemReceta(j,tmp3[0]));
			}
			
		}
	}

}

