package com.example.kitchapp;

import android.app.Activity;
<<<<<<< HEAD
=======
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
>>>>>>> Rama-Vivi-Android
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
<<<<<<< HEAD
import android.widget.ProgressBar;

public class PantallaTransicion extends Activity {
	
	private AccesoBBDD tarea;
	private ProgressBar barra;
	
=======
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

public class PantallaTransicion extends Activity {
	
	private ProgressDialog pDialog;	
	private AccesoBBDD tarea;
	private ProgressBar barra;
>>>>>>> Rama-Vivi-Android
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pantalla_transicion);
<<<<<<< HEAD
=======
		
		/*pDialog = new ProgressDialog(PantallaTransicion.this);
        pDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pDialog.setMessage("Procesando...");
        //pDialog.setCancelable(true);
        pDialog.setMax(100);*/
>>>>>>> Rama-Vivi-Android
		barra=(ProgressBar)findViewById(R.id.progressBar1);
        tarea = new AccesoBBDD();
        tarea.execute();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
		
	
	private void tareaLarga()
    {
            try { 
                    Thread.sleep(300); 
            } catch(InterruptedException e) {}
    }
	
	public class AccesoBBDD extends AsyncTask<Void,Integer,Boolean>{

		@Override
			protected Boolean doInBackground(Void... params) {
<<<<<<< HEAD
				for(int i=1; i<=10; i++) {
					tareaLarga();
					publishProgress(i*10);
=======
				// TODO Auto-generated method stub
				for(int i=1; i<=10; i++) {
					tareaLarga();
					publishProgress(i*10);
					/*if(isCancelled())
						break;*/
>>>>>>> Rama-Vivi-Android
				}
				return true;
			}
			
			
			@Override
            protected void onProgressUpdate(Integer... values) {
                    int progreso = values[0].intValue();
                    barra.setProgress(progreso);
<<<<<<< HEAD
=======
                    //pDialog.setProgress(progreso);
>>>>>>> Rama-Vivi-Android
            }
            
            
            protected void onPreExecute() {
<<<<<<< HEAD
            	barra.setMax(100);
            	barra.setProgress(0);
=======
                    
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
            	
>>>>>>> Rama-Vivi-Android
            }
            
            @Override
            protected void onPostExecute(Boolean result) {
                    if(result)
                    {
<<<<<<< HEAD
                           Intent i = new Intent(PantallaTransicion.this, PantallaPrincipal.class);
=======
                            //pDialog.dismiss();
                            //Toast.makeText(PantallaTransicion.this, "Tarea finalizada!", Toast.LENGTH_SHORT).show();
                            //setContentView(R.layout.activity_pantalla_principal);
                    	
                    	Intent i = new Intent(PantallaTransicion.this, PantallaPrincipal.class);
>>>>>>> Rama-Vivi-Android
                		startActivity(i);
                    	
                    }
            }
            
<<<<<<< HEAD
=======
            /*@Override
            protected void onCancelled() {
                    Toast.makeText(PantallaTransicion.this, "Tarea cancelada!", Toast.LENGTH_SHORT).show();
            }*/
>>>>>>> Rama-Vivi-Android
	}

}






