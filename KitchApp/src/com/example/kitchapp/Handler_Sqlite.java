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


	private static final String nameBD = "KitchApp";

	Context myContext;
	public Handler_Sqlite(Context ctx){
		super(ctx,nameBD, null,3);
		myContext = ctx;
	}
	
	public SQLiteDatabase open(){
		//Create and/or open a database that will be used for reading and writing.
		return this.getWritableDatabase();
	}
	
	@Override
	//This method is called when the database is created for the first time.
	public void onCreate(SQLiteDatabase db){
		String query1 = "CREATE TABLE products ("+_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT COLLATE NOCASE, cant INTEGER, idCat INTEGER, units TEXT, barCode TEXT);";
		String query2 = "CREATE TABLE productsTemporary("+_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, barCode TEXT);";
		
		//This method is limited to directly execute the SQL code that we pass as a parameter
		String query3 = "CREATE TABLE users ("+_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, password TEXT, email TEXT);";
		String query4 = "CREATE TABLE listShopping ("+_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT)";
		String query5 = "CREATE TABLE productsList ("+_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, idCat INTEGER, units TEXT, barCode TEXT)";
		String query6 = "CREATE TABLE listHaveProducts ("+_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, idList INTEGER, idProduct INTEGER,  cant INTEGER, FOREIGN KEY (idList) REFERENCES listShopping("+_ID+"), FOREIGN KEY (idProduct) REFERENCES productsList("+_ID+"))";
		String query7 = "CREATE TABLE favorites ("+_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, idRecipe INTEGER, title TEXT, image TEXT,numPers INTEGER,nationality TEXT,ingredients TEXT, quantities TEXT, units TEXT,preparation TEXT )";
		
		db.execSQL(query1);	
		db.execSQL(query2);
		db.execSQL(query3);
		db.execSQL(query4);
		db.execSQL(query5);
		db.execSQL(query6);
		db.execSQL(query7);
		
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
		db.execSQL("DROP TABLE IF EXISTS favorites");
		onCreate(db);
	}
	
	public ArrayList<ItemProducto> readAllProductsPantry(){
		
		ArrayList<ItemProducto> result=new ArrayList<ItemProducto>();
		SQLiteDatabase db=this.getReadableDatabase();
		
			String columnas[]={_ID,"name","cant"};
			//String args[]={key.toString()};
			//Cursor c=this.getReadableDatabase().query("productos", columnas, null, null,null, null,null);
			Cursor c=db.query("products", null, null, null, null, null, null);
			int id, idName, idNum, idCat, idUnits;
			id=c.getColumnIndex(_ID);
			idName=c.getColumnIndex("name");
			idNum=c.getColumnIndex("cant");
			idCat=c.getColumnIndex("idCat");
			idUnits=c.getColumnIndex("units");
		
			for(c.moveToFirst();!c.isAfterLast();c.moveToNext()){
				//c.getString(idCat);
				result.add(new ItemProducto(c.getInt(id),c.getString(idName),c.getInt(idNum),c.getInt(idCat),c.getString(idUnits),false));
			}
		
		return result;
	}
	
	public ArrayList<ItemProducto> readProducts(Integer key,String option){
		
		ArrayList<ItemProducto> result=new ArrayList<ItemProducto>();
		SQLiteDatabase db=this.getReadableDatabase();
		if (option.equals("readPantry")) {
			String columnas[]={_ID,"name","cant"};
			String args[]={key.toString()};
			//Cursor c=this.getReadableDatabase().query("productos", columnas, null, null,null, null,null);
			Cursor c=db.query("products", null, "idCat=?", args, null, null, null);
			int id, idName, idNum, idCat, idUnits;
			id=c.getColumnIndex(_ID);
			idName=c.getColumnIndex("name");
			idNum=c.getColumnIndex("cant");
			idCat=c.getColumnIndex("idCat");
			idUnits=c.getColumnIndex("units");
		
			for(c.moveToFirst();!c.isAfterLast();c.moveToNext()){
				//c.getString(idCat);
				result.add(new ItemProducto(c.getInt(id),c.getString(idName),c.getInt(idNum),c.getInt(idCat),c.getString(idUnits),false));
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
				int id, idName, idCat, idUnits;
				id=d.getColumnIndex(_ID);
				idName=d.getColumnIndex("name");
				idCat=d.getColumnIndex("idCat");
				idUnits=d.getColumnIndex("units");
				d.moveToFirst();
				result.add(new ItemProducto(d.getInt(id),d.getString(idName),c.getInt(cant),d.getInt(idCat),d.getString(idUnits),false));
			}
		}
		
		return result;
	}
	
	public ArrayList<ShoppingListItem> readLists(){
		//No utilizo por ahora los argumentos
		ArrayList<ShoppingListItem> lists=new ArrayList<ShoppingListItem>();
		SQLiteDatabase db=this.getReadableDatabase();
		//String args[]={key.toString()};
		//System.out.println(args[0]);
		Integer listName;
		//String[] valores_recuperar = {"name"};
		Cursor c=db.query("listShopping", null, null, null, null, null, null,null);
		listName = c.getColumnIndex("name");
		//c.moveToFirst();
		
		
		for(c.moveToFirst();!c.isAfterLast();c.moveToNext()){
			//c.getString(idCat);
			lists.add(new ShoppingListItem(c.getString(listName)));
		}
		/*c.moveToFirst();
	
        do {
        	String tmp=c.getString(listName);
        	lists.add(new ShoppingListItem(c.getString(listName)));
        } while (c.moveToNext());*/
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
	
		
public void insertProducts(String name,Integer number, Integer idCategory, String units,  String barCode, String option, Integer idList){
	
		
		if (option.equals("insertPantry")) {
			ContentValues registro=new ContentValues();
		
			registro.put("name", name);
			registro.put("cant", number);
			registro.put("idCat", idCategory);
			registro.put("units", units);
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
				registro.put("units",units);
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



	//método temporal
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
	
	public void updateProduct(String name,String nameNew,Integer number,String option,Integer idList) {
		String args [] = { name };
		if (option.equals("updatePantry")) {
			ContentValues tmp = new ContentValues();
		
			tmp.put("name",nameNew);
			tmp.put("cant",number);
		
			this.getWritableDatabase().update("products", tmp, "name=?", args);
		}
		else if (option.equals("updateList")) { 
			ContentValues tmp = new ContentValues();
			ContentValues tmc = new ContentValues();
			tmp.put("name",nameNew);
			tmc.put("cant",number);
			Integer idProduct = getIDProduct(name,"name");
			String argv [] = {idList.toString(),idProduct.toString()};
			this.getWritableDatabase().update("productsList", tmp, "name=?", args);
			this.getWritableDatabase().update("listHaveProducts", tmc, "idList=? AND idProduct=?", argv);
		}
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
	
	public String getBarcode(String name) {
		SQLiteDatabase db=this.getReadableDatabase();
		String args[]={name};
		int barcode;
		Cursor c=db.query("productsList", null, "name=?", args, null, null, null);
		barcode = c.getColumnIndex("barCode");
		c.moveToFirst();
		return c.getString(barcode);
	}
	
	public Integer getNumPersRecipe(String titleRecipe) {
		SQLiteDatabase db=this.getReadableDatabase();
		String args[]={titleRecipe};
		String campos[]={"numPers"};
	
		Cursor c=db.query("favorites", null, "title=?", args, null, null, null);
		int num = c.getColumnIndex("numPers");
		c.moveToFirst();
		return (c.getInt(num));
	}
	
	public String getNationalityRecipe(String titleRecipe) {
		SQLiteDatabase db=this.getReadableDatabase();
		String args[]={titleRecipe};
		String campos[]={"nationality"};
	
		Cursor c=db.query("favorites", null, "title=?", args, null, null, null);
		int nationality = c.getColumnIndex("nationality");
		c.moveToFirst();
		return (c.getString(nationality));
	}
	
	public String getIngredientsRecipe(String titleRecipe) {
		SQLiteDatabase db=this.getReadableDatabase();
		String args[]={titleRecipe};
		String campos[]={"ingredients"};
	
		Cursor c=db.query("favorites", null, "title=?", args, null, null, null);
		int ingredients = c.getColumnIndex("ingredients");
		c.moveToFirst();
		return (c.getString(ingredients));
	}
	
	public String getQuantitiesRecipe(String titleRecipe) {
		SQLiteDatabase db=this.getReadableDatabase();
		String args[]={titleRecipe};
		String campos[]={"quantities"};
	
		Cursor c=db.query("favorites", null, "title=?", args, null, null, null);
		int quantities = c.getColumnIndex("quantities");
		c.moveToFirst();
		return (c.getString(quantities));
	}
	
	public String getUnitsRecipe(String titleRecipe) {
		SQLiteDatabase db=this.getReadableDatabase();
		String args[]={titleRecipe};
		
	
		Cursor c=db.query("favorites", null, "title=?", args, null, null, null);
		int unit = c.getColumnIndex("units");
		c.moveToFirst();
		return (c.getString(unit));
	}
	
	public String getPreparationRecipe(String titleRecipe) {
		SQLiteDatabase db=this.getReadableDatabase();
		String args[]={titleRecipe};
		String campos[]={"preparation"};
	
		Cursor c=db.query("favorites", null, "title=?", args, null, null, null);
		int preparation = c.getColumnIndex("preparation");
		c.moveToFirst();
		return (c.getString(preparation));
	}
	
	public void addFavoriteRecipe(Integer idRecipe, String title, String image, Integer numPers, String nationality, String ingredients, String quantities,String units,String preparation ){
		ContentValues registro=new ContentValues();
		
		registro.put("idRecipe", idRecipe);
		registro.put("title", title);
		registro.put("image", image);
		registro.put("numPers", numPers);
		registro.put("nationality", nationality);
		registro.put("ingredients",ingredients);
		registro.put("quantities",quantities);
		registro.put("units",units);
		registro.put("preparation",preparation);
		this.getWritableDatabase().insert("favorites", null, registro);

	}
	
	public ArrayList<String> readInfoFavoriteRecipes(String data){
		ArrayList<String> result=new ArrayList<String>();
		SQLiteDatabase db=this.getReadableDatabase();
		//String args[]={(String)data};
		String campos[]={(String)data};
		Cursor c=db.query("favorites", null, null, null, null, null, null);
		int idInfo=c.getColumnIndex(data);
		//int idBarCode=c.getColumnIndex("barCode");
		for(c.moveToFirst();!c.isAfterLast();c.moveToNext()){
			String tmp1=c.getString(idInfo);
			//String tmp2=c.getString(idBarCode);
			result.add((c.getString(idInfo)));
			//result.add((c.getString(idBarCode)));
		}
		return result;
	}
	
	public String getUnitsProduct(String name) {
		
		SQLiteDatabase db=this.getReadableDatabase();
		String args[]={name};
		Cursor c=db.query("products", null, "name=?", args, null, null, null);
		int tmp;
		tmp = c.getColumnIndex("units");
		c.moveToFirst();
		return c.getString(tmp);
	}

}
