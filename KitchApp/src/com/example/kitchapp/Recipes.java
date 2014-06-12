package com.example.kitchapp;

import java.util.ArrayList;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBar.TabListener;
import android.support.v7.app.ActionBarActivity;

public class Recipes extends ActionBarActivity {
	
	private TabListener tabListener;
	private ViewPager mViewPager;
	private PagerAdapter adapter;
	private ViewPagerListener pageListener;
	private static ArrayList<String> infoBundle;
	private static ArrayList<String> titlefavoriteBundle;
	private static ArrayList<String> imagesfavoriteBundle;
	private Bundle extras1=new Bundle();
	
		protected void onCreate(Bundle arg0) {
	        super.onCreate(arg0);
	        setContentView(R.layout.activity_recipes_viewpager);
	        
	        extras1= this.getIntent().getExtras();
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
			
			

			public Fragment getItem(int arg0) {
				switch (arg0) {
		            case 0:
		            	return Fragment_TopTen.newInstance(infoBundle);
		            case 1:
		                return new Fragment_Recipes();
		            case 2:
		                return Fragment_Favorites.newInstance(titlefavoriteBundle,imagesfavoriteBundle);
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
	            	infoBundle=extras1.getStringArrayList("infoTopTen");
    				titlefavoriteBundle=extras1.getStringArrayList("titleFavorite");
    				imagesfavoriteBundle=extras1.getStringArrayList("imagesFavorite");
    			
	                if (mViewPager.getCurrentItem() != tab.getPosition())
	                    mViewPager.setCurrentItem(tab.getPosition());
	 
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
	    }
		
				
		}
