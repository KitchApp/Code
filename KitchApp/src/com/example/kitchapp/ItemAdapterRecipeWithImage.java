package com.example.kitchapp;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Request;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

	public class ItemAdapterRecipeWithImage extends BaseAdapter {

		private Context context;
		private List<ItemRecipeWithImage> items;

		public ItemAdapterRecipeWithImage(Context context, List<ItemRecipeWithImage> items) {
			this.context = context;
			this.items = items;
		}

		@Override
		public int getCount() {
			return this.items.size();
		}

		@Override
		public Object getItem(int position) {
			return this.items.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			View rowView = convertView;

			if (convertView == null) {
				// Create a new view into the list.
				LayoutInflater inflater = (LayoutInflater) context
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				rowView = inflater.inflate(R.layout.activity_item_recipe_with_image, parent, false);
			}

			// Set data into the view.
			ImageView ivItem = (ImageView) rowView.findViewById(R.id.ivItem);
			TextView tvTitle = (TextView) rowView.findViewById(R.id.tvTitle);

			ItemRecipeWithImage item = this.items.get(position);
			tvTitle.setText(item.getTitle());
			
			String tmp=item.getImage();
			String aux;
			if(tmp!=null)
				Picasso.with(context).load(item.getImage()).into(ivItem);
			else
				Picasso.with(context).load(item.getImageId()).into(ivItem);
			return rowView;
		}
		
	}
