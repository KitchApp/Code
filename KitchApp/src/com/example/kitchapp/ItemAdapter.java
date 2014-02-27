package com.example.kitchapp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


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








