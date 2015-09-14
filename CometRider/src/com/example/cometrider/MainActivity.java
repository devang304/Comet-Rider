package com.example.cometrider;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.ActionBar;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity 
{

	
	protected static final String TAG = "test";
	private Button next_button;
	private Spinner fromSpinner;
	private Spinner toSpinner;
	private Spinner daySpinner;
	private TextView from;
	private TextView to;
	private TextView day;
	private ProgressBar progress;
	private int mShortAnimationDuration;
	private RelativeLayout mRelativeLayout;
	
	String trip;
	String source;
	String destination;
	String day_chosen;
	
	static DataStore data ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		
		overridePendingTransition(R.anim.activity_open_scale,R.anim.activity_close_translate);
		setContentView(R.layout.activity_main);
		
		data = new DataStore(getResources().getStringArray(R.array.bus_stop_choices));
		
		ActionBar actionbar = getActionBar();
		actionbar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#006600")));
		
		fromSpinner = (Spinner) findViewById(R.id.from_spinner);
		toSpinner = (Spinner) findViewById(R.id.to_spinner);
		daySpinner = (Spinner)findViewById(R.id.day_spinner);
		
		from = (TextView) findViewById(R.id.from);
		to = (TextView) findViewById(R.id.to);
		day = (TextView) findViewById(R.id.day);
		
		
		next_button = (Button) findViewById(R.id.next_button);
		next_button.setOnClickListener(new OnClickListener() 
		{
			
			@Override
			public void onClick(View view) 
			{
				// TODO Auto-generated method stub
				Log.v(TAG, fromSpinner.getSelectedItem().toString());
				Log.v(TAG, toSpinner.getSelectedItem().toString());
				trip = fromSpinner.getSelectedItem().toString() + ";" + toSpinner.getSelectedItem().toString() 
						+ ";" + daySpinner.getSelectedItem().toString();
				
				if(tripValid(trip))
				{
					
					if(data.valid(source, destination, day_chosen))
					{
						Intent schedule_intent = new Intent(getApplicationContext(), TabsWithSwipe.class);
						//schedule_intent.putExtra("trip", trip);
						data.source = source;
						data.destination = destination;
						data.trip = trip;
						startActivity(schedule_intent);
						
						
					}
					
					else
					{
						Toast.makeText(getApplicationContext(), "Schedule for given stops not found in database!"
								, Toast.LENGTH_SHORT).show();
						
					}
					
					
				}
				else
				{
					Toast.makeText(getApplicationContext(), "Invalid Source and destination!", Toast.LENGTH_SHORT).show();
					
				}
				
				
				
				
			}
		});
		
		progress = (ProgressBar)findViewById(R.id.progress_bar);
		mShortAnimationDuration = 2000;
		
		mRelativeLayout = (RelativeLayout)findViewById(R.id.inner_relative);
		
		
		Log.v("onC", "Created now executing");
		new MainActivity.ReadFiles().execute();
	}

	
	
	protected boolean tripValid(String trip) 
	{
		StringTokenizer st = new StringTokenizer(trip, ";");
		
		String s = st.nextToken();
		String d = st.nextToken();
		
		if(!s.equals(d))
		{
			source = s;
			destination = d;
			day_chosen = st.nextToken();
			return true;
		}
			
		return false;
	}


	private void crossfadeHomeScreen() {

	    // Set the content view to 0% opacity but visible, so that it is visible
	    // (but fully transparent) during the animation.
	    mRelativeLayout.setAlpha(0f);
	    mRelativeLayout.setVisibility(View.VISIBLE);

	    // Animate the content view to 100% opacity, and clear any animation
	    // listener set on the view.
	    mRelativeLayout.animate()
	            .alpha(1f)
	            .setDuration(mShortAnimationDuration)
	            .setListener(null);

	    // Animate the loading view to 0% opacity. After the animation ends,
	    // set its visibility to GONE as an optimization step (it won't
	    // participate in layout passes, etc.)
	    progress.animate()
	            .alpha(0f)
	            .setDuration(500)
	            .setListener(new AnimatorListenerAdapter() {
	                @Override
	                public void onAnimationEnd(Animator animation) {
	                    progress.setVisibility(View.GONE);
	                }
	            });
	}
	
	private class ReadFiles extends AsyncTask<Void, Void, Void>
	{

		@Override
		protected Void doInBackground(Void... params) 
		{
			Log.i("asdasd", "starting");
			try 
			{
				Log.v("doIB", "In do in Back");
				startReadingFiles();
			}
			catch (IOException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result) 
		{
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			Log.v("done", "done reading files");
			crossfadeHomeScreen();
			
			
		}
		
		private void startReadingFiles() throws IOException 
		{
			
			
			AssetManager asset = getAssets();
			
			

	        try 
	        {
	        	Log.v("read", "starting to read file");
	        	Scanner scanner = new Scanner(asset.open("Weekday_east.txt"));
	            
	            StringTokenizer st;
	            String current;    
	            String temp;
	            current = scanner.nextLine();
	            
	            int i = 0;
	            while(scanner.hasNext())
	            {
	                current = scanner.nextLine();
	                if(!current.trim().isEmpty() && !current.startsWith("DICKERSON"))
	                {
	                    st = new StringTokenizer(current);
	                
	                    while(st.hasMoreTokens())
	                    {
	                        
	                        temp = st.nextToken();
	                        if(i%3 == 0)
	                        {
	                            data.McC_eastbound.add(temp);
	                        }
	                        else if(i%3 == 1)
	                        {
	                            data.UTD_eastbound.add(temp);
	                        }
	                        else if(i%3 == 2)
	                        {
	                            data.PGT_eastbound.add(temp);
	                        }
	                        i++;
	                        
	                    }
	                    

	                }
	                
	            }
	            
	            scanner.close();
	            
	            
	            scanner = new Scanner(asset.open("Weekday_west.txt"));
	            
	            current = scanner.nextLine();

	            i = 0;
	            while(scanner.hasNext())
	            {
	                current = scanner.nextLine();
	                if(!current.trim().isEmpty() && !current.startsWith("BUSH"))
	                {
	                    st = new StringTokenizer(current);
	                
	                    while(st.hasMoreTokens())
	                    {
	                        
	                        temp = st.nextToken();
	                        if(i%3 == 0)
	                        {
	                            data.PGT_westbound.add(temp);
	                        }
	                        else if(i%3 == 1)
	                        {
	                            data.UTD_westbound.add(temp);
	                        }
	                        else if(i%3 == 2)
	                        {
	                            data.McC_westbound.add(temp);
	                        }
	                        i++;
	                        
	                    }
	                    

	                }
	                
	            }
	            
	            scanner.close();
	            
	           
	            scanner = new Scanner(asset.open("Saturday_east.txt"));
	            
	            current = scanner.nextLine();
	            
	            i = 0;
	            while(scanner.hasNext())
	            {
	                current = scanner.nextLine();
	                if(!current.trim().isEmpty() && !current.startsWith("DICKERSON"))
	                {
	                    st = new StringTokenizer(current);
	                
	                    while(st.hasMoreTokens())
	                    {
	                        
	                        temp = st.nextToken();
	                        if(i%3 == 0)
	                        {
	                            data.McC_eastbound_saturday.add(temp);
	                        }
	                        else if(i%3 == 1)
	                        {
	                            data.UTD_eastbound_saturday.add(temp);
	                        }
	                        else if(i%3 == 2)
	                        {
	                            data.PGT_eastbound_saturday.add(temp);
	                        }
	                        i++;
	                        
	                    }
	                    

	                }
	                
	            }
	            
	            scanner.close();
	            
	            scanner = new Scanner(asset.open("Saturday_west.txt"));
	            
	            current = scanner.nextLine();
	            
	            System.out.println(current);
	            i = 0;
	            while(scanner.hasNext())
	            {
	                current = scanner.nextLine();
	                if(!current.trim().isEmpty() && !current.startsWith("BUSH"))
	                {
	                    st = new StringTokenizer(current);
	                
	                    while(st.hasMoreTokens())
	                    {
	                        
	                        temp = st.nextToken();
	                        if(i%3 == 0)
	                        {
	                            data.PGT_westbound_saturday.add(temp);
	                        }
	                        else if(i%3 == 1)
	                        {
	                            data.UTD_westbound_saturday.add(temp);
	                        }
	                        else if(i%3 == 2)
	                        {
	                            data.McC_westbound_saturday.add(temp);
	                        }
	                        i++;
	                        
	                    }
	                    

	                }
	                
	            }
	            
	            scanner.close();
	            
	            System.out.println("");
	            
	            scanner = new Scanner(asset.open("Sunday_west.txt"));
	            
	            current = scanner.nextLine();
	            
	            System.out.println(current);
	            i = 0;
	            while(scanner.hasNext())
	            {
	                current = scanner.nextLine();
	                if(!current.trim().isEmpty() && !current.startsWith("BUSH"))
	                {
	                    st = new StringTokenizer(current);
	                
	                    while(st.hasMoreTokens())
	                    {
	                        
	                        temp = st.nextToken();
	                        if(i%4 == 0)
	                        {
	                        	
	                        	data.PGT_westbound_sunday.add(temp);
	                        }
	                        else if(i%4 == 1)
	                        {
	                            data.UTD_westbound_sunday.add(temp);
	                        }
	                        else if(i%4 == 2)
	                        {
	                            data.Walmart_Sunday.add(temp);
	                        }
	                        else if(i%4 == 3)
	                        {
	                            data.McC_westbound_sunday.add(temp);
	                        }
	                        i++;
	                        System.out.print(temp + " \t\t\t\t");
	                    }
	                    

	                    System.out.println("");
	                }
	                
	            }
	            
	            
	            scanner.close();

	            scanner = new Scanner(asset.open("Sunday_east.txt"));
	            
	            current = scanner.nextLine();
	            
	            i = 0;
	            while(scanner.hasNext())
	            {
	                current = scanner.nextLine();
	                if(!current.trim().isEmpty() && !current.startsWith("DICKERSON"))
	                {
	                    st = new StringTokenizer(current);
	                
	                    while(st.hasMoreTokens())
	                    {
	                        
	                        temp = st.nextToken();
	                        if(i%3 == 0)
	                        {
	                            data.McC_eastbound_sunday.add(temp);
	                        }
	                        else if(i%3 == 1)
	                        {
	                            data.UTD_eastbound_sunday.add(temp);
	                        }
	                        else if(i%3 == 2)
	                        {
	                        	Log.v("asd", "Adding data to pgt sunday appropriately: " + temp);
	                        	data.PGT_eastbound_sunday.add(temp);
	                        }
	                        i++;
	                        
	                    }
	                    
	                }
	                
	                
	            }
	            
	            scanner.close();
	            
	            
	        } 

	    	catch (FileNotFoundException ex) 
	        
	        {
	            Toast.makeText(getApplicationContext(), "File not found!", Toast.LENGTH_SHORT).show();
	        }
		    
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
