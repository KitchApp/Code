package com.example.kitchapp;

import com.example.kitchapp.R.color;

import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.CalendarContract.Colors;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBar.TabListener;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.RelativeLayout;

public class Recipes extends ActionBarActivity {
	
	private TabListener tabListener;
	private ViewPager mViewPager;
	private PagerAdapter adapter;
	private ViewPagerListener pageListener;
	
		protected void onCreate(Bundle arg0) {
	        super.onCreate(arg0);
	        setContentView(R.layout.activity_recipes_viewpager);
	        setPagerAdapter();
            setActionBar();
	    }
		
		private void setPagerAdapter() {
	        if (adapter == null) {
	            adapter = new PagerAdapter(getSupportFragmentManager());
	        }
	        if (pageListener == null) {
	        	pageListener = new ViewPagerListener();
	        }
	        if (mViewPager == null) {
	            mViewPager = (ViewPager) findViewById(R.id.pager);
	            mViewPager.setAdapter(adapter);
	            mViewPager.setOnPageChangeListener(pageListener);
	            mViewPager.setOffscreenPageLimit(2);
	        }
	    }
		
		private static class PagerAdapter extends FragmentStatePagerAdapter {
	
			public PagerAdapter(FragmentManager fm) {
				super(fm);
			}			
			
			@Override
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
			 
			@Override
			public int getCount() {
				return 3;
			}
			
			@Override
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
	        //actionBar.setCustomView(R.drawable.tabselector);
	        tabListener = new ActionBar.TabListener() {
	 
	            @Override
	            public void onTabUnselected(Tab tab, FragmentTransaction arg1) {
	           
	            }
	 
	            @Override
	            public void onTabSelected(Tab tab, FragmentTransaction arg1) {
	            	
	                if(adapter == null)
	                    setPagerAdapter();
	            	
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
