package com.example.cometrider;

import java.util.ArrayList;
import java.util.HashMap;

import android.util.Log;

public class DataStore 
{
	
	final String PGT = "President George Bush Turnpike";
	final String McC = "McCallum";
	final String Walmart = "Walmart";
	final String UTD = "UTD Berkner";
	
	
	ArrayList<String> list1;
	ArrayList<String> list2;
	
	ArrayList<Reminder> reminderList = new ArrayList<Reminder>();
	
	
	ArrayList<String> McC_eastbound = new ArrayList<String>();
    ArrayList<String> UTD_eastbound = new ArrayList<String>();
    ArrayList<String> PGT_eastbound = new ArrayList<String>();
    
    
    ArrayList<String> McC_westbound = new ArrayList<String>();
    ArrayList<String> UTD_westbound = new ArrayList<String>();
    ArrayList<String> PGT_westbound = new ArrayList<String>();
    
    
    ArrayList<String> McC_eastbound_saturday = new ArrayList<String>();
    ArrayList<String> UTD_eastbound_saturday = new ArrayList<String>();
    ArrayList<String> PGT_eastbound_saturday = new ArrayList<String>();
    
    
    ArrayList<String> McC_westbound_saturday = new ArrayList<String>();
    ArrayList<String> UTD_westbound_saturday = new ArrayList<String>();
    ArrayList<String> PGT_westbound_saturday = new ArrayList<String>();
    
    ArrayList<String> McC_eastbound_sunday = new ArrayList<String>();
    ArrayList<String> UTD_eastbound_sunday = new ArrayList<String>();
    ArrayList<String> PGT_eastbound_sunday = new ArrayList<String>();
    
    
    ArrayList<String> McC_westbound_sunday = new ArrayList<String>();
    ArrayList<String> UTD_westbound_sunday = new ArrayList<String>();
    ArrayList<String> PGT_westbound_sunday = new ArrayList<String>();
    
    ArrayList<String> Walmart_Sunday = new ArrayList<String>();
    
    int source_image;
    int destination_image;
    
    String source;
    String destination;
    String trip;
    String day;
    
    
    
    static HashMap<String, Stop> stops = new HashMap<String, Stop>();
    
    

	public DataStore(String[] stringArray) 
	{
		for (int i = 0; i < stringArray.length; i++) 
		{
			stops.put(stringArray[i], new Stop(stringArray[i]));
		}
		
		Log.v("asd", "Setting stops");
		stops.get(PGT).addWestboundStop(new Stop(UTD));
		stops.get(PGT).addWestboundStop(new Stop(Walmart));
		stops.get(PGT).addWestboundStop(new Stop(McC));
		
		stops.get(UTD).addWestboundStop(new Stop(McC));
		stops.get(UTD).addWestboundStop(new Stop(Walmart));
		stops.get(UTD).addEastboundStop(new Stop(PGT));
		
		stops.get(McC).addEastboundStop(new Stop(PGT));
		stops.get(McC).addEastboundStop(new Stop(Walmart));
		stops.get(McC).addEastboundStop(new Stop(UTD));
		
		stops.get(Walmart).addEastboundStop(new Stop(PGT));
		stops.get(Walmart).addEastboundStop(new Stop(UTD));
		stops.get(Walmart).addWestboundStop(new Stop(McC));
		
		Log.v("sad", "Done Setting up stops");
		
		
	}
	
	public boolean valid(String s, String d, String day)
	{
		if(stops.get(s).getEastboundMap().containsKey(d) &&  stops.get(d).getWestboundMap().containsKey(s))
		{
			return evaluateLists(s, d, day, "east");
		}
		else if (stops.get(s).getWestboundMap().containsKey(d) &&  stops.get(d).getEastboundMap().containsKey(s))
		{
			return evaluateLists(s, d, day, "west");
		}
		else
		{
			return false;
			
		}
		
	}

