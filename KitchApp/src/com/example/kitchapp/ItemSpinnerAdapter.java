package com.example.kitchapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ItemSpinnerAdapter extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setting default screen to login.xml
		setContentView(R.layout.activity_add_manualmente);
		
		Spinner sp=(Spinner)findViewById(R.id.Spinner01);
		ArrayAdapter adapter= ArrayAdapter.createFromResource(this, R.array.Categorias, android.R.layout.simple_spinner_dropdown_item);
		sp.setAdapter(adapter);
		
		sp.setOnItemSelectedListener(new OnItemSelectedListener(){
			
			@Override
			public void onItemSelected(AdapterView<?> arg0, View parentView ,int position, long arg3) {
				// TODO Auto-generated method stub
				Toast.makeText(parentView.getContext(), "texto "+ parentView.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show();
			}
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}

		
		}
		
	}           
}