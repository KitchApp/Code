package com.example.kitchapp;

	import java.util.ArrayList;

	import android.app.Activity;
<<<<<<< HEAD
	import android.content.Context;
	import android.view.LayoutInflater;
	import android.view.View;
	import android.view.View.OnClickListener;
	import android.view.ViewGroup;
	import android.widget.BaseAdapter;	
	import android.widget.TextView;
	import android.widget.Toast;

public class ItemRecetaAdapter  extends BaseAdapter {

	  protected Activity activity;
	  protected ArrayList<ItemReceta> items;
	         
	  public ItemRecetaAdapter(Activity activity,ArrayList<ItemReceta> items ) {
	  	this.activity = activity;
		this.items = items;
	  }
	 
	  @Override
	  public int getCount() {
	    	return items.size();
=======
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;	
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ItemRecetaAdapter  extends BaseAdapter {
	  protected Activity activity;
	  protected ArrayList<ItemReceta> items;
	  protected Context context;
	         
	  public ItemRecetaAdapter(Activity activity,ArrayList<ItemReceta> items ) {
	    this.activity = activity;
	    this.items = items;
	  }
	  
	  public ItemRecetaAdapter(Context activity,ArrayList<ItemReceta> items ) {
		    this.context = activity;
		    this.items = items;
		  }
	 
	  @Override
	  public int getCount() {
	    return items.size();
		  
>>>>>>> Rama-Vivi-Android
	  }
	 
	  @Override
	  public Object getItem(int position) {
<<<<<<< HEAD
	    	return items.get(position);
=======
	    return items.get(position);
		  
>>>>>>> Rama-Vivi-Android
	  }
	 
	  @Override
	  public long getItemId(int position) {
<<<<<<< HEAD
	    	return items.get(position).getId();
=======
	    return items.get(position).getId();
>>>>>>> Rama-Vivi-Android
	  }
	 
	  @Override
	  public View getView(int position, View contentView, ViewGroup parent) {
<<<<<<< HEAD
	    	View vi=contentView;
	         
	    	if(contentView == null) {
	      	    LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	      	    vi = inflater.inflate(R.layout.activity_item_listviewrec, null);
	    	}
	    
	    	ItemReceta item = items.get(position);
	         
	    	/*ImageView image = (ImageView) vi.findViewById(R.id.imagen);
	    	int imageResource = activity.getResources().getIdentifier(item.getRutaImagen(), null, activity.getPackageName());
	    	image.setImageDrawable(activity.getResources().getDrawable(imageResource));*/
	         
	    	TextView nombre = (TextView) vi.findViewById(R.id.textView_Item_Name);
	    	nombre.setText(item.getNombre());
	         
	    	return vi;
=======
	    View vi=contentView;
	         
	    if(contentView == null) {
	      LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	      vi = inflater.inflate(R.layout.activity_item_listviewrec, null);
	    }
	    
	             
	    ItemReceta item = items.get(position);
	         
	    /*ImageView image = (ImageView) vi.findViewById(R.id.ivItem);
	    int imageResource = activity.getResources().getIdentifier(item.getRutaImagen(), null, activity.getPackageName());
	    image.setImageDrawable(activity.getResources().getDrawable(imageResource));*/
	         
	    TextView nombre = (TextView) vi.findViewById(R.id.textView_Item_Name);
	    nombre.setText(item.getNombre());
	         
	    
	    
	   
	    return vi;
>>>>>>> Rama-Vivi-Android
	  }
}
