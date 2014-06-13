package com.example.kitchapp;

public class ItemProducto {
  protected long id;
  protected int cantidad;
  protected String nombre;
  protected boolean selected;
  protected int category;
  protected String units;
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
	    this.cantidad = cantidad;
  }
     
  public ItemProducto(int id, String nombre, int cantidad,int category,String units, boolean selected) {
	    this.id = id;
	    this.nombre = nombre;
	    this.category = category;
	    this.cantidad = cantidad;
	    this.selected = selected;
	    this.units = units;
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

}
