package com.example.cometrider;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.StringTokenizer;

import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.util.Log;

public class Reminder
{
	private Ringtone ringtone;
	private String source;
	private String destination;
	private String bus_departure_time;
	private String reminder_before;
	private String day;
	private Uri ringtone_uri;
	StringTokenizer st;
	private String triggerAlarmAt;
	private long before;
	private long triggerAt;
	private Date depart;
	private Date rightNow;
	private Calendar testcal;
	
	
	public long getTriggerAt() 
	{
		return triggerAt;
	}



	public void setTriggerAt(long triggerAt) {
		this.triggerAt = triggerAt;
	}
	
	public Reminder(String day, String source, String destination, String bd, String rb, Uri uri, Ringtone rt) 
	{
		this.day = day;
		this.source = source;
		this.destination = destination;
		this.bus_departure_time = bd;
		this.reminder_before = rb;
		this.ringtone_uri = uri;
		this.ringtone = rt;
		
		
		setAlarmAt(this.bus_departure_time,this.reminder_before);
	}
	
	

	public Ringtone getRingtone() {
		return ringtone;
	}


	public void setRingtone(Ringtone ringtone) {
		this.ringtone = ringtone;
	}


	public String getDestination() {
		return destination;
	}


	public void setDestination(String destination) {
		this.destination = destination;
	}


	public String getBus_departure_time() {
		return bus_departure_time;
	}


	public void setBus_departure_time(String bus_departure_time) {
		this.bus_departure_time = bus_departure_time;
	}


	public String getSource() {
		return source;
	}


	public void setSource(String source) {
		this.source = source;
	}


	public String getReminder_before() {
		return reminder_before;
	}


	public void setReminder_before(String reminder_before) {
		this.reminder_before = reminder_before;
	}


	public String getDay() {
		return day;
	}


	public void setDay(String day) {
		this.day = day;
	}


	public Uri getRingtone_uri() {
		return ringtone_uri;
	}


	public void setRingtone_uri(Uri ringtone_uri) {
		this.ringtone_uri = ringtone_uri;
	}
	
	public String getTriggerAlarmAt() {
		return triggerAlarmAt;
	}

	public static int getDay(String day)
	 {
		  if(day.equalsIgnoreCase("Monday"))
		   return 2;
		  if(day.equalsIgnoreCase("Tuesday"))
		   return 3;
		  if(day.equalsIgnoreCase("Wednesday"))
		   return 4;
		  if(day.equalsIgnoreCase("Thursday"))
		   return 5;
		  if(day.equalsIgnoreCase("Friday"))
		   return 6;
		  if(day.equalsIgnoreCase("Saturday"))
		   return 7;
		  if(day.equalsIgnoreCase("Sunday"))
		   return 1;
		  return 0; 
	 }
	

	public void setAlarmAt(String bd, String rb) 
	{
		Log.v("rb", "rb first " + rb);
		st = new StringTokenizer(bd, ":");
		String temp1 = st.nextToken();
		bd = st.nextToken();
		int bd_hour = Integer.parseInt(temp1);
		int bd_min;
		String temp2 = "";
		
		for (int i = 0; i < bd.length()-1 ; i++) 
		{
			if(bd.charAt(i) == 'P')
			{
				bd_hour += 12;
				
			}
			else if(bd.charAt(i) == 'A')
			{
				
				
			}
			else
			{
				temp2 = temp2 + bd.charAt(i);
				
			}
			
		}
		
		bd_min = Integer.parseInt(temp2);
		int rb_min = 0;
		Log.v("rb", "rb second " + rb);
		Log.v("rb", "rb_min first " + rb_min);
		if(!rb.equals("On Time"))
		{
			st = new StringTokenizer(rb, " ");
			rb_min = Integer.parseInt(st.nextToken());
			Log.v("rb", "rb_min on time " + rb_min);
			
		}
		else 
		{
			rb_min = 0;
			
			Log.v("rb", "rb_min not on time " + rb_min);
			
		}
			
		
		Log.v("rb", "rb " + rb);
		
		testcal = Calendar.getInstance();
		Calendar rightnow = Calendar.getInstance();
		testcal.set(Calendar.DAY_OF_WEEK, getDay(day));
		testcal.set(Calendar.HOUR_OF_DAY, bd_hour);
		testcal.set(Calendar.MINUTE, bd_min);
		testcal.set(Calendar.SECOND, 0);
		
		if(rightnow.getTimeInMillis() > testcal.getTimeInMillis())
		{
			testcal.add(Calendar.DAY_OF_YEAR, 7);
			
		}
		Log.v("testcal", "" + testcal.getTime().toString());
		Log.v("testcal", "" + testcal.getTimeInMillis());
		Log.v("testcal", "" + (rb_min * 60 * 1000));
		testcal.setTimeInMillis(testcal.getTimeInMillis() - (long)(rb_min * 60 * 1000));
		
		Log.v("testcal", "" + testcal.getTimeInMillis());
		Log.v("testcal", "" + testcal.getTime().toString());
		Log.v("Right now", rightnow.getTime().toString());
		Log.v("Right now",""  + rightnow.getTimeInMillis());
		
		Log.v("testcal", "" + (testcal.getTimeInMillis()-rightnow.getTimeInMillis()));
		
		setTriggerAt(testcal.getTimeInMillis());
		
		
		
		
		
		
		
	}
	
	
}
