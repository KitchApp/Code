package com.example.kitchapp;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ProgressBar;

public class PantallaTransicion extends Activity {
	
	private AccesoBBDD tarea;
	private ProgressBar barra;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pantalla_transicion);
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
				// TODO Auto-generated method stub
				for(int i=1; i<=10; i++) {
					tareaLarga();
					publishProgress(i*10);
					/*if(isCancelled())
						break;*/
				}
				return true;
			}
			
			
			@Override
            protected void onProgressUpdate(Integer... values) {
                    int progreso = values[0].intValue();
                    barra.setProgress(progreso);
                    //pDialog.setProgress(progreso);
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
                    if(result)
                    {
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
	}

}






