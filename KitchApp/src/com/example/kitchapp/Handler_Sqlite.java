package com.example.kitchapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import static android.provider.BaseColumns._ID;


public class Handler_Sqlite extends SQLiteOpenHelper{


	private static final String nameBD = "KitchApp-BaseDD";

	Context myContext;
	public Handler_Sqlite(Context ctx){
		super(ctx,nameBD, null,1);
		myContext = ctx;
	}
	
	public SQLiteDatabase open(){
		//Create and/or open a database that will be used for reading and writing.
		return this.getWritableDatabase();
	}
	
	@Override
	//This method is called when the database is created for the first time.
	public void onCreate(SQLiteDatabase db){
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======

>>>>>>> Rama-Edu-Android
=======

>>>>>>> 32477ed215e82d01b6f7e658596c4fb24f018cf5
=======
>>>>>>> Rama-Edu-Android
=======

>>>>>>> bc19c81f8e762b2026442b06370439486d3b88f6
		String query1 = "CREATE TABLE products ("+_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, cant INTEGER, idCat INTEGER, barCode TEXT);";
		String query2 = "CREATE TABLE productsTemporary("+_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, barCode TEXT);";
		
		//This method is limited to directly execute the SQL code that we pass as a parameter
		String query3 = "CREATE TABLE users ("+_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, password TEXT, email TEXT);";
		db.execSQL(query1);	
		db.execSQL(query2);
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
		//db.execSQL(query2);
=======
=======
		db.execSQL(query3);
>>>>>>> Rama-Edu-Android
=======
=======
		//db.execSQL(query2);
