package com.example.kitchapp;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.view.Window;

public class PantallaTransicion extends Activity {

	private AccesoBBDD tarea;
	private ProgressBar barra;
	public static final String PREFS_NAME = "MyPrefsFile";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_pantalla_transicion);
		barra = (ProgressBar) findViewById(R.id.progressBar1);
		tarea = new AccesoBBDD();
		tarea.execute();
	}

	private void tareaLarga() {
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
		}
	}

	public class AccesoBBDD extends AsyncTask<Void, Integer, Boolean> {

		@Override
		protected Boolean doInBackground(Void... params) {
			for (int i = 1; i <= 10; i++) {
				tareaLarga();
				publishProgress(i * 10);
			}
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
	}

}
