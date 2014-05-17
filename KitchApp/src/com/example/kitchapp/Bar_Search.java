package com.example.kitchapp;

import java.util.ArrayList;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
<<<<<<< HEAD
=======
import android.widget.ListAdapter;
>>>>>>> 9d155f8c3ec06f067b28ee846e3deb48b5317b3a
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Bar_Search extends Activity implements OnClickListener {
	private Handler_Sqlite helper;
	private EditText txt_search;
	private TextView txt_product;
	private TextView txt_quantity;
	private TextView txt_qty;
	private String table = "products";
	
	/**Search by voice and barcode**/
	private static final int REQUEST_CODE = 1234;
	private Dialog match_text_dialog;
	private ListView textlist;
	private ArrayList<String> matches_text;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bar_search);
		helper = new Handler_Sqlite(this);

		txt_search = (EditText) findViewById(R.id.txt_barSearch);
		txt_product = (TextView) findViewById(R.id.txt_product);
<<<<<<< HEAD
		txt_product.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				modifyProduct(arg0);

			}

		});
		txt_quantity = (TextView) findViewById(R.id.txt_quantity);
		txt_quantity.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				modifyProduct(arg0);

			}

		});
=======
		txt_quantity = (TextView) findViewById(R.id.txt_quantity);
>>>>>>> 9d155f8c3ec06f067b28ee846e3deb48b5317b3a
		Button button_ok = (Button) findViewById(R.id.button_ok);
		button_ok.setOnClickListener(this);
		Button button_Voice = (Button) findViewById(R.id.button_Voice);
		button_Voice.setOnClickListener(this);
		Button button_codeSearch = (Button) findViewById(R.id.button_codeSearch);
		button_codeSearch.setOnClickListener(this);
		Bundle extras= this.getIntent().getExtras();
		if (extras != null) {
			CharSequence product = extras.getCharSequence("prod");
			CharSequence quantity = extras.getCharSequence("qty");
			txt_product.setText(product);
			txt_quantity.setText(quantity);
		}
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {

		case R.id.button_ok:
			String prod = txt_search.getText().toString();
<<<<<<< HEAD
			showProduct(prod, "name");
=======
			if (prod.length() != 0)
				showProduct(prod, "name");
>>>>>>> 9d155f8c3ec06f067b28ee846e3deb48b5317b3a
			break;

		case R.id.button_Voice:
			searchByVoice();
			break;

		case R.id.button_codeSearch:
			searchByBarcode();
			break;

		case R.id.button_save:

			SQLiteDatabase db = helper.open();
			if (db != null) {
				helper.updateProduct(txt_product.getText().toString(),
						Integer.parseInt(txt_qty.getText().toString()));
//				helper.close();
			}
			helper.close();
			txt_quantity.setText(txt_qty.getText());
			Intent j = new Intent(this, Bar_Search.class);
			j.putExtra("prod",txt_product.getText());
			j.putExtra("qty",txt_quantity.getText());
			startActivity(j);
			break;

		case R.id.button_cancel:
			Intent i = new Intent(this, Bar_Search.class);
			i.putExtra("prod",txt_product.getText());
			i.putExtra("qty",txt_quantity.getText());
			startActivity(i);
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
				if (col != "barCode")
					txt_search.setText(prod);
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

<<<<<<< HEAD

	public void modifyProduct(View view) {

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		LayoutInflater inflater = this.getLayoutInflater();

		view = inflater.inflate(R.layout.activity_modificar_producto_despensa,
				null);
		txt_qty = (TextView) view.findViewById(R.id.cantProduct);
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

=======
>>>>>>> 9d155f8c3ec06f067b28ee846e3deb48b5317b3a
}
