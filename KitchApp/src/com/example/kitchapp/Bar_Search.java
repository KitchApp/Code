package com.example.kitchapp;

import java.util.ArrayList;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.SearchView.OnQueryTextListener;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Bar_Search extends ActionBarActivity implements OnClickListener {
	private Handler_Sqlite helper;
	private TextView txt_product;
	private TextView txt_quantity;
	private EditText txt_qty;
	private EditText txt_prod;
	private String table = "products";
	private SearchView searchview;
	
	/**Search by voice and barcode**/
	private static final int REQUEST_CODE = 1234;
	private Dialog match_text_dialog;
	private ListView textlist;
	private ArrayList<String> matches_text;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item_ingredients_listviewrec);
		helper = new Handler_Sqlite(this);

		txt_product = (TextView) findViewById(R.id.ingredients);
		txt_product.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				modifyProduct(arg0);

			}

		});
		txt_quantity = (TextView) findViewById(R.id.quantity);
		txt_quantity.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				modifyProduct(arg0);
			}

		});
		Bundle extras= this.getIntent().getExtras();
		if (extras != null) {
			CharSequence product = extras.getCharSequence("prod");
			CharSequence quantity = extras.getCharSequence("qty");
			txt_product.setText(product);
			txt_quantity.setText(quantity);
		}
	}
	
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_search, menu);
		MenuItem searchItem = menu.findItem(R.id.manual);
		searchview = (SearchView)MenuItemCompat.getActionView(searchItem);
		searchview.setSubmitButtonEnabled(true);
		searchview.setOnQueryTextListener(new OnQueryTextListener() {

			@Override
			public boolean onQueryTextChange(String arg0) {
				return false;
			}

			@Override
			public boolean onQueryTextSubmit(String arg0) {
				String prod = searchview.getQuery().toString();
				if (prod.length() != 0)
					showProduct(prod, "name");
				return false;
			}});
		return super.onCreateOptionsMenu(menu);
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.manual:

			return true;
		case R.id.voice:
			searchByVoice();
			return true;
		case R.id.barcode:	
			searchByBarcode();
			return true;
		case R.id.home:
			Intent intent = new Intent(this, PantallaPrincipal.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			return true;
		case R.id.logout:	
			SharedPreferences settings = getSharedPreferences(PantallaTransicion.PREFS_NAME, 0);
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

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.button_save:

			SQLiteDatabase db = helper.open();
			if (db != null) {
				helper.updateProduct(txt_product.getText().toString(),txt_prod.getText().toString(),
						Integer.parseInt(txt_qty.getText().toString()),"updatePantry",1);
//				helper.close();
			}
			helper.close();
			txt_product.setText(txt_prod.getText().toString());
			txt_quantity.setText(txt_qty.getText());
			Intent j = new Intent(this, Bar_Search.class);
			j.putExtra("prod",txt_product.getText());
			j.putExtra("qty",txt_quantity.getText());
			startActivity(j);
			finish();
			break;

		case R.id.button_cancel:
			Intent i = new Intent(this, Bar_Search.class);
			i.putExtra("prod",txt_product.getText());
			i.putExtra("qty",txt_quantity.getText());
			startActivity(i);
			finish();
			break;

		case R.id.button_decrement:
			decreAmount(v);
			break;

		case R.id.button_increment:
			increAmount(v);
			break;

		default:
			break;
		}
	}
	
	private void showProduct(String prod, String col) {
		txt_product.setText("");
		txt_quantity.setText("");
		SQLiteDatabase db = helper.open();
		if (db != null) {
			ItemProducto p = helper.existProduct(prod, table, col);
			if (p != null) {
				txt_product.setVisibility(View.VISIBLE);
				txt_product.setText(p.getNombre());
				txt_quantity.setVisibility(View.VISIBLE);
				txt_quantity.setText(p.getCantidad() + "");
			}
			else { 
				alertDialogReport("Producto no existente");
				if (col != "barCode") {}
//					txt_search.setText(prod);
			}
		}
		helper.close();
	}

	private void searchByBarcode() {
		IntentIntegrator scanIntegrator = new IntentIntegrator(this);
		scanIntegrator.initiateScan();
	}

	private void searchByVoice() {
		if (isConnected()) {
			Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
			intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
					RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
			startActivityForResult(intent, REQUEST_CODE);
		} else {
			Toast.makeText(getApplicationContext(),
					"Please Connect to Internet", Toast.LENGTH_LONG).show();
		}
	}
	
	public boolean isConnected() {
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo net = cm.getActiveNetworkInfo();
		if (net != null && net.isAvailable() && net.isConnected()) {
			return true;
		} else {
			return false;
		}
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {

			match_text_dialog = new Dialog(Bar_Search.this);
			match_text_dialog.setContentView(R.layout.dialog_matches);
			match_text_dialog.setTitle("Select Matching Text");
			textlist = (ListView) match_text_dialog
					.findViewById(R.id.listDialogVoice);
			matches_text = data
					.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
					android.R.layout.simple_list_item_1, matches_text);
			textlist.setAdapter(adapter);
			textlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {

					match_text_dialog.hide();
					getProduct(position);
				}
			});

			match_text_dialog.show();

		}
		super.onActivityResult(requestCode, resultCode, data);

		/**Bar code**/

		IntentResult scanningResult = IntentIntegrator.parseActivityResult(
				requestCode, resultCode, data);

		if (scanningResult != null) {
			// we have a result
			String result = scanningResult.getContents();
			showProduct(result,"barCode");
		}
		
	}
	
	public void alertDialogReport(String msj) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);

		// builder.setTitle("Error")
		builder.setIcon(getResources().getDrawable(R.drawable.close))
				.setMessage(msj)
				.setNeutralButton(R.string.ok,
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface arg0, int arg1) {
								// arg0.cancel();
//								alertDialogListView(false, 3);
							}
						});

		builder.create();
		builder.show();
	}
	
	private void getProduct(int position) {
		String[] prod = matches_text.get(position).split("");
		String name = "";
		for (int i = 0; i < prod.length; i++) {
			name += prod[i];
			name += "";
		}
		showProduct(name,"name");
	} 

	private void increAmount(View v) {
		txt_qty.setText(Integer.parseInt(txt_qty.getText().toString()) + 1 + "");

	}

	private void decreAmount(View v) {
		if (Integer.parseInt(txt_qty.getText().toString()) > 0) {
			txt_qty.setText(Integer.parseInt(txt_qty.getText().toString()) - 1
					+ "");

		}

	}


	public void modifyProduct(View view) {

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		LayoutInflater inflater = this.getLayoutInflater();

		view = inflater.inflate(R.layout.activity_modificar_producto_despensa,
				null);
		
		txt_prod = (EditText) view.findViewById(R.id.nameProductModify);
		txt_prod.setText(txt_product.getText());
		txt_qty = (EditText) view.findViewById(R.id.cantProduct);
		txt_qty.setText(txt_quantity.getText());
		
		Button save = (Button) view.findViewById(R.id.button_save);
		save.setOnClickListener(this);
		Button cancel = (Button) view.findViewById(R.id.button_cancel);
		cancel.setOnClickListener(this);
		Button decrement = (Button) view.findViewById(R.id.button_decrement);
		decrement.setOnClickListener(this);
		Button increment = (Button) view.findViewById(R.id.button_increment);
		increment.setOnClickListener(this);
		
		builder.setView(view);
		builder.create();
		builder.show();

	}

}
