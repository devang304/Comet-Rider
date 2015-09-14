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

public class DisplayMapActivity extends Activity {

	private WebView webView;
	 
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_map);
		
		webView = (WebView) findViewById(R.id.webView1);
		String webUrl="http://www.utdallas.edu/services/download/map_comet_cruiser.pdf";
		webView.getSettings().setJavaScriptEnabled(true);    
		webView.getSettings().setBuiltInZoomControls(true);
		webView.loadUrl("https://docs.google.com/gview?embedded=true&url="+ webUrl);
		ActionBar actionbar = getActionBar();
		actionbar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#006600")));
 
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display_map, menu);
		return true;
	}

	@Override
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
