<<<<<<< HEAD
package com.example.kitchapp;

import java.util.ArrayList;
import java.util.List;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ShoppingLists extends ActionBarActivity implements OnClickListener{
	
	private ArrayList<ShoppingListItem> shoppingLists;
	private ListView list;
	private ShoppingListAdapter adapter;
	public Handler_Sqlite helper;
	private ArrayList<ShoppingListItem> lists;
	private boolean deleteButtonPressed;
	private MenuItem add_Item;
	private Dialog customDialog;
	private String listName;
	private String listTableName = "listshopping";
	private String column = "name";
	private Context mycontext;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mycontext=this;
		setContentView(R.layout.activity_shopping_lists);
		shoppingLists = new ArrayList<ShoppingListItem>();
		helper=new Handler_Sqlite(this);
		//helper.insertLists("Mercadona");
		initializeArrayList();
		list = (ListView) findViewById(R.id.listView_lists);
		adapter = new ShoppingListAdapter(this, shoppingLists);  // Mirar si hay problemas de compatibilidad entre Activity y SherlockActivity
		list.setAdapter(adapter);	
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long id) {
				// TODO Auto-generated method stub
				ShoppingListItem item = shoppingLists.get(position);
				int idList = helper.getIdList(item.getListName());
				Intent intent = new Intent (mycontext,MostrarProductosLista.class);
				intent.putExtra("idList",idList);
				mycontext.startActivity(intent);
				
			}
			
		});
		
		
		
	}
	
	public void initializeArrayList() {
		
		shoppingLists=helper.readLists();
	}
        
	
	
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_action_bar, menu);
        add_Item = menu.findItem(R.id.add_Product);
        return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		//cambiar add_Product por add(para que sea más general)
		case R.id.add_Product:
			open_Dialog();
			//add_List();
			return true;

		case R.id.delete_Product:
			if (deleteButtonPressed) {
				add_Item.setEnabled(true);
				delete_List();
				hideCheckBox();
				deleteButtonPressed = false;
			} else {
				add_Item.setEnabled(false);
				showCheckBox();
				deleteButtonPressed = true;
			}
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}

	}

	private void showCheckBox() {
		for (int i=0;i<list.getChildCount();i++) {
			View v = list.getChildAt(i);
			CheckBox check = (CheckBox) v.findViewById(R.id.checkBox_list);
			check.setVisibility(View.VISIBLE);
		}
	}

	private void add_List() {
		if (listName == "")
			listName = "Lista " + shoppingLists.size();
		shoppingLists.add(new ShoppingListItem(listName));
		helper.insertLists(listName);
		list.setAdapter(adapter);
	}

	private void delete_List() {
				
		for (int i=0;i<shoppingLists.size();i++) {
			ShoppingListItem item = shoppingLists.get(i);
			if (item.isChecked()) {
				shoppingLists.remove(i);
				i--;
				SQLiteDatabase db = helper.open();
				if (db!=null) {
					helper.removeList(item.getListName());
					helper.close();
				}
			}
		}
		list = (ListView) findViewById(R.id.listView_lists);
		adapter = new ShoppingListAdapter(this, shoppingLists);
		list.setAdapter(adapter);
	}
	

	public void hideCheckBox() {
		for (int i = 0; i < list.getChildCount(); i++) {
			View v = list.getChildAt(i);
			CheckBox check = (CheckBox) v.findViewById(R.id.checkBox_list);
			check.setVisibility(View.INVISIBLE);
		}
	}

	public void open_Dialog() {

		LayoutInflater li = LayoutInflater.from(this);
		View promptsView = li.inflate(R.layout.dialog_crear_lista_compra, null);

		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mycontext);

		// set prompts.xml to alertdialog builder
		alertDialogBuilder.setView(promptsView);

		final EditText userInput = (EditText) promptsView
				.findViewById(R.id.editTextDialogUserInput);

		// set dialog message
		alertDialogBuilder
			.setCancelable(false)
			.setPositiveButton("OK",
			  new DialogInterface.OnClickListener() {
			    public void onClick(DialogInterface dialog,int id) {
				// get user input and set it to result
				// edit text
			    	
			    	String name=userInput.getText().toString();
			    helper.insertLists(userInput.getText().toString());
			    int idLista=helper.getIdList(name);
			    Intent intent = new Intent(mycontext, MostrarProductosLista.class);
			    intent.putExtra("idList", idLista);
			    mycontext.startActivity(intent);
			    //refreshListView(userInput.getText().toString());
			    
			    }
			  })
			.setNegativeButton("Cancel",
			  new DialogInterface.OnClickListener() {
			    public void onClick(DialogInterface dialog,int id) {
				dialog.cancel();
			    }
			  });

		// create alert dialog
		AlertDialog alertDialog = alertDialogBuilder.create();

		// show it
		alertDialog.show();

			}
	/*public void refreshListView(String data){
		ArrayList<ShoppingListItem> tmp=helper.readLists(null, "option");
			//Para que se refresque la información en la pantalla
	    ShoppingListItem item = new ShoppingListItem(data);
				shoppingLists.add(item);
				list = (ListView)findViewById(R.id.listViewProducts);
				ShoppingListAdapter adapter;
				// Inicializamos el adapter.
				adapter = new ShoppingListAdapter(this,shoppingLists);
				// Asignamos el Adapter al ListView, en este punto hacemos que el
				// ListView muestre los datos que queremos.
				list.setAdapter(adapter);
			
	}*/

}
=======
package com.example.kitchapp;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
//import android.view.MenuInflater;
//import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ShoppingLists extends ActionBarActivity {

	private ArrayList<ShoppingListItem> shoppingLists;
	private ListView list;
	private ShoppingListAdapter adapter;
	public Handler_Sqlite helper;
	private ArrayList<ShoppingListItem> lists;
	private boolean deleteButtonPressed;
	private MenuItem add_Item;
	private Dialog customDialog;
	private String listName;
	private String listTableName = "listshopping";
	private String column = "name";
	private Context mycontext;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mycontext = this;
		setContentView(R.layout.activity_shopping_lists);
		shoppingLists = new ArrayList<ShoppingListItem>();
		helper = new Handler_Sqlite(this);
		initializeArrayList(1);
		list = (ListView) findViewById(R.id.listView_lists);
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				String name = shoppingLists.get(position).getListName();
				alertDialogModifyList(position, name);
			}

		});
		adapter = new ShoppingListAdapter(this, shoppingLists); 
		list.setAdapter(adapter);

	}

	public void initializeArrayList(Integer idList) {

		shoppingLists = helper.readLists();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_action_bar, menu);
		add_Item = menu.findItem(R.id.add_Product);
		MenuItem home = menu.findItem(R.id.home);
		home.setVisible(false);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		// cambiar add_Product por add(para que sea mï¿½s general)
		case R.id.add_Product:
			open_Dialog();
			return true;

		case R.id.delete_Product:
			if (deleteButtonPressed) {
				add_Item.setEnabled(true);
				delete_List();
				hideCheckBox();
				deleteButtonPressed = false;
			} else {
				add_Item.setEnabled(false);
				showCheckBox();
				deleteButtonPressed = true;
			}
			return true;
		case R.id.logout:
			SharedPreferences settings = getSharedPreferences(
					PantallaTransicion.PREFS_NAME, 0);
			SharedPreferences.Editor editor = settings.edit();

			editor.putBoolean("hasLoggedIn", false);
			editor.commit();

			Intent j = new Intent(this, Login.class);
			j.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(j);
			finish();
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}

	}

	private void showCheckBox() {
		for (int i = 0; i < list.getChildCount(); i++) {
			View v = list.getChildAt(i);
			CheckBox check = (CheckBox) v.findViewById(R.id.checkBox_list);
			check.setVisibility(View.VISIBLE);
		}
	}

	private void add_List() {
		if (listName == "")
			listName = "Lista " + shoppingLists.size();
		shoppingLists.add(new ShoppingListItem(listName));
		helper.insertLists(listName);
		list.setAdapter(adapter);
	}

	private void delete_List() {

		for (int i = 0; i < shoppingLists.size(); i++) {
			ShoppingListItem item = shoppingLists.get(i);
			if (item.isChecked()) {
				shoppingLists.remove(i);
				i--;
				SQLiteDatabase db = helper.open();
				if (db != null) {
					helper.remove(listTableName, "name", item.getListName());
					helper.close();
				}
			}
		}
		list = (ListView) findViewById(R.id.listView_lists);
		adapter = new ShoppingListAdapter(this, shoppingLists);
		list.setAdapter(adapter);
	}

	public void hideCheckBox() {
		for (int i = 0; i < list.getChildCount(); i++) {
			View v = list.getChildAt(i);
			CheckBox check = (CheckBox) v.findViewById(R.id.checkBox_list);
			check.setVisibility(View.INVISIBLE);
		}
	}

	public void open_Dialog() {

		LayoutInflater li = LayoutInflater.from(this);
		View promptsView = li.inflate(R.layout.dialog_crear_lista_compra, null);

		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				mycontext);

		// set prompts.xml to alertdialog builder
		alertDialogBuilder.setView(promptsView);

		final EditText userInput = (EditText) promptsView
				.findViewById(R.id.editTextDialogUserInput);

		// set dialog message
		alertDialogBuilder
				.setCancelable(false)
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						// get user input and set it to result
						// edit text

						int length = userInput.getText().length();
						int pos;
						if (length == 0) {
							pos = shoppingLists.size() + 1;
							listName = "Lista " + pos;
						} else
							listName = userInput.getText().toString();
						if (!helper.existList(listTableName, listName))
							insertNewList();
						else
							errorListName();

					}
				})
				.setNegativeButton("Cancel",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								dialog.cancel();
							}
						});

		// create alert dialog
		AlertDialog alertDialog = alertDialogBuilder.create();

		// show it
		alertDialog.show();

	}

	public void goList(View v) {
		RelativeLayout item = (RelativeLayout) v.getParent();
		TextView shoppingList = (TextView) item.getChildAt(1);
		listName = shoppingList.getText().toString();
		int numList = helper.getIdList(listName);
		Intent j = new Intent(this, MostrarProductosLista.class);
		j.putExtra("numList", numList);
		startActivity(j);
		super.finish();
	}

	public void alertDialogModifyList(final int position, final String wrong) {

		LayoutInflater li = LayoutInflater.from(this);
		View promptsView = li.inflate(R.layout.dialog_crear_lista_compra, null);

		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				mycontext);

		// set prompts.xml to alertdialog builder
		alertDialogBuilder.setView(promptsView);

		final EditText userInput = (EditText) promptsView
				.findViewById(R.id.editTextDialogUserInput);

		userInput.setText(wrong);

		// set dialog message
		alertDialogBuilder
				.setCancelable(false)
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						// get user input and set it to result
						// edit text

						int length = userInput.getText().length();
						int pos;
						if (length == 0) {
							pos = shoppingLists.size() + 1;
							listName = "Lista " + pos;
						} else
							listName = userInput.getText().toString();
						modifyList(position, wrong);
					}
				})
				.setNegativeButton("Cancel",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								dialog.cancel();
							}
						});

		// create alert dialog
		AlertDialog alertDialog = alertDialogBuilder.create();

		// show it
		alertDialog.show();

	}

	public void modifyList(int position, String wrong) {
		SQLiteDatabase tmp = helper.open();
		if (tmp != null)
			if (!helper.existList(listTableName, listName)) {
				shoppingLists.get(position).setListName(listName);
				helper.updateNameList(wrong, listName);
			}
		helper.close();
		list = (ListView) findViewById(R.id.listView_lists);
		adapter = new ShoppingListAdapter(this, shoppingLists);
		list.setAdapter(adapter);
	}

	public void insertNewList() {
		helper.insertLists(listName);
		int idLista = helper.getIdList(listName);
		Intent intent = new Intent(mycontext, MostrarProductosLista.class);
		intent.putExtra("idList", idLista);
		mycontext.startActivity(intent);
		super.finish();
	}

	public void errorListName() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);

		builder.setTitle("Informacion")
				.setIcon(
						getResources().getDrawable(
								android.R.drawable.ic_dialog_info))
				.setMessage("El nombre de lista " + listName + " ya existe")
				.setNeutralButton(R.string.ok,
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface arg0, int arg1) {
								open_Dialog();
							}
						});

		builder.create();
		builder.show();
	}

}

>>>>>>> eb9e561c31a6809bd723ad4dc8cbc927e80754b1
