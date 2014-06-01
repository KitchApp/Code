package com.example.kitchapp;

public class ShoppingListItem {
	
	private String listName;
	private boolean checked;
	
	
	public ShoppingListItem(String name){
		listName = name;
	}
	
	public String getListName() {
		return listName;
	}
	
	public void setListName(String listName) {
		this.listName = listName;
	}
	
	public boolean isChecked() {
		return checked;
	}
	
	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	
}
