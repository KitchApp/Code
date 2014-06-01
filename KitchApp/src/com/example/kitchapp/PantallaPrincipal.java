package com.example.kitchapp;

import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class PantallaPrincipal extends Activity implements OnClickListener{

	private Handler_Sqlite helper;
	private ArrayList<String> titulos;
	private ArrayList<String> imagenes;
	private ArrayList<String> topTen;
	
	public void onCreate(Bundle savedInstanceState) {
		helper=new Handler_Sqlite(this);
		helper.open();
		super.onCreate(savedInstanceState);
		// setting default screen to login.xml
		
		setContentView(R.layout.activity_pantalla_principal);
		Button buttonPantry = (Button) findViewById(R.id.button2);
		buttonPantry.setOnClickListener(this);
		Button buttonList = (Button) findViewById(R.id.button1);
		buttonList.setOnClickListener(this);
		Button buttonRecipes = (Button) findViewById(R.id.button3);
		buttonRecipes.setOnClickListener(this);
		
}
	
	
	public void onClick(View v) {
		switch(v.getId()) {
			case R.id.button1:
				Intent i = new Intent(this, ShoppingLists.class);
				startActivity(i);
				break;
			
			case R.id.button2:
				Intent j = new Intent(this, AccesoDespensa.class);
				startActivity(j);
				break;
				
			case R.id.button3:
				loadFavoriteRecipe();
				new GetTitleImageTopTen().execute();
				break;
		}
	}
	
	
	public void loadFavoriteRecipe(){
		//helper=new Handler_Sqlite(this);
		//helper.open();
		titulos=initializeArrayTitleRecipesFromLocalBBDD();
		imagenes=initializeArrayImagesRecipesFromLocalBBDD();
		helper.close();
	}
	
	public ArrayList<String> initializeArrayTitleRecipesFromLocalBBDD(){
		ArrayList<String>items = new ArrayList<String>();
		ArrayList<String>tmp = new ArrayList<String>();
		//Leer de la base de datos local los titulos de las recetas favoritas
		tmp=helper.readInfoFavoriteRecipes("title");
		for (int i = 0; i < tmp.size(); i++) {
			items.add((String)tmp.get(i));
		}
		return items;
	}
	
		
	public ArrayList<String> initializeArrayImagesRecipesFromLocalBBDD(){
		ArrayList<String>items = new ArrayList<String>();
		ArrayList<String>tmp = new ArrayList<String>();
		//Leer de la base de datos local las imagenes de las recetas favoritas
		tmp=helper.readInfoFavoriteRecipes("image");
		for (int i = 0; i < tmp.size(); i++) {
			items.add((String)tmp.get(i));
		}
		return items;
	}
	
	
	private class GetTitleImageTopTen extends AsyncTask<String, Integer, ArrayList<String>>{
		ArrayList<String> resp=new ArrayList<String>();
		HttpPost httppost1;
		HttpPost httppost2;
		@Override
	    protected ArrayList<String> doInBackground(String... urls) {
		    	
			HttpClient httpclient = new DefaultHttpClient();
			//String searchFilter=userInput.getText().toString().trim();
			//set the remote endpoint URL
			    
			httppost1 = new HttpPost("http://www.kitchapp.es/getRecipesTitleTopTen.php?");
			httppost2 = new HttpPost("http://www.kitchapp.es/getUrlsRecipesImagesTopTen.php?");

			try {
			
			        JSONObject json1 = new JSONObject();
			        JSONObject json2 = new JSONObject();
			        //add serialised JSON object into POST request
			        StringEntity se1 = new StringEntity(json1.toString());
			        StringEntity se2 = new StringEntity(json2.toString());
			        //set request content type
			        se1.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
			        httppost1.setEntity(se1);
			        se2.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
			        httppost2.setEntity(se2);
			
			
			        //send the POST request
			        HttpResponse response1 = httpclient.execute(httppost1);
			        HttpResponse response2 = httpclient.execute(httppost2);
			
			        //read the response from Services endpoint
			        String jsonResponse1 = EntityUtils.toString(response1.getEntity());
			        String jsonResponse2 = EntityUtils.toString(response2.getEntity());
			        if (!jsonResponse1.equals("")){
			        	//existRecipe=true;
			        	resp.add(jsonResponse1);
			        	resp.add(jsonResponse2);
			        	//resp.add(searchFilter);
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
			//Toast.makeText(getActivity(), result, Toast.LENGTH_SHORT).show();
			//apellido.setText(result);
			//if(existRecipe){
			/*String tmp="";
			
			infoRecipe.add(result.get(0));
			infoRecipe.add(result.get(0));*/
				//Intent intent = new Intent(getActivity(),ShowRecipe.class);
				/*intent.putStringArrayListExtra("topten", result);
			    startActivity(intent);*/
			//}	      
			//bundle=result;
			Intent intent = new Intent(getApplicationContext(), Recipes.class);
			intent.putStringArrayListExtra("infoTopTen", result);
			intent.putStringArrayListExtra("titleFavorite", titulos);
			intent.putStringArrayListExtra("imagesFavorite", imagenes);
			startActivity(intent);
		}
	}
<<<<<<< HEAD
=======
	
	
>>>>>>> Rama-Vivi-Android
}
