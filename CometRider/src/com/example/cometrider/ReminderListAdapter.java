package com.example.cometrider;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ReminderListAdapter extends ArrayAdapter<Reminder> 
{

	public ReminderListAdapter(Context context, List<Reminder> objects) 
	{
		super(context, 0, objects);
		// TODO Auto-generated constructor stub
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		Holder holder;
		
		if (view == null) {
			// Create a new view, inflating it from an XML resource.
			// Don't attach to parent, because the ListView will do that for us
			view = LayoutInflater.from(getContext()).inflate(R.layout.reminder_item, parent, false);
			
			// This is called the Holder pattern.  
			// It makes it easier and faster to re-find views in a list item later
			holder = new Holder();
			holder.reminderTime = (TextView) view.findViewById(R.id.reminder_time);
			holder.tripInfo = (TextView)view.findViewById(R.id.trip_info);
			
			view.setTag(holder);
			
		} else {
			holder = (Holder) view.getTag();
		}
		
		// Get the check from the data list the Adapter is holding on to
		Reminder reminder = getItem(position);
		
		// Set the content of the list item view
		
		
		
		holder.reminderTime.setText(reminder.getBus_departure_time());
		String trip = reminder.getBus_departure_time() + ", " + reminder.getDay() + ", " + reminder.getSource() + " to " + reminder.getDestination();
		
		holder.tripInfo.setText(trip);
			
		
		// Return the view to the ListView that asked for it
		return view;
	}
		
	public static class Holder 
	{
		TextView reminderTime;
		TextView tripInfo;
		
		
	}	
		
}
