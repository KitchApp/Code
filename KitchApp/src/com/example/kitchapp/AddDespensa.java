package com.example.kitchapp;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class AddDespensa extends Activity implements OnClickListener {
  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_add_despensa);
         
    ListView lv = (ListView)findViewById(R.id.listView);
         
    final ArrayList<Item> itemsCompra = obtenerItems();
         
    ItemAdapter adapter = new ItemAdapter(this, itemsCompra);
         
    lv.setAdapter(adapter);  
    lv.setOnItemClickListener(new OnItemClickListener(){
		 
	    @Override
	    public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
	        // TODO Auto-generated method stub
	        Toast.makeText(getApplicationContext(), "Ha pulsado el item " + position, Toast.LENGTH_SHORT).show();
	        if (position == 0) {
	        	addManualmente(arg1);
	        }
	 
	    }
	 
	}); 
  }
     
  private ArrayList<Item> obtenerItems() {
    ArrayList<Item> items = new ArrayList<Item>();
         
    items.add(new Item(1, "Manualmente", "Tuberculo", "drawable/teclado_android"));
    items.add(new Item(2, "Por voz", "Fruta", "drawable/microfono"));
    items.add(new Item(3, "Por código de barras", "Verdura", "drawable/barras"));
         
    return items;
  }
  
  public void addManualmente(View view) {
	  Intent intent = new Intent(this,AddManualmente.class);
	  startActivity(intent);
  }

@Override
public void onClick(View arg0) {
	// TODO Auto-generated method stub
	
}
}