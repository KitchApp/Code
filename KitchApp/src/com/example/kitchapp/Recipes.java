package com.example.kitchapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBarActivity;


public class Recipes extends ActionBarActivity {
	
	protected void onCreate(Bundle arg0) {
	        super.onCreate(arg0);
	        setContentView(R.layout.activity_recipes_viewpager);
	  
	        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());
	        ViewPager mViewPager = (ViewPager) findViewById(R.id.pager);
	        mViewPager.setAdapter(adapter);
    	}
	
	
	public class PagerAdapter extends FragmentPagerAdapter {

		public PagerAdapter(FragmentManager fm) {
			super(fm);
		}

		public Fragment getItem(int arg0) {
			switch (arg0) {
	            	case 0:
	                	return new Fragment_TopTen();
	            	case 1:
	                	return new Fragment_Recipes();
	            	case 2:
	                	return new Fragment_Favorites();
	            	default:
	            		return null;
			}
		}

		public int getCount() {
			return 3;
		}
		
		public CharSequence getPageTitle(int position) {
			String titulo = null;
			switch (position) {
			case 0:
	                	titulo = "TOP TEN";
	                	break;
	            	case 1:
	            		titulo = "RECETAS";
	                	break;
	            	case 2:
	            		titulo = "FAVORITOS";
	                	break;
			}
			return titulo;
		}
    }
}