>>>>>>> Rama-Edu-Android
=======
>>>>>>> bc19c81f8e762b2026442b06370439486d3b88f6
		db.execSQL(query3);
		
		 InputStream is = null;
		    try {
		         is = myContext.getAssets().open("initialializeProductsTemporary.sql");
		         if (is != null) {
		             db.beginTransaction();
		             BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		             String line = reader.readLine();
		             while (!TextUtils.isEmpty(line)) {
		                 db.execSQL(line);
		                 line = reader.readLine();
		             }
		             db.setTransactionSuccessful();
		         }
		    } catch (Exception ex) {
		        // Muestra log             
		    } finally {
		        db.endTransaction();
		        if (is != null) {
		            try {
		                is.close();
		            } catch (IOException e) {
		                // Muestra log
		            }                
		        }
		    }			
	}

	@Override
	//This methos is called when the database needs to be upgraded.
	public void onUpgrade(SQLiteDatabase db,int old_version, int new_version){
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
		db.execSQL("DROP TABLE IF EXISTS productos");
=======

>>>>>>> 32477ed215e82d01b6f7e658596c4fb24f018cf5
=======
>>>>>>> Rama-Edu-Android
		//db.execSQL("DROP TABLE IF EXISTS categorias");
		db.execSQL("DROP TABLE IF EXISTS users");
		db.execSQL("DROP TABLE IF EXISTS products");
		db.execSQL("DROP TABLE IF EXISTS productsTemporary");
<<<<<<< HEAD
>>>>>>> 0eeb76638ed6d7066f13a91e1147d0ce22aab4a7
=======
=======
>>>>>>> bc19c81f8e762b2026442b06370439486d3b88f6

		//db.execSQL("DROP TABLE IF EXISTS categorias");
		db.execSQL("DROP TABLE IF EXISTS users");
		db.execSQL("DROP TABLE IF EXISTS products");
		db.execSQL("DROP TABLE IF EXISTS productsTemporary");
		onCreate(db);
	}
	
	
	public ArrayList<ItemProducto> readProducts(Integer key){
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
		
=======

>>>>>>> Rama-Edu-Android
=======

>>>>>>> 32477ed215e82d01b6f7e658596c4fb24f018cf5
=======
>>>>>>> Rama-Edu-Android
=======

>>>>>>> bc19c81f8e762b2026442b06370439486d3b88f6
		ArrayList<ItemProducto> result=new ArrayList<ItemProducto>();
		String columnas[]={_ID,"name","cant"};
		SQLiteDatabase db=this.getReadableDatabase();
		String args[]={key.toString()};
		//Cursor c=this.getReadableDatabase().query("productos", columnas, null, null,null, null,null);
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======

>>>>>>> Rama-Edu-Android
=======

>>>>>>> 32477ed215e82d01b6f7e658596c4fb24f018cf5
=======
>>>>>>> Rama-Edu-Android
=======

>>>>>>> bc19c81f8e762b2026442b06370439486d3b88f6
		Cursor c=db.query("products", null, "idCat=?", args, null, null, null);
		int id, idName, idNum, idCat;
		id=c.getColumnIndex(_ID);
		idName=c.getColumnIndex("name");
		idNum=c.getColumnIndex("cant");
		idCat=c.getColumnIndex("idCat");
		
		for(c.moveToFirst();!c.isAfterLast();c.moveToNext()){
			//c.getString(idCat);
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
			//TODO hacer consulta para la categoría
			
=======
			//TODO hacer consulta para la categorï¿½a

>>>>>>> Rama-Edu-Android
=======
			//TODO hacer consulta para la categorï¿½a

>>>>>>> 32477ed215e82d01b6f7e658596c4fb24f018cf5
=======
>>>>>>> Rama-Edu-Android
=======

>>>>>>> bc19c81f8e762b2026442b06370439486d3b88f6
			result.add(new ItemProducto(c.getInt(id),c.getString(idName),c.getInt(idNum)));
		}
		return result;
	}
	
	public boolean readUser(String nameUser) {
		SQLiteDatabase db=this.getReadableDatabase();
		String args[]={nameUser};
		Cursor c=db.query("users", null, "name=?", args, null, null, null);
		if (c.getCount() == 0)
			return false;
		else
			return true;
		
	}
	
	public String readPassword(String nameUser) {
		SQLiteDatabase db=this.getReadableDatabase();
		String args[]={nameUser};
		Cursor c=db.query("users", null, "name=?", args, null, null, null);
		int id,name,password,email;
		id = c.getColumnIndex(_ID);
		name = c.getColumnIndex("name");
		password = c.getColumnIndex("password");
		email = c.getColumnIndex("email");
		
		c.moveToFirst();
		
		return c.getString(password);
		
	}
		
	public boolean exist(String data, String table){
		
		String columnas[]={_ID,"name","barCode"};
	
		SQLiteDatabase db=this.getReadableDatabase();
		String args[]={data};
		//Cursor c=this.getReadableDatabase().query("productos", columnas, null, null,null, null,null);
		Cursor c=db.query(table, null, "barCode=?", args, null, null, null);
	
		return (c.moveToFirst());
		
		
	}
	
	public ArrayList<Object> readProductsTemporary(Object data){
		ArrayList<Object> result=new ArrayList<Object>();
		SQLiteDatabase db=this.getReadableDatabase();
		String args[]={(String)data};
		Cursor c=db.query("productsTemporary", null, "barCode=?", args, null, null, null);
		int idName=c.getColumnIndex("name");
		int idBarCode=c.getColumnIndex("barCode");
		c.moveToFirst();
		String tmp1=c.getString(idName);
		String tmp2=c.getString(idBarCode);
		result.add((c.getString(idName)));
		result.add((c.getString(idBarCode)));
		
		return result;
	}
	
		
	public void insertProducts(String name,Integer number, Integer idCategory, String barCode){
		ContentValues registro=new ContentValues();
		
		registro.put("name", name);
		registro.put("cant", number);
		registro.put("idCat", idCategory);
		registro.put("barCode",barCode);
		
		this.getWritableDatabase().insert("products", null, registro);
	
	}
	
	public void updateProduct(String name,String nameNew,Integer number) {
		String args [] = { name };
		ContentValues tmp = new ContentValues();
		
		tmp.put("name",nameNew);
		tmp.put("cant",number);
		
		this.getWritableDatabase().update("products", tmp, "name=?", args);
	}
	
	public void insertUser(String name,String password,String email) {
		ContentValues register = new ContentValues();
		register.put("name", name);
		register.put("password", password);
		register.put("email", email);
		
		this.getWritableDatabase().insert("users", null, register);
	}
}
