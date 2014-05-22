package com.example.kitchapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;

import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import android.widget.Toast;
 
public class Registro extends Activity implements OnClickListener {
	
	private EditText userName;
	private EditText password;
	private EditText email;
	private String encryptedPassword="";

	Handler_Sqlite helper = new Handler_Sqlite(this);

	
	public String session_name;
	    public String session_id;
    	//private boolean userValidation=false;
    	//private boolean emailValidation=false;
    	//public Boolean registrado=false;
	
	public String session_name;
    	public String session_id;
   
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set View to register.xml
        setContentView(R.layout.registro);
 
        Button buttonRegister = (Button) findViewById(R.id.btnRegistro);
        userName = (EditText) findViewById(R.id.reg_usuario);
        password = (EditText) findViewById(R.id.reg_password);
        email = (EditText) findViewById(R.id.reg_email);
 

        // Listening to Login Screen link
        //loginScreen.setOnClickListener(this);
        buttonRegister.setOnClickListener(this);
        userName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				if(hasFocus==false){
					//userValidation=true;
					new HttpGetName().execute();
					
				}
			}
		});
        
        password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {

				// TODO Auto-generated method stub
				
				if(hasFocus==true){
					//emailValidation=true;
					new HttpGetEmail().execute();
					
				}
				
			}
		});
 
    }

      

    
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.link_to_login:
				finish();
				break;
			case R.id.btnRegistro:
				String mail = email.getText().toString();
				String [] mailarroba = mail.split("@");
				if ((mailarroba.length < 2) || (mail.equals(mailarroba[0])) || (mailarroba.length > 2)) {
					errorMail();
				}
				else {
					String [] mailpoint = mailarroba[1].split("\\.");
					if (mailpoint.length < 2 || mailarroba[1].equals(mailpoint[0])) {
						errorMail();
					}
					else {
						showMessageEmailValidation();

						/*new HttpAsyncTask().execute();
						
						Intent intent = new Intent(Registro.this,PantallaTransicion.class);
				        	startActivity(intent);
						finish();*/
			            
					}
				}
				break;
		}
		
	}

	public void showMessageInvalidRegister() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		 
	    builder.setTitle("Informacion")
	            .setIcon(getResources().getDrawable(android.R.drawable.ic_dialog_info))
	            .setMessage("Usuario o email ya registrados.")
	            .setNeutralButton(R.string.ok, new DialogInterface.OnClickListener() {
	 
	                @Override
	                public void onClick(DialogInterface arg0, int arg1) {
	                	arg0.cancel();
	                	
	                }
	            });
	 
	    builder.create();
	    builder.show();
	}
	
	
	public void showMessageEmailValidation() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		 
	    builder.setTitle("Informacion")
	            .setIcon(getResources().getDrawable(android.R.drawable.ic_dialog_info))
	            .setMessage("Recuerde que si desea utilizar su cuenta en la p�gina web tiene que validarla pinchando en el enlace que se le ha enviado al correo electr�nico.")
	            .setNeutralButton(R.string.ok, new DialogInterface.OnClickListener() {
	 
	                @Override
	                public void onClick(DialogInterface arg0, int arg1) {
	                	arg0.cancel();
	                	new HttpAsyncTask().execute();
	                	Intent intent = new Intent(Registro.this,PantallaTransicion.class);
				        startActivity(intent);
						finish();
	                	
	                }
	            });
	 
	    builder.create();
	    builder.show();
	}
	
	
	public void errorMail() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		 
	    builder.setTitle("Error")
	            .setIcon(getResources().getDrawable(R.drawable.close))
	            .setMessage("La direccion de correo tiene que ser tipo example@dominio.com/es")
	            .setNeutralButton(R.string.ok, new DialogInterface.OnClickListener() {
	            	@Override
	            	public void onClick(DialogInterface arg0, int arg1) {
	                	arg0.cancel();
	            	}
	            });
	    builder.create();
	    builder.show();
	}
	
	public void errorRegister() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		 
	    builder.setTitle("Error")
	    	   .setIcon(getResources().getDrawable(R.drawable.close))
	           .setMessage("Ya existe ese nombre de usuario. Por favor registrese de nuevo")
	           .setNeutralButton(R.string.ok, new DialogInterface.OnClickListener() {
	 
	                @Override
	                public void onClick(DialogInterface arg0, int arg1) {
	                	arg0.cancel();
	                }
	            });
	 
	    builder.create();
	    builder.show();
	}
	
	public class HttpGetName extends AsyncTask<String, Integer, String> {
     
		
		AlertDialog.Builder builder;
		protected void onPreExecute() {
		    super.onPreExecute();
    		builder = new AlertDialog.Builder(Registro.this);
		} 
	    
	    @Override
		protected String doInBackground(String... mURL) {
			
			String response="";

	          //mURL[0]=mURL[0].replace(" ", "%20");
	          //Log.i("LocAndroid Response HTTP Threas","Ejecutando get 0: "+mURL);
	          HttpClient httpclient = new DefaultHttpClient();
	          HttpGet httppost= new HttpGet();;
 	          Log.i("LocAndroid Response HTTP Thread","Ejecutando get 1");
	    	  //HttpGet httppost = new HttpGet(mURL[0]);
	         
	        	 httppost = new HttpGet("http://kitchapp.es/consultarUsuarioPorNombre.php?name="+userName.getText().toString());
	         
	         
	         Log.i("LocAndroid Response HTTP Thread","Ejecutando get 2");
	         try {
	   
	
	         Log.i("LocAndroid Response HTTP","Ejecutando get");
	         // Execute HTTP Post Request
	         ResponseHandler<String> responseHandler=new BasicResponseHandler();
	        	response = httpclient.execute(httppost,responseHandler);
	         Log.i("LocAndroid Response HTTP",response);
	    	} catch (ClientProtocolException e) {
	        Log.i("LocAndroid Response HTTP ERROR 1",e.getMessage());
	        // TODO Auto-generated catch block
	    } catch (IOException e) {
	        
	        Log.i("LocAndroid Response HTTP ERROR 2",e.getMessage());
	        // TODO Auto-generated catch block
	    }
			// TODO Auto-generated method stub
			return response;
		}
    //return response;
	
		protected void onPostExecute(String result) {
			JSONArray ja=null;
			try {
						
			
			if(result.length()>1)
				ja=new JSONArray(result);
			
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
			Toast.makeText(getApplicationContext(), "Error recuperando la informacion del servidor, verifique su conexion a internet y vuelva a intentarlo.", 1000).show();
			
		}
		try{
			
			if(userName.getText().toString().equals(ja.getString(0))){
				//registrado=true;
				builder.setTitle("Informacion")
	            .setIcon(getResources().getDrawable(android.R.drawable.ic_dialog_info))
	            .setMessage("Usuario no disponible")
	            .setNeutralButton(R.string.ok, new DialogInterface.OnClickListener() {
	 
	                @Override
	                public void onClick(DialogInterface arg0, int arg1) {
	                	arg0.cancel();
	                	userName.requestFocus();
	                }
	            });
	 
				builder.create();
				builder.show();
				
			}
		}
			catch (Exception e) {
				
			} 
		
	
	}
    
    }
	
	
