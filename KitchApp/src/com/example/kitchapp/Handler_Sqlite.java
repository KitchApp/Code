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
		String query1 = "CREATE TABLE products ("+_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, cant INTEGER, idCat INTEGER, barCode TEXT);";
		String query2 = "CREATE TABLE productsTemporary("+_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, barCode TEXT);";
		
		//This method is limited to directly execute the SQL code that we pass as a parameter
		String query3 = "CREATE TABLE users ("+_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, password TEXT, email TEXT);";
		String query4 = "CREATE TABLE listShopping ("+_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT)";
		String query5 = "CREATE TABLE productsList ("+_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, idCat INTEGER, barCode TEXT)";
		String query6 = "CREATE TABLE listHaveProducts ("+_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, idList INTEGER, idProduct INTEGER,  cant INTEGER, FOREIGN KEY (idList) REFERENCES listShopping("+_ID+"), FOREIGN KEY (idProduct) REFERENCES productsList("+_ID+"))";

		db.execSQL(query1);	
		db.execSQL(query2);
		db.execSQL(query3);
		db.execSQL(query4);
		db.execSQL(query5);
		db.execSQL(query6);
		
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

		//db.execSQL("DROP TABLE IF EXISTS categorias");
		db.execSQL("DROP TABLE IF EXISTS users");
		db.execSQL("DROP TABLE IF EXISTS products");
		db.execSQL("DROP TABLE IF EXISTS productsTemporary");
		db.execSQL("DROP TABLE IF EXISTS listShopping");
		db.execSQL("DROP TABLE IF EXISTS productsList");
		db.execSQL("DROP TABLE IF EXISTS listHaveProducts");
		onCreate(db);
	}
	
	
	public ArrayList<ItemProducto> readProducts(Integer key,String option){
		
		ArrayList<ItemProducto> result=new ArrayList<ItemProducto>();
		SQLiteDatabase db=this.getReadableDatabase();
		if (option.equals("readPantry")) {
			String columnas[]={_ID,"name","cant"};
			String args[]={key.toString()};
			//Cursor c=this.getReadableDatabase().query("productos", columnas, null, null,null, null,null);
			Cursor c=db.query("products", null, "idCat=?", args, null, null, null);
			int id, idName, idNum, idCat;
			id=c.getColumnIndex(_ID);
			idName=c.getColumnIndex("name");
			idNum=c.getColumnIndex("cant");
			idCat=c.getColumnIndex("idCat");
		
			for(c.moveToFirst();!c.isAfterLast();c.moveToNext()){
				//c.getString(idCat);
				result.add(new ItemProducto(c.getInt(id),c.getString(idName),c.getInt(idNum),c.getInt(idCat),false));
			}
		}
		else if (option.equals("readList")) {
			System.out.println("holaaaa");
			String args[]={key.toString()};
			System.out.println(args[0]);
			int idL,idProduct,cant;
			Cursor c=db.query("listHaveProducts", null, "idList=?", args, null, null, null);
			idL = c.getColumnIndex("idList");
			idProduct = c.getColumnIndex("idProduct");
			cant = c.getColumnIndex("cant");
			for(c.moveToFirst();!c.isAfterLast();c.moveToNext()){
				String argv[]={((Integer) c.getInt(idProduct)).toString()};
				Cursor d=db.query("productsList", null, "_ID=?", argv, null, null, null);
				int id, idName, idCat;
				id=d.getColumnIndex(_ID);
				idName=d.getColumnIndex("name");
				idCat=d.getColumnIndex("idCat");
				d.moveToFirst();
				result.add(new ItemProducto(d.getInt(id),d.getString(idName),c.getInt(cant),d.getInt(idCat),false));
			}
		}
		
		return result;
	}
	
	public ArrayList<ShoppingListItem> readLists(){

		ArrayList<ShoppingListItem> lists=new ArrayList<ShoppingListItem>();
		SQLiteDatabase db=this.getReadableDatabase();
		Integer listName;
		Cursor c=db.query("listShopping", null, null, null, null, null, null,null);
		listName = c.getColumnIndex("name");
		
		for(c.moveToFirst();!c.isAfterLast();c.moveToNext()){
			lists.add(new ShoppingListItem(c.getString(listName)));
		}
		return lists;		

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
	
		
public void insertProducts(String name,Integer number, Integer idCategory, String barCode, String option, Integer idList){
	
		
		if (option.equals("insertPantry")) {
			ContentValues registro=new ContentValues();
		
			registro.put("name", name);
			registro.put("cant", number);
			registro.put("idCat", idCategory);
			registro.put("barCode",barCode);
			this.getWritableDatabase().insert("products", null, registro);
		}
		else if (option.equals("insertList")) {
			if (existProductList(name)) {
				Integer idProduct = getIDProduct(name,"name");
				ContentValues tmp = new ContentValues();
				tmp.put("idList",idList);
				tmp.put("idProduct",idProduct);
				tmp.put("cant",number);
				this.getWritableDatabase().insert("listHaveProducts", null, tmp);
			}
			else {
				ContentValues registro=new ContentValues();
		
				registro.put("name", name);
				registro.put("idCat", idCategory);
				registro.put("barCode",barCode);
				this.getWritableDatabase().insert("productsList", null, registro);
				Integer idProduct = getIDProduct(name,"name");
				ContentValues tmp = new ContentValues();
				tmp.put("idList",idList);
				tmp.put("idProduct",idProduct);
				tmp.put("cant",number);
				this.getWritableDatabase().insert("listHaveProducts", null, tmp);
			}
				
		}
		
	}

	
public Integer getIDProduct(String name,String col) {
	
	SQLiteDatabase db=this.getReadableDatabase();
	String args[]={name};
	Cursor c=db.query("productsList", null, col + "=?", args, null, null, null);
	int id,nameP,idCat,barcode;
	id = c.getColumnIndex(_ID);
	nameP = c.getColumnIndex("name");
	idCat = c.getColumnIndex("idCat");
	barcode = c.getColumnIndex("barCode");
	
	c.moveToFirst();
	
	return c.getInt(id);
}

public Integer getIdList(String name) {
	
	SQLiteDatabase db=this.getReadableDatabase();
	String args[]={name};
	Cursor c=db.query("listShopping", null, "name=?", args, null, null, null);
	int id;
	id = c.getColumnIndex(_ID);
	c.moveToFirst();
	return c.getInt(id);
}

public boolean existProductList(String name) {
	SQLiteDatabase db=this.getReadableDatabase();
	String args[]={name};
	Cursor c=db.query("productsList", null, "name=?", args, null, null, null);

	return (c.moveToFirst());
}


	//mï¿½todo temporal

	public void insertLists(String name){
		ContentValues registro=new ContentValues();
		
		registro.put("name", name);
		this.getWritableDatabase().insert("listShopping", null, registro);
	
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
	
	public void removeProduct(String name,String option,Integer idList) {
		String args [] = { name };
		if (option.equals("deletePantry")) {
			this.getWritableDatabase().delete("products","name=?",args);
		}
		else if (option.equals("deleteList")) {
			Integer idProduct = getIDProduct(name,"name");
			String argv [] = { idList.toString(),idProduct.toString() };
			this.getWritableDatabase().delete("listHaveProducts","idList=? AND idProduct=?",argv);
			if (!existProductOtherList(idProduct)) {
				this.getWritableDatabase().delete("productsList","name=?",args);
			}
		}
	}
	
	public boolean existProductOtherList(Integer id) {
		SQLiteDatabase db=this.getReadableDatabase();
		String args[]={id.toString()};
		Cursor c=db.query("listHaveProducts", null, "idProduct=?", args, null, null, null);
	
		return (c.moveToFirst());
	}
	
	//Hay que borrar primero los productos--->corregirlo
	public void remove(String tableName, String column, String elemRemove) {
		String args[] = {elemRemove};
		String tmp=column+"=?";
		this.getWritableDatabase().delete(tableName,column+"=?",args);
	}
	
	
	public boolean existProductAdded(String name) {
		SQLiteDatabase db=this.getReadableDatabase();
		String args[]={name};
		Cursor c=db.query("products", null, "name=?", args, null, null, null);
	
		return (c.moveToFirst());
	}
	
	public Integer getCant(String name) {
		SQLiteDatabase db=this.getReadableDatabase();
		String args[]={name};
		int cant;
		Cursor c=db.query("products", null, "name=?", args, null, null, null);
		cant = c.getColumnIndex("cant");
		c.moveToFirst();
		return (c.getInt(cant));
	}
	
	public boolean existListHaveProducts(Integer idProduct,Integer idList) {
		SQLiteDatabase db=this.getReadableDatabase();
		String args[]={idList.toString(),idProduct.toString()};
		Cursor c=db.query("listHaveProducts", null, "idList=? AND idProduct=?", args, null, null, null);
	
		return (c.moveToFirst());
	}
	
	public ItemProducto existProduct(String data, String table, String col){
		
		SQLiteDatabase db= this.getReadableDatabase();
		String args[]={data};
		Cursor c = db.query(table, null, "name=?", args, null, null, null);
		ItemProducto p = null;
		int id = c.getColumnIndex(_ID);
		int n = c.getColumnIndex("name");
		int q = c.getColumnIndex("cant");
		if (c.moveToFirst()) {
			p = new ItemProducto(c.getInt(id),c.getString(n),c.getInt(q));
		};	
		return p;
	}
	
	public boolean existList(String table, String data){
			
		SQLiteDatabase db = this.getReadableDatabase();
		String args[] = {data};
		Cursor c = db.query(table, null, "name=?", args, null, null, null);
	
		return (c.moveToFirst());		
	}
	
	public void updateNameList(String oldName,String newName) {
		String args [] = { oldName };
		ContentValues tmp = new ContentValues();
		
		tmp.put("name",newName);
		
		this.getWritableDatabase().update("listShopping", tmp, "name=?", args);
	}

}
