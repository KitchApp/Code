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
   	  // return items.get(position).getListName();
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
    
         
    /*ImageView image = (ImageView) vi.findViewById(R.id.imagen);
    int imageResource = activity.getResources().getIdentifier(item.getRutaImagen(), null, activity.getPackageName());
    image.setImageDrawable(activity.getResources().getDrawable(imageResource));*/
         
    TextView nombre = (TextView) vi.findViewById(R.id.text_listName);
    nombre.setText(item.getListName());
         
    /*TextView cantidad = (TextView) vi.findViewById(R.id.textView_Item_Cant);
    cantidad.setText(item.getCantidad() + "");*/
 
    checkList.setChecked(item.isChecked());
    checkList.setTag(item);
 
    return vi;
  }
}




/*package com.example.kitchapp;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

public class ShoppingListAdapter extends BaseAdapter {
	
	private LayoutInflater layoutInflater;
	private ArrayList<ShoppingListItem> lists;
	private Activity activity;
	
	public ShoppingListAdapter(Activity activity, ArrayList<ShoppingListItem> lists) {
		this.activity = activity;
		this.lists = lists;
	}
	
	public View getView(int position, View convertView, ViewGroup parent)
    {
        // holder pattern
		Holder holder = null;
        if (convertView == null) { 
        	holder = new Holder();
        	convertView  = layoutInflater.inflate(R.layout.shoppinglist_item, null);
            holder.setTextListName((TextView) convertView.findViewById(R.id.text_listName));
            holder.setCheckBox((CheckBox) convertView.findViewById(R.id.checkBox_list));
            convertView.setTag(holder);
        }
        else
        {
            holder = (Holder) convertView.getTag();
        }
 
        final ShoppingListItem row = lists.get(position);
        holder.getTextListName().setText(row.getListName());
        holder.getCheckBox().setTag(row.getListName());
        //holder.getCheckBox().setChecked(row.isChecked());
        holder.getCheckBox().setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (row.getListName().equals(buttonView.getTag().toString()));
				
			}
        	         	
        });
        return convertView;
    }

	@Override
	public int getCount() {
		return lists.size();
	}

	@Override
	public Object getItem(int position) {
		return lists.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	 
}
 
class Holder
{
    TextView textListName;
    CheckBox checkBox;

	public TextView getTextListName() {
		return textListName;
	}

	public void setTextListName(TextView textListName) {
		this.textListName = textListName;
	}
 
    public CheckBox getCheckBox() {
    	return this.checkBox;
    }
    
    public void setCheckBox(CheckBox checkBox) {
    	this.checkBox = checkBox;
    }
}*/
