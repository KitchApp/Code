package com.example.kitchapp;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import static android.provider.BaseColumns._ID;


public class Handler_Sqlite extends SQLiteOpenHelper {
	public Handler_Sqlite(Context ctx){
		super(ctx,"MyBase", null,6);
	}
	
	public SQLiteDatabase open(){
		//Create and/or open a database that will be used for reading and writing.
		return this.getWritableDatabase();
	}
	
	@Override
	//This method is called when the database is created for the first time.
	public void onCreate(SQLiteDatabase db){
		String query1 = "CREATE TABLE productos ("+_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, cant INTEGER, idCat INTEGER);";
		//String query2= "CREATE TABLE categorias("+_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT)";
		//This method is limited to directly execute the SQL code that we pass as a parameter
		db.execSQL(query1);	
		//db.execSQL(query2);
	}

	@Override
	//This methos is called when the database needs to be upgraded.
	public void onUpgrade(SQLiteDatabase db,int old_version, int new_version){
		db.execSQL("DROP TABLE IF EXISTS productos");
		//db.execSQL("DROP TABLE IF EXISTS categorias");
		onCreate(db);
	}

	
	public ArrayList<ItemProducto> read(Integer key){
		
		ArrayList<ItemProducto> result=new ArrayList<ItemProducto>();
		String columnas[]={_ID,"name","cant"};
		SQLiteDatabase db=this.getReadableDatabase();
		String args[]={key.toString()};
		//Cursor c=this.getReadableDatabase().query("productos", columnas, null, null,null, null,null);
		Cursor c=db.query("productos", null, "idCat=?", args, null, null, null);
		int id, idName, idNum, idCat;
		id=c.getColumnIndex(_ID);
		idName=c.getColumnIndex("name");
		idNum=c.getColumnIndex("cant");
		idCat=c.getColumnIndex("idCat");
		
		for(c.moveToFirst();!c.isAfterLast();c.moveToNext()){
			c.getString(idCat);
			//TODO hacer consulta para la categoría
			
			result.add(new ItemProducto(c.getInt(id),c.getString(idName),c.getInt(idNum)));
		}
		return result;
	}
	
		
	public void insertProducto(String name,Integer number, Integer idCategory){
		ContentValues registro=new ContentValues();
		
		registro.put("name", name);
		registro.put("cant", number);
		registro.put("idCat", idCategory);
		
		this.getWritableDatabase().insert("productos", null, registro);
	
	}
}
