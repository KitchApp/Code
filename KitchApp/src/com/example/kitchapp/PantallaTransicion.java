package com.example.kitchapp;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
<<<<<<< HEAD
import android.view.Menu;
import android.view.View;
=======
import android.view.Window;
>>>>>>> Rama-Vivi-Android
import android.widget.ProgressBar;

public class PantallaTransicion extends Activity {

	private AccesoBBDD tarea;
	private ProgressBar barra;
	public static final String PREFS_NAME = "MyPrefsFile";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_pantalla_transicion);
<<<<<<< HEAD
		
		/*pDialog = new ProgressDialog(PantallaTransicion.this);
        	pDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        	pDialog.setMessage("Procesando...");
        	//pDialog.setCancelable(true);
        	pDialog.setMax(100);*/

		barra=(ProgressBar)findViewById(R.id.progressBar1);
	        tarea = new AccesoBBDD();
        	tarea.execute();
=======
		barra = (ProgressBar) findViewById(R.id.progressBar1);
		tarea = new AccesoBBDD();
		tarea.execute();
>>>>>>> Rama-Vivi-Android
	}

	private void tareaLarga() {
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
		}
	}
<<<<<<< HEAD
	
		
	
	private void tareaLarga() {
            try { 
                    Thread.sleep(300); 
            } catch(InterruptedException e) {}
    	}
	
	public class AccesoBBDD extends AsyncTask<Void,Integer,Boolean>{
=======
>>>>>>> Rama-Vivi-Android

	public class AccesoBBDD extends AsyncTask<Void, Integer, Boolean> {

		@Override
		protected Boolean doInBackground(Void... params) {
			for (int i = 1; i <= 10; i++) {
				tareaLarga();
				publishProgress(i * 10);
			}
<<<<<<< HEAD
			
			
			@Override
            protected void onProgressUpdate(Integer... values) {
                    int progreso = values[0].intValue();
                    barra.setProgress(progreso);
            }
            
            protected void onPreExecute() {
                    
                    /*pDialog.setOnCancelListener(new OnCancelListener() {
                                @Override
                                public void onCancel(DialogInterface dialog) {
                                	AccesoBBDD.this.cancel(true);
                                }
                        });*/
                    
                    //pDialog.setProgress(0);
                    //pDialog.show();
            	    barra.setMax(100);
            	    barra.setProgress(0);
            	
            }
            
            @Override
            protected void onPostExecute(Boolean result) {
                    if(result) {

                            //pDialog.dismiss();
                            //Toast.makeText(PantallaTransicion.this, "Tarea finalizada!", Toast.LENGTH_SHORT).show();
                            //setContentView(R.layout.activity_pantalla_principal);
                    	
                    	    Intent i = new Intent(PantallaTransicion.this, PantallaPrincipal.class);
                	    startActivity(i);
                    }
            }
            
            /*@Override
            protected void onCancelled() {
                    Toast.makeText(PantallaTransicion.this, "Tarea cancelada!", Toast.LENGTH_SHORT).show();
            }*/
=======
			return true;
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			int progreso = values[0].intValue();
			barra.setProgress(progreso);
		}

		protected void onPreExecute() {
			barra.setMax(100);
			barra.setProgress(0);

		}

		@Override
		protected void onPostExecute(Boolean result) {
			if (result) {
				Intent i = new Intent(PantallaTransicion.this,
						PantallaPrincipal.class);
				startActivity(i);
				finish();
			}
		}
>>>>>>> Rama-Vivi-Android
	}

}
