package com.example.kitchapp;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class MostrarProductosCategoria extends Activity implements OnClickListener {
	
	private ListView list;
    private ArrayList<ItemProducto> products;
    private int pos;
    private TextView cantProduct;
    private Button save;
    private Button cancel;
    private int cantFinal;
    private AlertDialog.Builder builder;
    Handler_Sqlite helper=new Handler_Sqlite(this);
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mostrar_productos_categoria);
		products = new ArrayList<ItemProducto>();
		
		
		/*SQLiteDatabase tmp=helper.open();	
		if (tmp!=null){
				helper.insertProducto("Yogurt",2,1);
				helper.insertProducto("Manzana",3,2 );
				helper.insertProducto("Magdalena",5,3);
				helper.insertProducto("Leche",6,1);
				helper.insertProducto("coca-cola", 4, 4);
				helper.insertProducto("Pollo", 1, 5);
				helper.insertProducto("Merluza", 1, 6);
				helper.insertProducto("Red bull", 4, 4);
				helper.insertProducto("Arroz largo", 4, 8);
				helper.insertProducto("Guisantes", 1, 9);
				helper.insertProducto("Gel baño", 4, 10);
				helper.insertProducto("Macarrones", 2, 8);
				helper.insertProducto("Helado Fresa", 4, 9);
				/*helper.insertCategory(1,"Lácteos")
				helper.insertCategory(2,"Frutas y Verduras")
				helper.insertCategory(3,"Pan y Bollería")
				helper.insertCategory(4,"Bebidas")
				helper.insertCategory(5,"Carnes")
				helper.insertCategory(6,"Pescados")
				helper.insertCategory(7,"Salsas y condimentos")
				helper.insertCategory(8,"Arroces")
				helper.insertCategory(9,"Congelados")
				helper.insertCategory(10,"Varios")*/
		
		Bundle extraLacteos= this.getIntent().getExtras();
		if(extraLacteos!=null){
			if(extraLacteos.getInt("lacteos")==1){
				initializeArrayList(extraLacteos.getInt("lacteos"));
			}
			else if(extraLacteos.getInt("frutas")==2){
				initializeArrayList(extraLacteos.getInt("frutas"));
			}
			else if(extraLacteos.getInt("pan")==3){
					initializeArrayList(extraLacteos.getInt("pan"));
				}
			else if(extraLacteos.getInt("bebidas")==4){
				initializeArrayList(extraLacteos.getInt("bebidas"));
			}
			else if(extraLacteos.getInt("carnes")==5){
				initializeArrayList(extraLacteos.getInt("carnes"));
			}
			else  if(extraLacteos.getInt("pescados")==6){
				initializeArrayList(extraLacteos.getInt("pescados"));
			}
			else  if(extraLacteos.getInt("salsas")==7){
				initializeArrayList(extraLacteos.getInt("salsas"));
			}
			else if(extraLacteos.getInt("arroces")==8){
				initializeArrayList(extraLacteos.getInt("arroces"));
			}
			else  if(extraLacteos.getInt("congelados")==9){
				initializeArrayList(extraLacteos.getInt("congelados"));
			}
			else  if(extraLacteos.getInt("varios")==10){
				initializeArrayList(extraLacteos.getInt("varios"));
			}
				
		}
			
			
		//}		
			
		
		helper.close();
		
		
		
		Bundle extra = this.getIntent().getExtras();
		
		
		if (extra!=null) {
			int key = extra.getInt("key");
			if (key == 0) {
				
				ItemProducto item = new ItemProducto(products.size(),extra.getString("nameProduct"),extra.getInt("cantProduct"));
				boolean encontrado = false;
				int i = 0;
				while (i<products.size() && !encontrado){
					ItemProducto prod = products.get(i);
					String name = prod.getNombre().toLowerCase();
					if (name.equals(item.getNombre().toLowerCase())) {
						encontrado = true;
					}
					i++;
				}
				/*if (!encontrado) {
					products.add(item);
				}
				else {
					errorProduct();
				}*/
			}
			else {
				int posi = extra.getInt("posicion");
				int cantProducto = extra.getInt("cantProduct");
				ItemProducto producto = products.get(posi);
				producto.setCantidad(cantProducto);
				products.set(posi,producto);
			}
		}
		
		TextView link_atras = (TextView) findViewById(R.id.textView_Atras);
		link_atras.setOnClickListener(this);
		list = (ListView)findViewById(R.id.listViewProducts);
		ItemProductoAdapter adapter;
		// Inicializamos el adapter.
		adapter = new ItemProductoAdapter(this,products);
		// Asignamos el Adapter al ListView, en este punto hacemos que el
		// ListView muestre los datos que queremos.
		list.setAdapter(adapter);
		//setContentView(R.layout.activity_mostrar_productos_categoria);
		list.setOnItemClickListener(new OnItemClickListener(){
			 
		    @Override
		    public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
		        // TODO Auto-generated method stub
		        //Toast.makeText(getApplicationContext(), "Ha pulsado el item " + position, Toast.LENGTH_SHORT).show();
		        cantFinal = products.get(position).getCantidad();
		        modificarProducto(arg1,position);
		 
		    }
		 
		}); 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.mostrar_productos_categoria, menu);
		return true;
	}
	
	public void modificarProducto(View view,int position) {
		
		builder = new AlertDialog.Builder(this);
		 
        // Get the layout inflater
        LayoutInflater inflater = this.getLayoutInflater();
 
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout

        view = inflater.inflate(R.layout.activity_modificar_producto_despensa, null);
        cantProduct = (TextView) view.findViewById(R.id.cantProduct);
        cantProduct.setText(products.get(position).getCantidad() + "");
    	pos = position;
    	save = (Button) view.findViewById(R.id.button_save);
    	save.setOnClickListener(this);
    	cancel = (Button) view.findViewById(R.id.button_cancel);
    	cancel.setOnClickListener(this);
        builder.setView(view);
                
        
        builder.create();
        builder.show();
 
	}
	/*@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}*/

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()) {
			case R.id.button_save:
				Intent intent = new Intent(this,MostrarProductosCategoria.class);
				ItemProducto prod = products.get(pos);
				intent.putExtra("posicion",pos);
				intent.putExtra("cantProduct",prod.getCantidad());
				intent.putExtra("key",1);
				//intent.putExtra("productos",products);
				startActivity(intent);
				break;
			
			case R.id.button_cancel:
				ItemProducto produ = products.get(pos);
            	produ.setCantidad(cantFinal);
            	products.set(pos,produ);
            	Intent i = new Intent(this,MostrarProductosCategoria.class);
				i.putExtra("posicion",pos);
				i.putExtra("cantProduct",produ.getCantidad());
				i.putExtra("key",1);
				startActivity(i);
				break;
			
			case R.id.textView_Atras:
				Intent j = new Intent(this,AccesoDespensa.class);
				startActivity(j);
				break;
				
		}
            	
				
	}
	
	private void initializeArrayList(Integer category) {
	
		
		products=helper.read(category);
		
		/*products.add(new ItemProducto(1,helper.read()[1],4,""));
		products.add(new ItemProducto(2,helper.read()[2],3,""));
		products.add(new ItemProducto(3,helper.read()[4],4,""));
		products.add(new ItemProducto(4,"Natillas Chocolate Danone",2,""));
		products.add(new ItemProducto(5,"Queso Semicurado El Ventero",1,""));*/
		
	}
	
	public void decrementCant(View view) {
		/*cantProduct = (TextView) view.findViewById(R.id.cantProduct);
		int cant = Integer.parseInt(cantProduct.getText().toString());
		int cantModified = cant--;
		cantProduct.setText(cantModified + "");*/
		
		ItemProducto product = products.get(pos);
		if (product.getCantidad() > 0) {
			product.setCantidad(product.getCantidad()-1);
			products.set(pos,product);
			modificarProducto(view,pos);
		}
		
	}
	
	public void incrementCant(View view) {
		/*cantProduct = (TextView) view.findViewById(R.id.cantProduct);
		int cant = Integer.parseInt(cantProduct.getText().toString());
		int cantModified = cant--;
		cantProduct.setText(cantModified + "");*/
		
		ItemProducto product = products.get(pos);
		product.setCantidad(product.getCantidad()+1);
		products.set(pos,product);
		modificarProducto(view,pos);
		
	}
	
	public void errorProduct() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		 
	    builder.setTitle("Error")
	            .setIcon(
	                    getResources().getDrawable(
	                            R.drawable.close))
	            .setMessage("Producto ya existente en la despensa")
	            .setNeutralButton(R.string.ok, new DialogInterface.OnClickListener() {
	 
	                @Override
	                public void onClick(DialogInterface arg0, int arg1) {
	                	arg0.cancel();
	                }
	            });
	 
	    builder.create();
	    builder.show();
	}
}
