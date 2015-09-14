package com.example.cometrider;

import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.NavUtils;

public class ReminderActivity extends Activity {

	private Button set_button;
	private Button notification_button;
	private TextView time_trip;
	private Spinner time_spinner;
	private TextView leavingFrom;
	private TextView going_to;
	private TextView day_selected;
	Ringtone rt;
	PendingIntent pendingIntent;
	Uri uri;
	
	
	String trip;
	String triggerBefore;
	private DataStore data;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reminder);
		overridePendingTransition(R.anim.activity_open_translate,R.anim.activity_close_scale);
		data = MainActivity.data;
		
		trip = getIntent().getExtras().getString("time");
		
		Log.v("trip", trip);
		time_trip = (TextView)findViewById(R.id.trip_time);
		
		time_trip.setText(trip);
		
		day_selected = (TextView)findViewById(R.id.day_selected);
		day_selected.setText(data.day);
		
		leavingFrom = (TextView)findViewById(R.id.leaving_source);
		leavingFrom.setText(data.source);
		
		going_to = (TextView)findViewById(R.id.going_dest);
		going_to.setText(data.destination);
		
		
		time_spinner = (Spinner)findViewById(R.id.time_spinner);
		
		
		ActionBar actionbar = getActionBar();
		actionbar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#006600")));
		
		notification_button = (Button)findViewById(R.id.ringtone_button);
		notification_button.setOnClickListener(new OnClickListener() 
		{
			
			@Override
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
				triggerBefore = time_spinner.getSelectedItem().toString();
				Log.v("time_spinner", triggerBefore);
				
				Intent intent = new Intent(RingtoneManager.ACTION_RINGTONE_PICKER);
				intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TYPE, RingtoneManager.TYPE_ALARM);
				intent.putExtra(RingtoneManager.EXTRA_RINGTONE_SHOW_SILENT, false);
				intent.putExtra(RingtoneManager.EXTRA_RINGTONE_SHOW_DEFAULT, true);
				intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TITLE, "Select notification tone");
				String uri = null;
				//chooses and keeps the selected item as a uri
				if ( uri != null ) 
				{ 
					intent.putExtra(RingtoneManager.EXTRA_RINGTONE_EXISTING_URI, Uri.parse( uri ));
				
				} 
				else 
				{ 
					intent.putExtra(RingtoneManager.EXTRA_RINGTONE_EXISTING_URI, (Uri)null);
				}
				startActivityForResult(intent, 0);
				
			}
		});
		
		
		set_button = (Button) findViewById(R.id.set_button);
		set_button.setEnabled(false);
		set_button.setOnClickListener(new OnClickListener() 
		{
			
			@Override
			public void onClick(View arg0) 
			{
				
				CharSequence text = "Reminder Set!";
				int duration = Toast.LENGTH_SHORT;
				
				Toast toast = Toast.makeText(getApplicationContext(), text, duration);
				toast.show();
				
				Intent intent = new Intent(getApplicationContext(), ReminderTrigger.class);
				intent.putExtra("ringtoneUri", uri.toString());
				PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);
				
				AlarmManager manager = (AlarmManager)getApplicationContext().getSystemService(Context.ALARM_SERVICE);
				
				Log.v("set for", "" + data.reminderList.get(data.reminderList.size()-1).getTriggerAt());
				manager.set(AlarmManager.RTC_WAKEUP, data.reminderList.get(data.reminderList.size()-1).getTriggerAt(), pendingIntent);
				
				
				finish();
				
			}
		});
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent Mringtone) {
		switch (resultCode) 
		{
			/*
			* 
			*/
			case RESULT_OK: 
			//sents the ringtone that is picked in the Ringtone Picker Dialog
			uri = Mringtone.getParcelableExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI);
			rt = RingtoneManager.getRingtone(this, uri);
			
			notification_button.setText(rt.getTitle(getApplicationContext()));
			
			data.reminderList.add(new Reminder(data.day, data.source, data.destination, trip, triggerBefore, uri, rt));
			
			
			set_button.setEnabled(true);
			//prints out the result in the console window
			Log.i("Sample", "uri " + uri);
	
			//this passed the ringtone selected from the user to a new method
			//play(uri);

			//set default ringtone
			try
			{ 
				RingtoneManager.setActualDefaultRingtoneUri(this, resultCode, uri);
			}
			catch (Exception localException)
			{
				
			}
			break; 
	
	
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.reminder, menu);
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
