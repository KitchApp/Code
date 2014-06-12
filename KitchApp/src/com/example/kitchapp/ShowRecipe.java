package com.example.kitchapp;

import java.util.ArrayList;
import java.util.List;

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
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
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
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_recipe);
	    
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
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
	
	
	private class GetInfoRecipeById extends AsyncTask<String, Integer, ArrayList<String>>{
		Activity activity;
		ArrayList<String> resp=new ArrayList<String>();
		ArrayList<ItemReceta> ingredients=new ArrayList<ItemReceta>();
		ArrayList<ItemReceta> quantities=new ArrayList<ItemReceta>();
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
		    HttpPost httppost5 = new HttpPost("http://www.kitchapp.es/getQuantityIngredientsByIdRecipe.php?entity_id="+idRecipeBundle);
		    
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
		        StringEntity se4 = new StringEntity(json3.toString());
		        StringEntity se5 = new StringEntity(json4.toString());
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
			result.set(0, parseNumPersonsAndNationality(result.get(0)));
			numPersRef.setText(result.get(0)+" Personas");
			headerIngredients.setText("Ingredientes");
			//result[1] contiene la nacionalidad
			//result.set(1, parseNumPersonsAndNationality(result.get(1)));
			nationalityRef.setText(result.get(1));
			//result[2] contiene los ingredientes
			initializeArrayIngredients();
			initializeArrayQuantities();
			// Inicializamos el adapter.
			//adapter = new ItemRecetaAdapter(activity,ingredients);
			adapter = new ItemIngredientsAdapter(activity,ingredients,quantities);
			// Asignamos el Adapter al ListView, en este punto hacemos que el
	     	// ListView muestre los datos que queremos.
			ingredientsRef=(ListView)findViewById(R.id.ingredients);
			ingredientsRef.setAdapter(adapter);
			//result[3] contiene la preparación
			headerPreparation.setText("Preparación");
			result.set(3,parseNumPersonsAndNationality(result.get(3)));
			preparationRef.setText(result.get(3));
			//parsear preparacion
			//preparationRef.setText(text); 
			
		}
		
		public String parseNumPersonsAndNationality(String result){
			if(!result.equals("[null]")){
				String [] tmp1=result.split("\"");
				//String [] tmp2=tmp1[1].split("\""+"]");
				return tmp1[1];
			}
			return "";
		}
		
		
		public void initializeArrayIngredients(){
			String [] tmp1;
			String [] tmp2;
			
			tmp1=resp.get(2).split("\\["+"\"");
			for (int i = 1; i < tmp1.length; i++) {
				tmp2=tmp1[i].split("\"]");
				ingredients.add(new ItemReceta(i,tmp2[0]));
			}
			
		}
		
		public void initializeArrayQuantities(){
			String [] tmp1;
			String [] tmp2;
			
			tmp1=resp.get(4).split("\\["+"\"");
			for (int i = 1; i < tmp1.length; i++) {
				tmp2=tmp1[i].split("\"]");
				quantities.add(new ItemReceta(i,tmp2[0]));
			}
			
		}
	}

}

