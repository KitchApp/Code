package com.example.kitchapp;

import android.app.Activity;
import android.content.Intent;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

<<<<<<< HEAD

=======
>>>>>>> eb9e561c31a6809bd723ad4dc8cbc927e80754b1
public class BarcodeScanner {
	
	private String barcode;
	
	public BarcodeScanner(Activity activity) {
		barcode = "";
		IntentIntegrator scanIntegrator = new IntentIntegrator(activity);
		scanIntegrator.initiateScan();
	}
	
<<<<<<< HEAD
	
=======
>>>>>>> eb9e561c31a6809bd723ad4dc8cbc927e80754b1
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
		if (scanningResult != null )
			barcode = scanningResult.getContents();
	}
	
	public String getBarcode() {
		return barcode;
	}
<<<<<<< HEAD
		  
	
//	public void addBarCode(){
//		IntentIntegrator scanIntegrator = new IntentIntegrator(activity);
//		scanIntegrator.initiateScan();
//	}
=======
	
>>>>>>> eb9e561c31a6809bd723ad4dc8cbc927e80754b1
}