	private boolean evaluateLists(String s, String d, String day, String bound) 
	{
		this.day = day;
		this.destination = d;
		this.source = s;
		if(s.equals(UTD))
		{
			if (bound.equals("east"))
			{
				source_image = R.drawable.utd_east;
				Log.v("asd", "" + source_image);
				if (day.equals("Sunday")) 
				{
					list1 = this.UTD_eastbound_sunday;
					
					return evaluateDestination(s,d,day,"east");
					
				}
				else if (day.equals("Saturday")) 
				{
					list1 = this.UTD_eastbound_saturday;
					return evaluateDestination(s,d,day,"east");
				}
				else
				{
					list1 = UTD_eastbound;
					return evaluateDestination(s,d,day,"east");
					
				}
				
				
			}
			else if (bound.equals("west")) 
			{
				source_image = R.drawable.utd_west;
				Log.v("asd", "" + source_image);
				if (day.equals("Sunday")) 
				{
					list1 = UTD_westbound_sunday;
					return evaluateDestination(s,d,day,"west");
				}
				else if (day.equals("Saturday")) 
				{
					list1 = UTD_westbound_saturday;
					return evaluateDestination(s,d,day,"west");
				}
				else
				{
					list1 = UTD_westbound;
					return evaluateDestination(s,d,day,"west");
					
				}
				
			}
			
			
		}
		
		else if(s.equals(PGT))
		{
			source_image = R.drawable.pgt_stop;
			if (bound.equals("east"))
			{
				if (day.equals("Sunday")) 
				{
					list1 = this.PGT_eastbound_sunday;
					
					return evaluateDestination(s,d,day,"east");
					
				}
				else if (day.equals("Saturday")) 
				{
					list1 = this.PGT_eastbound_saturday;
					return evaluateDestination(s,d,day,"east");
				}
				else
				{
					list1 = PGT_eastbound;
					return evaluateDestination(s,d,day,"east");
					
				}
				
				
			}
			else if (bound.equals("west")) 
			{
				if (day.equals("Sunday")) 
				{
					list1 = PGT_westbound_sunday;
					return evaluateDestination(s,d,day,"west");
				}
				else if (day.equals("Saturday")) 
				{
					list1 = PGT_westbound_saturday;
					return evaluateDestination(s,d,day,"west");
				}
				else
				{
					list1 = PGT_westbound;
					return evaluateDestination(s,d,day,"west");
					
				}
				
			}
			
			
		}
		
		else if(s.equals(McC))
		{
			source_image = R.drawable.mcc_stop;
			if (bound.equals("east"))
			{
				if (day.equals("Sunday")) 
				{
					list1 = this.McC_eastbound_sunday;
					
					return evaluateDestination(s,d,day,"east");
					
				}
				else if (day.equals("Saturday")) 
				{
					list1 = this.McC_eastbound_saturday;
					return evaluateDestination(s,d,day,"east");
				}
				else
				{
					list1 = McC_eastbound;
					return evaluateDestination(s,d,day,"east");
					
				}
				
				
			}
			else if (bound.equals("west")) 
			{
				if (day.equals("Sunday")) 
				{
					list1 = McC_westbound_sunday;
					return evaluateDestination(s,d,day,"west");
				}
				else if (day.equals("Saturday")) 
				{
					list1 = McC_westbound_saturday;
					return evaluateDestination(s,d,day,"west");
				}
				else
				{
					list1 = McC_westbound;
					return evaluateDestination(s,d,day,"west");
					
				}
				
			}
			
			
		}
		
		else if(s.equals(Walmart))
		{
			source_image = R.drawable.walmart;
			if (bound.equals("east"))
			{
				if (day.equals("Sunday")) 
				{
					list1 = this.Walmart_Sunday;
					
					return evaluateDestination(s,d,day,"east");
					
				}
				else 
					return false;
				
				
			}
			else if (bound.equals("west")) 
			{
				if (day.equals("Sunday")) 
				{
					list1 = this.Walmart_Sunday;
					
					return evaluateDestination(s,d,day,"east");
					
				}
				else 
					return false;
				
			}
			
			
		}
		
		
		return false;
		
	}

	private boolean evaluateDestination(String s, String d, String day, String bound)
	{
		if(d.equals(PGT))
		{
			destination_image = R.drawable.pgt_stop;
			if (bound.equals("east")) 
			{
				if (day.equals("Sunday")) 
				{
					Log.v("asd", "Adding appropriately to list2");
					list2 = this.PGT_eastbound_sunday;
					return true;
				}
				else if (day.equals("Saturday")) 
				{
					list2 = this.PGT_eastbound_saturday;
					return true;
				}
				else
				{
					list2 = this.PGT_eastbound;
					return true;
					
				}
				
				
			} 
			else if (bound.equals("west")) 
			{
				if (day.equals("Sunday")) 
				{
					list2 = PGT_westbound_sunday;
					return true;

				}
				else if (day.equals("Saturday")) 
				{
					list2 = PGT_westbound_saturday;
					return true;

				}
				else
				{
					list2 = PGT_westbound;
					return true;

					
				}
				
			}
			
		}
		else if(d.equals(McC))
		{
			destination_image = R.drawable.mcc_stop;
			if (bound.equals("east")) 
			{
				if (day.equals("Sunday")) 
				{
					Log.v("asd", "Adding appropriately to list2");
					list2 = this.McC_eastbound_sunday;
					return true;
				}
				else if (day.equals("Saturday")) 
				{
					list2 = this.McC_eastbound_saturday;
					return true;
				}
				else
				{
					list2 = this.McC_eastbound;
					return true;
					
				}
				
				
			} 
			else if (bound.equals("west")) 
			{
				if (day.equals("Sunday")) 
				{
					list2 = McC_westbound_sunday;
					return true;

				}
				else if (day.equals("Saturday")) 
				{
					list2 = McC_westbound_saturday;
					return true;

				}
				else
				{
					list2 = McC_westbound;
					return true;

					
				}
				
			}
			
		}
		else if(d.equals(Walmart))
		{
			destination_image = R.drawable.walmart;
			if (bound.equals("east")) 
			{
				if (day.equals("Sunday")) 
				{
					list2 = Walmart_Sunday;
					return true;
				}
				else 
					return false;
			} 
			else if (bound.equals("west")) 
			{
				if (day.equals("Sunday")) 
				{
					list2 = Walmart_Sunday;
					return true;
				}
				else 
					return false;
				
			}
			
		}
		else if(d.equals(UTD))
		{
			if (bound.equals("east")) 
			{
				destination_image = R.drawable.utd_east;
				if (day.equals("Sunday")) 
				{
					Log.v("asd", "Adding appropriately to list2");
					list2 = this.UTD_eastbound_sunday;
					return true;
				}
				else if (day.equals("Saturday")) 
				{
					list2 = this.UTD_eastbound_saturday;
					return true;
				}
				else
				{
					list2 = this.UTD_eastbound;
					return true;
					
				}
				
				
			} 
			else if (bound.equals("west")) 
			{
				destination_image = R.drawable.utd_west;

				if (day.equals("Sunday")) 
				{
					list2 = UTD_westbound_sunday;
					return true;

				}
				else if (day.equals("Saturday")) 
				{
					list2 = UTD_westbound_saturday;
					return true;

				}
				else
				{
					list2 = UTD_westbound;
					return true;

				}
				
			}
			
		}
		return false;
	
	}

	
}
