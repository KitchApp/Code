package com.example.kitchapp;

import android.app.Activity;
import android.content.Intent;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class BarcodeScanner {
	
	private String barcode;
	
	public BarcodeScanner(Activity activity) {
		barcode = "";
		IntentIntegrator scanIntegrator = new IntentIntegrator(activity);
		scanIntegrator.initiateScan();
	}
	
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
		if (scanningResult != null )
			barcode = scanningResult.getContents();
	}
	
	public String getBarcode() {
		return barcode;
	}
	
}
