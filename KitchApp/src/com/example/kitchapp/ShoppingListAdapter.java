package com.example.kitchapp;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.view.View.OnClickListener;

public class ShoppingListAdapter extends BaseAdapter {
  protected Activity activity;
  protected ArrayList<ShoppingListItem> items;
         
  public ShoppingListAdapter(Activity activity, ArrayList<ShoppingListItem> items) {
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
  	return 0;
  }
 
  @Override
  public View getView(int position, View contentView, ViewGroup parent) {
    View vi=contentView;
         
    if(contentView == null) {
      LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      vi = inflater.inflate(R.layout.shoppinglist_item, null);
    }
             
    ShoppingListItem item = items.get(position);
    CheckBox checkList = (CheckBox) vi.findViewById(R.id.checkBox_list);
    checkList.setOnClickListener(new OnClickListener() {
    	@Override
    	public void onClick(View v) {
    		ShoppingListItem data = (ShoppingListItem) v.getTag();
    		data.setChecked(((CheckBox) v).isChecked());
    		
    	}
    });
        
    TextView nombre = (TextView) vi.findViewById(R.id.text_listName);
    nombre.setText(item.getListName());
    
    checkList.setChecked(item.isChecked());
    checkList.setTag(item);
 
    return vi;
  }
}
