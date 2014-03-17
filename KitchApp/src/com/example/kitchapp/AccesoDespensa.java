package com.example.kitchapp;


import java.util.ArrayList;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;


public class AccesoDespensa extends Activity implements OnClickListener {
	
	private static final int REQUEST_CODE = 1234;
	Dialog match_text_dialog;
	ListView textlist;
	ArrayList<String> matches_text;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_acceso_despensa);

		Button b1 = (Button) findViewById(R.id.button1);
		b1.setOnClickListener(this);

		Button button_add=(Button)findViewById(R.id.button_add);
		button_add.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.acceso_despensa, menu);
		return true;
	}
	

	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()){

		case R.id.button1:
			Intent i = new Intent(this,MostrarProductosCategoria.class);
			startActivity(i);
			break;
			
		case R.id.button_add:

			alertDialog(v);
		}
	}
	
	
	public void alertDialog(View v){
		final String [] items = new String[] {"Manualmente", "Voz", "Código de barras" };
	    final Integer[] icons = new Integer[] {R.drawable.teclado_android, R.drawable.microfono, R.drawable.barras};
	    ListAdapter adapter = new ItemAdapter(this, items, icons);
	    
        new AlertDialog.Builder(this).setAdapter(adapter, new DialogInterface.OnClickListener() {
        	public void onClick(DialogInterface dialog, int item ) {
        		if (item==0)
        			addManualmente();
        		else if (item == 1) {
        			addVoice();
        		}
        			
	        }
	    }).show();
	}
	
	
	public void addManualmente() {
		  Intent intent = new Intent(this,AddManualmente.class);
		  startActivity(intent);
	  }
	
	public void addVoice() {
		if(isConnected()){
       	 Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        	 intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
        	 RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        	 startActivityForResult(intent, REQUEST_CODE);
       			 }
       	else{
       		Toast.makeText(getApplicationContext(), "Please Connect to Internet", Toast.LENGTH_LONG).show();
       	}
	}
	
	public boolean isConnected() {
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo net = cm.getActiveNetworkInfo();
	    if (net!=null && net.isAvailable() && net.isConnected()) {
	        return true;
	    } else {
	        return false; 
	    }
	}
	
	@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
     if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
    	    
     match_text_dialog = new Dialog(AccesoDespensa.this);
     match_text_dialog.setContentView(R.layout.dialog_matches);
     match_text_dialog.setTitle("Select Matching Text");
     textlist = (ListView)match_text_dialog.findViewById(R.id.listDialogVoice);
     matches_text = data
		     .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
     ArrayAdapter<String> adapter =    new ArrayAdapter<String>(this,
    	     android.R.layout.simple_list_item_1, matches_text);
     textlist.setAdapter(adapter);
     textlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
     @Override
     public void onItemClick(AdapterView<?> parent, View view,
                             int position, long id) {
    	 
    	 match_text_dialog.hide();
    	 addProduct(position);
     }
 });
     
     match_text_dialog.show();
     
     }
     super.onActivityResult(requestCode, resultCode, data);
    }
	
	public void addProduct(int position) {
		try {
			Intent intent = new Intent(this,MostrarProductosCategoria.class);
   	 		String[] prod = matches_text.get(position).split("");
   	 		int cant = Integer.parseInt(prod[prod.length - 1]);
   	 		String name = "";
   	 		for (int i = 0;i < prod.length - 1;i++) {
   	 			name += prod[i];
   	 			name += "";
   	 		}
   	 		intent.putExtra("key",0);
   	 		intent.putExtra("nameProduct",name);
   	 		intent.putExtra("cantProduct", cant);
   	 		startActivity(intent);
		}
		catch (NumberFormatException e) {
			Toast.makeText(this, "La cantidad de producto especificada tiene que ser un número", Toast.LENGTH_SHORT).show();
		}
	}

}