public class HttpGetEmail extends AsyncTask<String, Integer, String> {
     
		
		AlertDialog.Builder builder;
		protected void onPreExecute() {
		    super.onPreExecute();
    		builder = new AlertDialog.Builder(Registro.this);
		} 
	    
    @Override
	protected String doInBackground(String... mURL) {
		
		String response="";
	        //mURL[0]=mURL[0].replace(" ", "%20");
	         //Log.i("LocAndroid Response HTTP Threas","Ejecutando get 0: "+mURL);
	          HttpClient httpclient = new DefaultHttpClient();
	          HttpGet httppost= new HttpGet();;
	         Log.i("LocAndroid Response HTTP Thread","Ejecutando get 1");
	    	//HttpGet httppost = new HttpGet(mURL[0]);
         	 httppost = new HttpGet("http://kitchapp.es/consultarUsuarioPorEmail.php?mail="+email.getText().toString());
         
         Log.i("LocAndroid Response HTTP Thread","Ejecutando get 2");
         try {
   

         Log.i("LocAndroid Response HTTP","Ejecutando get");
        // Execute HTTP Post Request
      ResponseHandler<String> responseHandler=new BasicResponseHandler();
        	response = httpclient.execute(httppost,responseHandler);
         Log.i("LocAndroid Response HTTP",response);
    	} catch (ClientProtocolException e) {
        Log.i("LocAndroid Response HTTP ERROR 1",e.getMessage());
        // TODO Auto-generated catch block
    } catch (IOException e) {
        
        Log.i("LocAndroid Response HTTP ERROR 2",e.getMessage());
        // TODO Auto-generated catch block
    }
		// TODO Auto-generated method stub
		return response;
	}
    //return response;
	
	
	protected void onPostExecute(String result) {
		JSONArray ja=null;
		try {
					
		
		if(result.length()>1)
			ja=new JSONArray(result);
		
		
	} catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		Toast.makeText(getApplicationContext(), "Error recuperando la informacion del servidor, verifique su conexion a internet y vuelva a intentarlo.", 1000).show();
		
	}
	try{
		String tmp=email.getText().toString();
		boolean x=email.getText().toString().equals(ja.getString(2));
		boolean y=!(email.getText().toString().equals(""));

		if(email.getText().toString().equals(ja.getString(2))&& !(email.getText().toString().equals(""))){
			//registrado=true;
			builder.setTitle("Informacion")
            .setIcon(getResources().getDrawable(android.R.drawable.ic_dialog_info))
            .setMessage("Email ya registrado")
            .setNeutralButton(R.string.ok, new DialogInterface.OnClickListener() {
 
                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                	arg0.cancel();
                	email.requestFocus();
                }
            });
 
			builder.create();
			builder.show();
			
		}
	}
		catch (Exception e) {
			
		} 
		
	
	}
    
    }
	
	
	
	private class HttpAsyncTask extends AsyncTask<String, Integer, Integer> {
           @Override
           protected Integer doInBackground(String... urls) {
        	HttpClient httpclient = new DefaultHttpClient();
        	//set the remote endpoint URL
                HttpPost httppost = new HttpPost("http://www.kitchapp.es/json/user/register");
                try {
	                //get the UI elements for username and password
	                EditText username= (EditText) findViewById(R.id.reg_usuario);
	                EditText password= (EditText) findViewById(R.id.reg_password);
	                EditText email= (EditText) findViewById(R.id.reg_email);
	
	                JSONObject json = new JSONObject();
	                //extract the username and password from UI elements and create a JSON object
	                String name=username.getText().toString().trim();
	                String pass=password.getText().toString().trim();
	                String mail=email.getText().toString().trim();
	                json.put("name", username.getText().toString().trim());
	                json.put("pass", password.getText().toString().trim());
	                json.put("mail", email.getText().toString().trim());
	                //add serialised JSON object into POST request
	                StringEntity se = new StringEntity(json.toString());
	                //set request content type
	                se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
	                httppost.setEntity(se);
	                //send the POST request
	                HttpResponse response = httpclient.execute(httppost);
	                //read the response from Services endpoint
	                String jsonResponse = EntityUtils.toString(response.getEntity());
	
	                JSONObject jsonObject = new JSONObject(jsonResponse);
	                //String tmp=jsonObject.getString("user");
	                	//showMessageInvalidRegister();
	                /*{"uid":"253","uri":"http://www.kitchapp.es/?q=json/user/253"}*/
	                /*{"form_errors":{"name":"El nombre <em class=\"placeholder\">edu</em> ya se encuentra en uso.","mail":"La direcci\u00f3n de correo <em class=\"placeholder\">edu1@hotmail.com</em> ya est\u00e1 registrada. <a href=\"/?q=user/password\">\u00bfHa olvidado su contrase\u00f1a?</a>"}}*/
	                /*{"form_errors":{"name":"El nombre <em class=\"placeholder\">mayra</em> ya se encuentra en uso.","mail":"La direcci\u00f3n de correo <em class=\"placeholder\">majuma22@gmail.com</em> ya est\u00e1 registrada. <a href=\"/?q=user/password\">\u00bfHa olvidado su contrase\u00f1a?</a>"}}*/
	                //read the session information
	                
	
	                session_name=jsonObject.getString("session_name");
	                session_id=jsonObject.getString("sessid");
	                return 0;

                }catch (Exception e) {
                	Log.v("Error adding article", e.getMessage());
            	
            	}

            	return 0;
           }  	
        	/*encryptedPassword=httpGetData(urls[0]);
        	return httpGetData(urls[0]);*/
        }
        
       // onPostExecute displays the results of the AsyncTask.
        //@Override
        protected void onPostExecute(Integer result) {
        	/*Intent intent = new Intent(Registro.this,PantallaTransicion.class);
        	startActivity(intent);
			finish();*/
            
        }

	
	
	/*public static String httpGetData(String mURL) {
        //String response="";
		 InputStream inputStream = null;
	     String result = "";
	     try {
	 
	            // create HttpClient
	            HttpClient httpclient = new DefaultHttpClient();
	 
	            // make GET request to the given URL
	            HttpResponse httpResponse = httpclient.execute(new HttpGet(mURL));
	 
	            // receive response as inputStream
	            inputStream = httpResponse.getEntity().getContent();
	 
	            // convert inputstream to string
	            if(inputStream != null)
	                result = convertInputStreamToString(inputStream);
	            else
	                result = "Did not work!";
	 
	     } 
	     catch (Exception e) {
	            Log.d("InputStream", e.getLocalizedMessage());
	     }
	 
	     return result;
    
    }  
	
	 
	 private static String convertInputStreamToString(InputStream inputStream) throws IOException{
	        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
	        String line = "";
	        String result = "";
	        while((line = bufferedReader.readLine()) != null)
	            result += line;
	 
	        inputStream.close();
	        return result;
	 
	    
	}*/
}
