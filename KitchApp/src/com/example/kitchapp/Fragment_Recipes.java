package com.example.kitchapp;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang3.text.translate.UnicodeEscaper;
import org.apache.commons.lang3.text.translate.UnicodeUnescaper;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.json.JSONTokener;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Fragment_Recipes extends Fragment {
	
	//private ArrayList<ItemReceta> options=new ArrayList<ItemReceta>();
	private ArrayList<ItemRecipeWithImage> options=new ArrayList<ItemRecipeWithImage>();
	private ListView list;
	private View rootView;
	private EditText userInput;
	boolean existRecipe=false;
	private Context context;
	private String searchFilter;	
	private Handler_Sqlite helper;
	private ArrayList<ItemProducto> products;
	public HashMap<String,ArrayList<String>> hashIngredients;
	public HashMap<String,String> hashImages;
	public HashMap<String,ArrayList<String>> hashQuantUnit;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		hashIngredients=new HashMap<String, ArrayList<String>>();
		hashImages=new HashMap<String, String>();
		hashQuantUnit=new HashMap<String, ArrayList<String>>();
		context = container.getContext();
		rootView = inflater.inflate(R.layout.activity_mostrar_categoria_recetas, container, false);	
		initializeArrayListRecipes();
		helper=new Handler_Sqlite(getActivity());
		list = (ListView)rootView.findViewById(R.id.listViewCatRec);
		ItemAdapterRecipeWithImage adapter;
		// Inicializamos el adapter.
		adapter = new ItemAdapterRecipeWithImage(getActivity(),options);
		// Asignamos el Adapter al ListView, en este punto hacemos que el
		// ListView muestre los datos que queremos.
		list.setAdapter(adapter);
		list.setOnItemClickListener(new OnItemClickListener(){	 
		    @Override
		    public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
		        // TODO Auto-generated method stub

		    	switch(position){
	    		
		    	// Recetas por Ingrediente
		        case 0: open_Dialog(position);
		        		break;

		        // Recetas por Nacionalidad		        		
		        case 1: open_Dialog(position);
		        		break;
		        
		        		// Recetas por Dieta	    	
		        case 2: open_Dialog_Spinner(position);
		        		break;
		         }
		    }
		});
		
		return rootView;	
	}	
	
		
	
	private void initializeArrayListRecipes() {
		options.add(new ItemRecipeWithImage(R.drawable.ingrediente,"Por ingrediente"));
		options.add(new ItemRecipeWithImage(R.drawable.nacionalidad,"Nacionalidad"));
		options.add(new ItemRecipeWithImage(R.drawable.dieta,"Dieta"));
		options.add(new ItemRecipeWithImage(R.drawable.midespensa,"Con mi despensa"));
		options.add(new ItemRecipeWithImage(R.drawable.rapidas,"Modo de cocina(Rapidas)"));
		options.add(new ItemRecipeWithImage(R.drawable.intolerancias,"Intolerancias"));
		options.add(new ItemRecipeWithImage(R.drawable.tipoplato,"Tipo de plato"));		
	}
	
	
	public void open_Dialog(final int position) {

		LayoutInflater li = LayoutInflater.from(getActivity());
		View promptsView = li.inflate(R.layout.dialog_name_ingredient_recipe, null);

		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());

		// set prompts.xml to alertdialog builder
		alertDialogBuilder.setView(promptsView);

		userInput = (EditText) promptsView.findViewById(R.id.editTextDialogUserInput);
		switch (position) {
			case 0: userInput.setHint("Introduzca ingrediente");
					break;
			case 1: userInput.setHint("Introduzca nacionalidad");
					break;
			case 3: userInput.setHint("Introduzca ");
					break;			
		}
        
		// set dialog message
		alertDialogBuilder.setCancelable(false).setPositiveButton("OK",new DialogInterface.OnClickListener() {
					    public void onClick(DialogInterface dialog,int id) {
					    	switch (position){
					    	case 0: new GetTitleImageByIngredient().execute();			    
					    			break;
					    			
					    	case 1: new GetTitleImageByNationality().execute();
					    			break;
					    	}
					    	
					    	//resto de opciones
					    	
					    }
				})
		.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			    public void onClick(DialogInterface dialog,int id) {
			    	dialog.cancel();
			    }
		});

		// create alert dialog
		AlertDialog alertDialog = alertDialogBuilder.create();

		// show it
		alertDialog.show();
	}
	

	
	public void open_Dialog_Spinner(final int position){
		
		String aux= "";		
		switch (position) {
			case 2: aux= "Seleccione una Dieta";
				break;
				
				
		}
		
		AlertDialog.Builder Dialog = new AlertDialog.Builder(getActivity());
	    Dialog.setTitle(aux);

	    LayoutInflater li = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    View dialogView = li.inflate(R.layout.dialog_recipe_spinner, null);
	    Dialog.setView(dialogView);

	    Dialog.setPositiveButton("Ok",new DialogInterface.OnClickListener() {
	                   public void onClick(DialogInterface dialog, int whichButton) {
	                	   switch (position){
	                	   		case 2: new GetTitleImageByDiet().execute();
	                	   			break;
	                	   			
	                	   		  }		
	                   }
	    });	    	    

	    Dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
	                   public void onClick(DialogInterface dialog, int whichButton) {

	                   }
	    });
	     
	    Dialog.show();
	     
	    Spinner spinnercategory = (Spinner)dialogView.findViewById(R.id.viewSpin);
	    
	    switch (position){
	    
	    case 2: ArrayAdapter<CharSequence> adapterDiet = ArrayAdapter.createFromResource(context, R.array.dietas, android.R.layout.simple_spinner_item);	     
	    		adapterDiet.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    		spinnercategory.setAdapter(adapterDiet);
	    		break;
	    }
	    	    
	     spinnercategory.setOnItemSelectedListener(new OnItemSelectedListener() {

	     public void onItemSelected(AdapterView<?> parent, View arg1,int arg2, long arg3) {
	    	 searchFilter = parent.getSelectedItem().toString();
	    	 //String tmp=searchFilter;	    	 
	     }

	     public void onNothingSelected(AdapterView<?> arg0) {
	                // TODO Auto-generated method stub
	     }
	     });
		
	}	
	
	// (0) Busqueda por Ingrediente.
	private class GetTitleImageByIngredient extends AsyncTask<String, Integer, ArrayList<String>>{
		
		ArrayList<String> resp=new ArrayList<String>();
		HttpPost httppost1;
		HttpPost httppost2;
		
		@Override
	    protected ArrayList<String> doInBackground(String... urls) {
	    	
			HttpClient httpclient = new DefaultHttpClient();
			String searchFilter=userInput.getText().toString().trim();
			//set the remote endpoint URL
		    
			try {
				httppost1 = new HttpPost("http://www.kitchapp.es/getRecipesTitleByIngredient.php?field_ingrediente_nombre_value="+URLEncoder.encode(searchFilter,"UTF-8"));
				httppost2 = new HttpPost("http://www.kitchapp.es/getUrlsRecipesImagesByIngredient.php?field_ingrediente_nombre_value="+URLEncoder.encode(searchFilter,"UTF-8"));
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		    		    
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
		        //if (!jsonResponse1.equals("")){
		        	//existRecipe=true;
		        	resp.add(jsonResponse1);
		        	resp.add(jsonResponse2);
		        	resp.add(searchFilter);
		        //}
		        
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
				Intent intent = new Intent(getActivity(),ShowListRecipes.class);
				intent.putStringArrayListExtra("recipes", result);
		    	startActivity(intent);
			//}	    		    
		}
	}
			
	// (1) Busqueda por nacionalidad
	private class GetTitleImageByNationality extends AsyncTask<String, Integer, ArrayList<String>>{
	
		ArrayList<String> resp=new ArrayList<String>();
		HttpPost httppost3;
		HttpPost httppost4;
		@Override
		protected ArrayList<String> doInBackground(String... urls) {
		    	
			HttpClient httpclient = new DefaultHttpClient();
			String searchFilter=userInput.getText().toString().trim();
		    //set the remote endpoint URL
			if (searchFilter.equals("Española") || searchFilter.equals("española"))
				searchFilter="Espanola";
			    
			try{
				httppost3 = new HttpPost("http://www.kitchapp.es/getTitleByNationality.php?name="+URLEncoder.encode(searchFilter,"UTF-8"));
				httppost4 = new HttpPost("http://www.kitchapp.es/getUrlsRecipesImagesByNationality.php?name="+URLEncoder.encode(searchFilter,"UTF-8"));	
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
				
		    try {
			
		        JSONObject json3 = new JSONObject();
		        JSONObject json4 = new JSONObject();
		        //add serialised JSON object into POST request
		        StringEntity se3 = new StringEntity(json3.toString());
		        StringEntity se4 = new StringEntity(json4.toString());
		        //set request content type
		        se3.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
		        httppost3.setEntity(se3);
		        se4.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
		        httppost4.setEntity(se4);
					
		        //send the POST request
		        HttpResponse response3 = httpclient.execute(httppost3);
		        HttpResponse response4 = httpclient.execute(httppost4);
			
		        //read the response from Services endpoint
		        String jsonResponse3 = EntityUtils.toString(response3.getEntity());
		        String jsonResponse4 = EntityUtils.toString(response4.getEntity());
		        //if (!jsonResponse3.equals("")){
		        	//existRecipe=true;
		        	resp.add(jsonResponse3);
		        	resp.add(jsonResponse4);
		        	resp.add(searchFilter);
		        //}
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
				Intent intent = new Intent(getActivity(),ShowListRecipes.class);				
				intent.putStringArrayListExtra("recipes", result);
		    	startActivity(intent);
			//}				
		}
	}
	
	
	// (2) Busqueda por Dieta
		private class GetTitleImageByDiet extends AsyncTask<String, Integer, ArrayList<String>>{
			
			ArrayList<String> resp=new ArrayList<String>();
			HttpPost httppost1;
			HttpPost httppost2;
			
			@Override
		    protected ArrayList<String> doInBackground(String... urls) {
		    	
				HttpClient httpclient = new DefaultHttpClient();
				//String searchFilter=userInput.getText().toString().trim();
			    //set the remote endpoint URL
			    //HttpPost httppost1 = new HttpPost("http://www.kitchapp.es/getRecipesTitleByDiet.php?field_dieta_value="+searchFilter);
				//HttpPost httppost2 = new HttpPost("http://www.kitchapp.es/getUrlsRecipesImagesByDiet.php?field_dieta_value="+searchFilter);
				try {
					httppost1 = new HttpPost("http://www.kitchapp.es/getRecipesTitleByDiet.php?name="+URLEncoder.encode(searchFilter,"UTF-8"));
					httppost2 = new HttpPost("http://www.kitchapp.es/getUrlsRecipesImagesByDiet.php?name="+URLEncoder.encode(searchFilter,"UTF-8"));
				} catch (UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			    		    
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
			        //if (!jsonResponse1.equals("")){
			        	//existRecipe=true;
			        	resp.add(jsonResponse1);
			        	resp.add(jsonResponse2);
			        	resp.add(searchFilter);
			       // }
			       
			        return resp;
			        		
			    }catch (Exception e) {
			        Log.v("Error adding article", e.getMessage());
			    }
			
			    return null;
			}
		
			// onPostExecute displays the results of the AsyncTask.
			@Override
			protected void onPostExecute(ArrayList<String> result) {
				//if(existRecipe){
					Intent intent = new Intent(getActivity(),ShowListRecipes.class);
					intent.putStringArrayListExtra("recipes", result);
				    	startActivity(intent);
				//}	    		    
			}
		}
	
}
