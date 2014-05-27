package com.example.kitchapp;

import java.util.ArrayList;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
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
import android.support.v7.app.ActionBar.TabListener;
import android.support.v7.app.ActionBarActivity;
<<<<<<< HEAD
import android.view.View;
import android.widget.RelativeLayout;
=======
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
>>>>>>> Rama-Vivi-Android

public class Recipes extends ActionBarActivity {
	
	private TabListener tabListener;
	private ViewPager mViewPager;
	private PagerAdapter adapter;
	private ViewPagerListener pageListener;
	private static ArrayList<String> infoBundle;
	
		protected void onCreate(Bundle arg0) {
	        super.onCreate(arg0);
	        setContentView(R.layout.activity_recipes_viewpager);
	        
	        Bundle extras1= this.getIntent().getExtras();
			if(extras1!=null){
				infoBundle=extras1.getStringArrayList("infoTopTen");
				
			}
	  
	        adapter = new PagerAdapter(getSupportFragmentManager());
	        mViewPager = (ViewPager) findViewById(R.id.pager);
	        mViewPager.setAdapter(adapter);
	        mViewPager.setOnPageChangeListener(pageListener);
	        setActionBar();
	    }
		
		
		public class PagerAdapter extends FragmentPagerAdapter {
	
			public PagerAdapter(FragmentManager fm) {
				super(fm);
			}			
			
<<<<<<< HEAD
			@Override
=======

>>>>>>> Rama-Vivi-Android
			public Fragment getItem(int arg0) {
				switch (arg0) {
		            case 0:
		            	return Fragment_TopTen.newInstance(infoBundle);
		            case 1:
		                //return new Fragment_Recipes();
		            case 2:
		                //return new Fragment_Favorites();
		            default:
		            	return null;
				}
			}
<<<<<<< HEAD
			 
			@Override
=======
	
>>>>>>> Rama-Vivi-Android
			public int getCount() {
				return 1;
			}
			
<<<<<<< HEAD
			@Override
			public CharSequence getPageTitle(int position) {
				String titulo = null;
				switch (position) {
					case 0:					
=======
			public CharSequence getPageTitle(int position) {
				String titulo = null;
				switch (position) {
					case 0:
>>>>>>> Rama-Vivi-Android
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
		
		private class ViewPagerListener extends ViewPager.SimpleOnPageChangeListener {
			@Override
			public void onPageSelected(int position) {
				getSupportActionBar().setSelectedNavigationItem(position);
			}
		}
		
		private void setActionBar() {
	        final ActionBar actionBar = getSupportActionBar();
	        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
	        tabListener = new ActionBar.TabListener() {
	 
	            @Override
	            public void onTabUnselected(Tab tab, FragmentTransaction arg1) {
	            }
	 
	            @Override
	            public void onTabSelected(Tab tab, FragmentTransaction arg1) {
	               /* if(adapter == null)
	                    setPagerAdapter();*/
	                if (mViewPager.getCurrentItem() != tab.getPosition())
<<<<<<< HEAD
	                    mViewPager.setCurrentItem(tab.getPosition());	                		 
=======
	                    mViewPager.setCurrentItem(tab.getPosition());
	 
>>>>>>> Rama-Vivi-Android
	            }
	 
	            @Override
	            public void onTabReselected(Tab tab, FragmentTransaction arg1) {
	            }
	        };
			
	        for (int i = 0; i < 3; i++) {
	            Tab tab = actionBar.newTab();
	            tab.setTabListener(tabListener);
	            tab.setText(adapter.getPageTitle(i));
	            actionBar.addTab(tab);
	        }
			
	        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
	            actionBar.setTitle("");
	        } /*else {
	            actionBar.setTitle(R.string.app_name_capital);
	        }*/
<<<<<<< HEAD
	    }				
}
=======
	    }
		
		}
>>>>>>> Rama-Vivi-Android
