package com.example.cometrider;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


public class ScheduleActivityAdapter extends ArrayAdapter<String> 
{

	public ScheduleActivityAdapter(Context context, List<String> objects) 
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
			view = LayoutInflater.from(getContext()).inflate(R.layout.item_table, parent, false);
			
			// This is called the Holder pattern.  
			// It makes it easier and faster to re-find views in a list item later
			holder = new Holder();
			holder.timeChosen = (TextView) view.findViewById(R.id.bus_time);
			
			view.setTag(holder);
			
		} else {
			holder = (Holder) view.getTag();
		}
		
		// Get the check from the data list the Adapter is holding on to
		String time = getItem(position);
		
		// Set the content of the list item view
		
		
		
		holder.timeChosen.setText(time);
		
		
			
		
		// Return the view to the ListView that asked for it
		return view;
	}
		
	public static class Holder {
		TextView timeChosen;
		
		
	}	
		
	
	
}
