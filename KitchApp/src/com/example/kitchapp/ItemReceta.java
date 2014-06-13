package com.example.kitchapp;

public class ItemReceta {

	protected long id;
	protected String nombre;
	
		  
	public ItemReceta() {
		this.nombre = "";		    
		
	}
		     
	public ItemReceta(int id,String nombre) {
		this.id=id;
	    this.nombre = nombre;	
	   
	}
		 		     
	public long getId() {
	    return id;
	}
		     
	public void setId(long id) {
	    this.id = id;
	}
	     
	public String getNombre() {
	    return nombre;
	}
		     
	public void setNombre(String nombre) {
	    this.nombre = nombre;
	}

	  		  		 		 
		  
			  
}
