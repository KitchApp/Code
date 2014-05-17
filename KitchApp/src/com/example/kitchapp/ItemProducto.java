package com.example.kitchapp;

public class ItemProducto {
  protected long id;
  protected int cantidad;
  protected String nombre;
  protected boolean selected;
  protected int category;
  protected String barCode;
  
  public ItemProducto() {
    this.nombre = "";
    //this.tipo = "";
    this.cantidad = 0;
    this.selected = false;
  }
  
  public ItemProducto(int id, String nombre, int cantidad) {
	    this.id = id;
	    this.nombre = nombre;
	    //this.tipo = tipo;
	    this.cantidad = cantidad;
	    //this.barCode=barCode;
  }
     
  //public ItemProducto(long id, String nombre, int cantidad, String tipo) {
  public ItemProducto(int id, String nombre, int cantidad,int category, boolean selected) {
	    this.id = id;
	    this.nombre = nombre;
	    //this.tipo = tipo;
	    this.category = category;
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
<<<<<<< HEAD
  
=======

>>>>>>> c6cdd3dc9d1dde44a6ac404340440f340d8c9e81
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
<<<<<<< HEAD
=======

>>>>>>> c6cdd3dc9d1dde44a6ac404340440f340d8c9e81
     
 /* public String getTipo() {
    return tipo;
  }
     
  public void setTipo(String tipo) {
    this.tipo = tipo;
  }*/
  
}
