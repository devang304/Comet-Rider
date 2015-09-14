package com.example.cometrider;

import java.util.ArrayList;
import java.util.StringTokenizer;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class TabsWithSwipe extends FragmentActivity implements ActionBar.TabListener 
{

	private ViewPager viewPager;
    private TabsPagerAdapter mAdapter;
    private ActionBar actionBar;
     

	private DataStore data = MainActivity.data;
	
	String trip;
	String source;
	String destination;
	StringTokenizer st;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		overridePendingTransition(R.anim.activity_open_translate,R.anim.activity_close_scale);
		setContentView(R.layout.activity_tabs_with_swipe);
		
		
		
		
		trip = data.trip;
		Log.v("trip", trip);
		st = new StringTokenizer(trip, ";");
		
		source = st.nextToken();
		Log.v("trip", source);
		destination = st.nextToken();
		Log.v("trip", destination);
		String[] tabs = {source, destination}; 
		
		
		viewPager = (ViewPager) findViewById(R.id.pager);
        actionBar = getActionBar();
        mAdapter = new TabsPagerAdapter(getSupportFragmentManager());
 
        viewPager.setAdapter(mAdapter);
        
        
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);    
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#006600")));
 
        // Adding Tabs
        for (String tab_name : tabs) 
        {
            actionBar.addTab(actionBar.newTab().setText(tab_name)
                    .setTabListener(this));
        }
        
        /**
         * on swiping the viewpager make respective tab selected
         * */
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
 
            @Override
            public void onPageSelected(int position) 
            {
                // on changing the page
                // make respected tab selected
                actionBar.setSelectedNavigationItem(position);
                
            }
 
            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) 
            {
            }
 
            @Override
            public void onPageScrollStateChanged(int arg0) 
            {
            	
            }
        });
        
        
		
		
		
        
	}

	 @Override
     public boolean onCreateOptionsMenu(Menu menu) {
                 MenuInflater inflater = getMenuInflater();
                 inflater.inflate(R.menu.tabs_with_swipe, menu);
                 return true;
     }

	 public  boolean onOptionsItemSelected(MenuItem item) 
		{
		    // Handle presses on the action bar items
		    switch (item.getItemId()) 
		    {
		        case R.id.display_map:
		            openMap();
		            return true;
		            
		        case R.id.twitter:
		        	openTwitter(item);
		        	return true;
		        	
		        case R.id.reminder_list:
		        	openReminderList();
		        	return true;
		        
		       
		        default:
		            return super.onOptionsItemSelected(item);
		    }
		}

		private void openReminderList() 
		{
			// TODO Auto-generated method stub
			Intent reminderList_intent = new Intent(getApplicationContext(), ReminderList.class);
			
			startActivity(reminderList_intent);
			overridePendingTransition(R.anim.slide_down, R.anim.slide_up);
		}


		private void openTwitter(MenuItem item) 
		{
			// TODO Auto-generated method stub
			Intent twitter_intent = new Intent(getApplicationContext(), DisplayTwitterActivity.class);
			
			startActivity(twitter_intent);
			overridePendingTransition(R.anim.slide_down, R.anim.slide_up);
			
		}


		private void openMap() 
		{
			// TODO Auto-generated method stub
			Intent map_intent = new Intent(getApplicationContext(), DisplayMapActivity.class);
			startActivity(map_intent);
			overridePendingTransition(R.anim.slide_down, R.anim.slide_up);
			
		}

	 
	@Override
    public void onTabReselected(Tab tab, FragmentTransaction ft) 
	{
		
    }
 
    @Override
    public void onTabSelected(Tab tab, FragmentTransaction ft) 
    {
        // on tab selected
        // show respected fragment view
        viewPager.setCurrentItem(tab.getPosition());
    }
 
    @Override
    public void onTabUnselected(Tab tab, FragmentTransaction ft) 
    {
    	
    }
}
