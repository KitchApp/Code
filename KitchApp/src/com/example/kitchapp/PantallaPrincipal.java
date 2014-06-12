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

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class PantallaPrincipal extends ActionBarActivity implements OnClickListener{

	public void onCreate(Bundle savedInstanceState) {
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
				new GetTitleImageTopTen().execute();
				break;
		}
		finish();
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
			        	resp.add(jsonResponse1);
			        	resp.add(jsonResponse2);
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
			Intent intent = new Intent(getApplicationContext(), Recipes.class);
			intent.putStringArrayListExtra("infoTopTen", result);
			startActivity(intent);
		}
	}
	
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_pantalla_ppal, menu);
        return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
		SharedPreferences settings = getSharedPreferences(
				PantallaTransicion.PREFS_NAME, 0);
		SharedPreferences.Editor editor = settings.edit();

		editor.putBoolean("hasLoggedIn", false);
		editor.commit();

		Intent j = new Intent(this, Login.class);
		j.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(j);
		finish();
		return true;
	}
}	
