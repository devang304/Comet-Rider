package com.example.cometrider;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.support.v4.app.NavUtils;

public class DisplayTwitterActivity extends Activity {

	private WebView webView;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_twitter);
		// Show the Up button in the action bar.
		webView = (WebView) findViewById(R.id.webView_twitter);
		String webUrl="https://mobile.twitter.com/UTDCometCruiser";
		webView.getSettings().setJavaScriptEnabled(true);    
		webView.getSettings().setBuiltInZoomControls(true);
		webView.loadUrl(webUrl);
		ActionBar actionbar = getActionBar();
		actionbar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#006600")));
		
		
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display_twitter, menu);
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
