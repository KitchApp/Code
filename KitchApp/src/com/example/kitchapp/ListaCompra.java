/*package com.example.kitchapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.TextView;*/

/*public class ListaCompra extends Activity implements OnClickListener {

	private AlertDialog.Builder builder;
	private TextView dialog_header;
	private EditText nameOfList;
	private Button button_save;
	private Button button_cancel;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lista_compra);
		
		TabHost tabHost= getTabHost(); // creamos el tabHost de la actividad
		TabHost.TabSpec spec; // creamos un recurso para las propiedades de la pestaña
		Intent intentTab1; //intentTab que se utilizara para abrir cada pestaña.
		Resources res= getResources(); // obtenemos los recursos
		
		// se lanza crea el intent para abrir la actividad que en realidad es una clase
		intentTab1= new Intent().setClass(this, ListaCompra.class);
		//intentTab1= new Intent().setComponent(getCallingActivity());
		// se configura la pestaña con sus propiedades
		spec= tabHost.newTabSpec("dialog_crear_lista_compra").setIndicator("CrearL").setContent(intentTab1);
		tabHost.addTab(spec);		
		
	}
	
	private TabHost getTabHost() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.acceso_despensa, menu);
		return true;
	}

	//Dialog_crear_lista
	public void crearLista(View view,int position) {
		
		builder = new AlertDialog.Builder(this);
		 
        // Get the layout inflater
        LayoutInflater inflater = this.getLayoutInflater();
 
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout

        view = inflater.inflate(R.layout.activity_lista_compra, null);
        
        //dialog_header = (TextView) view.findViewById(R.id.);
         
        //nameOfList = (EditText) view.findViewById(R.id.);
    	
    	button_save = (Button) view.findViewById(R.id.button_save);
    	button_save.setOnClickListener(this);
    	
    	button_cancel = (Button) view.findViewById(R.id.button_cancel);
    	button_cancel.setOnClickListener(this);
    	    	
        builder.setView(view);
                        
        builder.create();
        builder.show();
 
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
}*/
