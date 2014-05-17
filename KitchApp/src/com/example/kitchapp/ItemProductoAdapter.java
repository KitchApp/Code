package com.example.kitchapp;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
<<<<<<< HEAD
=======
import android.widget.Toast;
>>>>>>> c6cdd3dc9d1dde44a6ac404340440f340d8c9e81

public class ItemProductoAdapter extends BaseAdapter {
  protected Activity activity;
  protected ArrayList<ItemProducto> items;
         
  public ItemProductoAdapter(Activity activity, ArrayList<ItemProducto> items) {
    this.activity = activity;
    this.items = items;
  }
 
  @Override
  public int getCount() {
    return items.size();
  }
 
  @Override
  public Object getItem(int position) {
    return items.get(position);
  }
 
  @Override
  public long getItemId(int position) {
    return items.get(position).getId();
  }
 
  @Override
  public View getView(int position, View contentView, ViewGroup parent) {
    View vi=contentView;
         
    if(contentView == null) {
      LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      vi = inflater.inflate(R.layout.activity_item_listview, null);
    }
    
    CheckBox checkProduct = (CheckBox) vi.findViewById(R.id.checkBox_Product);
    checkProduct.setOnClickListener(new OnClickListener() {
    	@Override
    	public void onClick(View v) {
    		ItemProducto data = (ItemProducto) v.getTag();
    		data.setSelected(((CheckBox) v).isChecked());
    	}
    });
<<<<<<< HEAD
=======

>>>>>>> c6cdd3dc9d1dde44a6ac404340440f340d8c9e81
             
    ItemProducto item = items.get(position);
         
    /*ImageView image = (ImageView) vi.findViewById(R.id.imagen);
    int imageResource = activity.getResources().getIdentifier(item.getRutaImagen(), null, activity.getPackageName());
    image.setImageDrawable(activity.getResources().getDrawable(imageResource));*/
         
    TextView nombre = (TextView) vi.findViewById(R.id.textView_Item_Name);
    nombre.setText(item.getNombre());
         
    TextView cantidad = (TextView) vi.findViewById(R.id.textView_Item_Cant);
<<<<<<< HEAD
    
=======

    cantidad.setText(item.getCantidad() + "");

>>>>>>> c6cdd3dc9d1dde44a6ac404340440f340d8c9e81
    if (item.getCantidad() == 0) {
    	cantidad.setText("");
    }
    else {
    	cantidad.setText(item.getCantidad() + "");
    }
    
    checkProduct.setChecked(item.isSelected());
    checkProduct.setTag(item);
 
    return vi;
  }
}
