package com.example.kitchapp;

public class ItemProducto {
  protected long id;
  protected int cantidad;
  protected String nombre;
  protected String barCode;
<<<<<<< HEAD
<<<<<<< HEAD
         
=======

>>>>>>> Rama-Edu-Android
=======

>>>>>>> 32477ed215e82d01b6f7e658596c4fb24f018cf5
  public ItemProducto() {
    this.nombre = "";
    //this.tipo = "";
    this.cantidad = 0;
  }
     
  //public ItemProducto(long id, String nombre, int cantidad, String tipo) {
  public ItemProducto(int id, String nombre, int cantidad) {
    this.id = id;
    this.nombre = nombre;
    //this.tipo = tipo;
    this.cantidad = cantidad;
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
     
 /* public String getTipo() {
    return tipo;
  }
     
  public void setTipo(String tipo) {
    this.tipo = tipo;
  }*/
  
}
