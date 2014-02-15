package com.example.kitchapp;

<<<<<<< HEAD

=======
>>>>>>> refs/heads/Rama-Vivi-Android
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

<<<<<<< HEAD
public class AccesoDespensa extends Activity implements OnClickListener {
=======
public class AccesoDespensa extends Activity implements OnClickListener{
>>>>>>> refs/heads/Rama-Vivi-Android

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_acceso_despensa);
<<<<<<< HEAD
		Button b1 = (Button) findViewById(R.id.button1);
		b1.setOnClickListener(this);
=======
>>>>>>> refs/heads/Rama-Vivi-Android
		Toast.makeText(this, "Actividad despensa", Toast.LENGTH_SHORT).show();
		Button b=(Button)findViewById(R.id.button_add);
		b.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.acceso_despensa, menu);
		return true;
	}
<<<<<<< HEAD
	
	/*public void mostrarProductos(View view) {
		Intent intent = new Intent(this,MostrarProductosCategoria.class);
		startActivity(intent);
	}*/
	
=======

>>>>>>> refs/heads/Rama-Vivi-Android
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()){
		case R.id.button_add:
			Intent i = new Intent(this,AddDespensa.class);
			startActivity(i);
			break;
<<<<<<< HEAD
			
		case R.id.button1:
			Intent intent = new Intent(this,MostrarProductosCategoria.class);
			startActivity(intent);
			break;
		}
	}
	
}
	
	
=======
		}
	}

}
>>>>>>> refs/heads/Rama-Vivi-Android
