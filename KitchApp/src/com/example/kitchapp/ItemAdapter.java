package com.example.kitchapp;

import java.util.ArrayList;
<<<<<<< HEAD
import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
=======

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
>>>>>>> Rama-Android
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

<<<<<<< HEAD

public class ItemAdapter extends ArrayAdapter<String> {

	private List<Integer> images;
		/*Al constructor de nuestro adaptador sólo le pasaremos el contexto(que será la actividad 
  		desde la que se crea el adaptador). En este constructor tan sólo guardaremos el contexto
  		para nuestro uso posterior y llamaremos al constructor padre, pasándole el ID del layout
  		que queremos utilizar(layout por defecto en android) y el array que contiene los datos
  		a mostrar.*/
	
	public ItemAdapter(Context context, List<String> items, List<Integer> images) {
		super(context, android.R.layout.select_dialog_item, items);
		this.images = images;
	}

	public ItemAdapter(Context context, String[] items, Integer[] images) {
		super(context, android.R.layout.select_dialog_item, items);
		this.images = Arrays.asList(images);
	}

	/*Redefinimos el método getView que es el encargado de generar y rellenar con nuestros datos
	 *todos los controles necesarios de la interfaz gráfica de cada elemento de la lista.
	 *El método getView() se llamará cada vez que haya que mostrar un elemento de la lista.
	 *Tras esto, tan sólo tendremos que obtener la referencia a cada una de nuestras
	 *etiquetas y configurar el textview.*/
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = super.getView(position, convertView, parent);
	    TextView textView = (TextView) view.findViewById(android.R.id.text1);
	    textView.setWidth(8);
	    textView.setHeight(4);
	    textView.setCompoundDrawablesWithIntrinsicBounds(images.get(position), 0, 0, 0);
	    textView.setCompoundDrawablePadding((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, getContext().getResources().getDisplayMetrics()));
	    return view;
	}

}








=======
public class ItemAdapter extends BaseAdapter {
  protected Activity activity;
  protected ArrayList<Item> items;
         
  public ItemAdapter(Activity activity, ArrayList<Item> items) {
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
      vi = inflater.inflate(R.layout.activity_list_item_layout, null);
    }
             
    Item item = items.get(position);
         
    ImageView image = (ImageView) vi.findViewById(R.id.imagen);
    int imageResource = activity.getResources().getIdentifier(item.getRutaImagen(), null, activity.getPackageName());
    image.setImageDrawable(activity.getResources().getDrawable(imageResource));
         
    TextView nombre = (TextView) vi.findViewById(R.id.nombre);
    nombre.setText(item.getNombre());
         
    /*TextView tipo = (TextView) vi.findViewById(R.id.tipo);
    tipo.setText(item.getTipo());*/
 
    return vi;
  }
}
>>>>>>> Rama-Android
