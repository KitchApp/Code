package com.example.kitchapp;

public class ItemProducto {
  protected long id;
  protected int cantidad;
  protected String nombre;
  protected boolean selected;
  protected int category;
  protected String units;
  protected String barCode;
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
  public ItemProducto() {
    this.nombre = "";
    //this.tipo = "";
    this.cantidad = 0;
    this.selected = false;
  }
     
  //public ItemProducto(long id, String nombre, int cantidad, String tipo) {
  public ItemProducto(int id, String nombre, int cantidad,int category,String units,boolean selected) {
	    this.id = id;
	    this.nombre = nombre;
	    //this.tipo = tipo;
	    this.category = category;
	    this.units = units;
	    this.cantidad = cantidad;
	    this.selected = selected;
	    //this.barCode=barCode;
	  }
     
  /*public Item(long id, String nombre, String tipo, String rutaImagen) {
    this.id = id;
    this.nombre = nombre;
    this.tipo = tipo;
    this.rutaImagen = rutaImagen;
  }*/
     
  public long getId() {
    return id;
  }
     
  public void setId(long id) {
    this.id = id;
  }
     
  /*public String getRutaImagen() {
    return rutaImagen;
  }*/
     
  /*public void setRutaImagen(String rutaImagen) {
    this.rutaImagen = rutaImagen;
  }*/
     
  public String getNombre() {
    return nombre;
  }
     
  public void setNombre(String nombre) {
    this.nombre = nombre;
  }
  
  public int getCantidad() {
	  return cantidad;
  }
  
  public void setCantidad(int cantidad) {
	  this.cantidad = cantidad;
  }
  
  public boolean isSelected() {
	  return selected;
  }
  
  public void setSelected(boolean selected) {
	  this.selected = selected;
  }
  
  public int getCategory() {
	  return category;
  }
  
  public void setCategory(int category) {
	  this.category = category;
  }
  
  public String getUnits() {
	  return units;
  }
  
  public void setUnits(String units) {
	  this.units = units;
  }
     
 /* public String getTipo() {
    return tipo;
  }
     
  public void setTipo(String tipo) {
    this.tipo = tipo;
  }*/
  
}
