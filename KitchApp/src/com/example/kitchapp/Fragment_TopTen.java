package com.example.kitchapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Fragment_TopTen extends Fragment {
View rootView;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		rootView = inflater.inflate(R.layout.activity_fragment_topten, container, false);
		
		TextView texto = (TextView) rootView.findViewById(R.id.texto_topten);
		
		texto.setText("Tab seleccionada" + "\n\n" + "Top Ten");;
		
		return rootView;
	}
	
}
