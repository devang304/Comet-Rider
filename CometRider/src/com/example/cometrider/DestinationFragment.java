package com.example.cometrider;

import java.util.ArrayList;
import java.util.StringTokenizer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class DestinationFragment extends Fragment 
{
	private ImageView image;
	private ListView mListView_destination;
	private DataStore data = MainActivity.data;
	private ArrayList<String> list_destination = data.list2;
	private ScheduleActivityAdapter mAdapter_destination;
	private TextView destination_Text;

	String source;
    String trip;
    String destination;
    StringTokenizer st;
	
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_destination, container, false);
        

		image = (ImageView)rootView.findViewById(R.id.destinationImage);
		image.setImageResource(data.destination_image);
		trip = data.trip;
        
        st = new StringTokenizer(trip, ";");
        source = st.nextToken();
		Log.v("trip", source);
		
        destination = st.nextToken();
		Log.v("trip", destination);
		
		
		
		destination_Text = (TextView)rootView.findViewById(R.id.stopDestination);
		destination_Text.setText("Bus reaches " + destination + " at:"); 
		mListView_destination = (ListView)rootView.findViewById(R.id.listViewDestination);
		mAdapter_destination = new ScheduleActivityAdapter(getActivity(), list_destination);
		mListView_destination.setAdapter(mAdapter_destination);
		
		mListView_destination.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int position,
					long id)
				{
					// TODO Auto-generated method stub
					String trip = (String)adapterView.getAdapter().getItem(position).toString();
					if(!trip.trim().equals("--"))
					{
						Intent intent = new Intent(getActivity(), ReminderActivity.class);
						intent.putExtra("time", trip);
						startActivity(intent);
						
					}
					else
						Toast.makeText(getActivity(), "Please choose a valid time!", Toast.LENGTH_SHORT).show();
					

				}
			});
        
		
        return rootView;
    }
	
	
}
