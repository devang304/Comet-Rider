package com.example.cometrider;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.support.v4.app.NavUtils;

public class ReminderList extends Activity {

	private DataStore data = MainActivity.data;
	private ListView mListView_reminders;
	private ArrayList<Reminder> list_reminders = data.reminderList;
	private ReminderListAdapter mAdapter_reminder;
	private TextView noRem;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reminder_list);
		
		ActionBar actionbar = getActionBar();
		actionbar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#006600")));
		
		
		noRem = (TextView)findViewById(R.id.noRem);
		mListView_reminders = (ListView)findViewById(R.id.reminderListView);
		mAdapter_reminder = new ReminderListAdapter(getApplicationContext(), list_reminders);
		mListView_reminders.setAdapter(mAdapter_reminder);
		
		if(!(list_reminders.size() == 0))
		{
			mListView_reminders.setVisibility(View.VISIBLE);
			noRem.setVisibility(View.GONE);
			
		}
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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


}
