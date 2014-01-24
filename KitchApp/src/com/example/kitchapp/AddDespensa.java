package com.example.kitchapp;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class AddDespensa extends Activity {
  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_add_despensa);
         
    ListView lv = (ListView)findViewById(R.id.listView);
         
    final ArrayList<Item> itemsCompra = obtenerItems();
         
    ItemAdapter adapter = new ItemAdapter(this, itemsCompra);
         
    lv.setAdapter(adapter);    
  }
     
  private ArrayList<Item> obtenerItems() {
    ArrayList<Item> items = new ArrayList<Item>();
         
    items.add(new Item(1, "Manualmente", "Tuberculo", "drawable/teclado_android"));
    items.add(new Item(2, "Por voz", "Fruta", "drawable/microfono"));
    items.add(new Item(3, "Por código de barras", "Verdura", "drawable/barras"));
         
    return items;
  }
}