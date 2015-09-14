package com.example.cometrider;

import java.util.ArrayList;
import java.util.StringTokenizer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class SourceFragment extends Fragment 
{
	private DataStore data = MainActivity.data;
	private ImageView image;
	private ListView mListView_source;
	private TextView source_Text;
	private ArrayList<String> list_source = data.list1;
	private ScheduleActivityAdapter mAdapter_source;

    String source;
    String trip;
    String destination;
    StringTokenizer st;
    
    
	
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_source, container, false);
        
        image = (ImageView)rootView.findViewById(R.id.sourceImage);
		image.setImageResource(data.source_image);
        
        trip = data.trip;
        
        
        source_Text = (TextView)rootView.findViewById(R.id.stopSource);
        st = new StringTokenizer(trip, ";");
        source = st.nextToken();
		Log.v("trip", source);
		
        destination = st.nextToken();
		Log.v("trip", destination);
		
		source_Text.setText("Bus leaves " + source+ " at:"); 
		
		mListView_source = (ListView)rootView.findViewById(R.id.listViewSource);
		
        mAdapter_source = new ScheduleActivityAdapter(getActivity(), list_source);
        Log.v("asd", mListView_source.toString());
        Log.v("asd", mAdapter_source.toString());
        mListView_source.setAdapter(mAdapter_source);
        mListView_source.setOnItemClickListener(new AdapterView.OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> adapterView, View view, int position,
				long id) 
		{
			// TODO Auto-generated method stub
			String trip = adapterView.getAdapter().getItem(position).toString();
			Log.v("selected", "Selected time is " + trip);
			if(!trip.trim().equals("--"))
			{
				Intent intent = new Intent(getActivity(), ReminderActivity.class);
				intent.putExtra("time", trip);
				startActivity(intent);
				
			}
			else
				Toast.makeText(getActivity(), "Please choose a valid time!", Toast.LENGTH_SHORT).show();
			

		}});
		
		
        return rootView;
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
	        
	       
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}

	private void openTwitter(MenuItem item) 
	{
		// TODO Auto-generated method stub
		Intent twitter_intent = new Intent(getActivity().getApplicationContext(), DisplayTwitterActivity.class);
		
		startActivity(twitter_intent);
		getActivity().overridePendingTransition(R.anim.slide_down, R.anim.slide_up);
		
	}


	private void openMap() 
	{
		// TODO Auto-generated method stub
		Intent map_intent = new Intent(getActivity().getApplicationContext(), DisplayMapActivity.class);
		startActivity(map_intent);
		getActivity().overridePendingTransition(R.anim.slide_down, R.anim.slide_up);
		
	}
	
	
}
