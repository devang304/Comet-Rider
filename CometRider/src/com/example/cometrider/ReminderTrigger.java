package com.example.cometrider;

import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.support.v4.app.NavUtils;

public class ReminderTrigger extends Activity {

	private Button stop;
	private ImageView image;
	Ringtone rt;
	Uri uri;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reminder_trigger);
		overridePendingTransition(R.anim.activity_open_translate,R.anim.activity_close_scale);
		ActionBar actionbar = getActionBar();
		actionbar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#006600")));
		
		image = (ImageView)findViewById(R.id.alarmTrigger);
		stop = (Button)findViewById(R.id.stopAlarm);
		
		stop.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				rt.stop();
				
				finish();
				
			}
		});
		
		
		Intent intent = getIntent();
		uri = Uri.parse(intent.getExtras().getString("ringtoneUri"));
		rt = RingtoneManager.getRingtone(this, uri);
		rt.play();
		
		
		final Animation alarmanim = AnimationUtils.loadAnimation(this,
			    R.anim.alarmanim);
		
		image.startAnimation(alarmanim);
		
		
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.reminder_trigger, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
