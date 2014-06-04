package com.example.kitchapp;

import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

public class ItemListAdapter extends BaseExpandableListAdapter {
	
	private Context _context;
    private List<Integer> _listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<Integer, List<ItemProducto>> _listDataChild;
    private boolean visibilityCheckBox;
    private boolean updatePantry;
 
    public ItemListAdapter(Context context, List<Integer> listDataHeader,
            HashMap<Integer, List<ItemProducto>> listChildData,boolean visibilityCheckBox,boolean updatePantry) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
        this.visibilityCheckBox = visibilityCheckBox;
        this.updatePantry = updatePantry;
    }
 
    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .get(childPosititon);
    }
 
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }
 
    @Override
    public View getChildView(int groupPosition, final int childPosition,
            boolean isLastChild, View convertView, ViewGroup parent) {
 
        final ItemProducto childText = (ItemProducto) getChild(groupPosition, childPosition);
 
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.activity_item_listview, null);
        }
        
        CheckBox checkProduct = (CheckBox) convertView.findViewById(R.id.checkBox_Product);
        if (visibilityCheckBox) {
        	checkProduct.setVisibility(View.VISIBLE);
        }
        checkProduct.setOnClickListener(new OnClickListener() {
        	@Override
        	public void onClick(View v) {
        		ItemProducto data = (ItemProducto) v.getTag();
        		data.setSelected(((CheckBox) v).isChecked());
        	}
        });
 
        TextView txtListNameChild = (TextView) convertView
                .findViewById(R.id.textView_Item_Name);
        
        TextView txtListCantChild = (TextView) convertView
                .findViewById(R.id.textView_Item_Cant);
        
        TextView txtListUnitsChild = (TextView) convertView
        		.findViewById(R.id.textView_Item_Units);
        
        if ((updatePantry) && (childText.isSelected())) {
        	txtListNameChild.setPaintFlags(txtListNameChild.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        	txtListCantChild.setPaintFlags(txtListCantChild.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        	txtListUnitsChild.setPaintFlags(txtListUnitsChild.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        }
        else {
        	txtListNameChild.setPaintFlags(txtListNameChild.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
        	txtListCantChild.setPaintFlags(txtListCantChild.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
        	txtListUnitsChild.setPaintFlags(txtListUnitsChild.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
        	childText.setSelected(false);
        }
        
        txtListNameChild.setText(childText.getNombre());
        txtListCantChild.setText(childText.getCantidad() + "");
        txtListUnitsChild.setText(childText.getUnits());
                
        checkProduct.setChecked(childText.isSelected());
        checkProduct.setTag(childText);
        
        return convertView;
    }
 
    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .size();
    }
 
    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }
 
    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }
 
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }
 
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
            View convertView, ViewGroup parent) {
        Integer headerTitle = (Integer) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.activity_item_listview_listproducts, null);
        }
        String categoryHeader = "";
        switch (headerTitle) {
        	case 1:
        		categoryHeader = "Lacteos";
        		break;
        	case 2:
        		categoryHeader = "Frutas y Verduras";
        		break;
        	case 3:
        		categoryHeader = "Pan y Bolleria";
        		break;
        	case 4:
        		categoryHeader = "Bebidas";
        		break;
        	case 5:
        		categoryHeader = "Carnes";
        		break;
        	case 6:
        		categoryHeader = "Pescados";
        		break;
        	case 7:
        		categoryHeader = "Condimentos";
        		break;
        	case 8:
        		categoryHeader = "Pastas y Arroces";
        		break;
        	case 9:
        		categoryHeader = "Congelados";
        		break;
        	case 10:
        		categoryHeader = "Salsas";
        		break;
        	case 11:
        		categoryHeader = "Drogueria";
        		break;
        	case 12:
        		categoryHeader = "Varios";
        		break;
        	
        }
 
        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.textViewProductsList);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(categoryHeader);
 
        return convertView;
    }
 
    @Override
    public boolean hasStableIds() {
        return false;
    }
 
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}
