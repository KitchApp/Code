package com.example.kitchapp;

import java.util.Arrays;
import java.util.List;
import android.content.Context;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ItemAdapter extends ArrayAdapter<String> {

	private List<Integer> images;
		/*Al constructor de nuestro adaptador s�lo le pasaremos el contexto(que ser� la actividad 
  		desde la que se crea el adaptador). En este constructor tan s�lo guardaremos el contexto
  		para nuestro uso posterior y llamaremos al constructor padre, pas�ndole el ID del layout
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

	/*Redefinimos el m�todo getView que es el encargado de generar y rellenar con nuestros datos
	 *todos los controles necesarios de la interfaz gr�fica de cada elemento de la lista.
	 *El m�todo getView() se llamar� cada vez que haya que mostrar un elemento de la lista.
	 *Tras esto, tan s�lo tendremos que obtener la referencia a cada una de nuestras
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
