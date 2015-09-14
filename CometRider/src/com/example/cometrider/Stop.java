package com.example.cometrider;

import java.util.HashMap;

public class Stop 
{
	private String name;
	private HashMap<String, Stop> eastbound = new HashMap<String, Stop>();
	private HashMap<String, Stop> westbound = new HashMap<String, Stop>();
	
	public Stop(String name) 
	{
		this.name = name;
	}
	
	public String getName() 
	{
		return name;
	}
	
	public void setName(String name) 
	{
		this.name = name;
	}

	public HashMap<String, Stop> getEastboundMap() {
		return eastbound;
	}

	public void setEastboundMap(HashMap<String, Stop> eastbound) {
		this.eastbound = eastbound;
	}

	public HashMap<String, Stop> getWestboundMap() {
		return westbound;
	}

	public void setWestboundMap(HashMap<String, Stop> westbound) {
		this.westbound = westbound;
	}
	
	public void addWestboundStop(Stop s)
	{
		this.getWestboundMap().put(s.name, s);
		
	}
	
	public void addEastboundStop(Stop s)
	{
		this.getEastboundMap().put(s.name, s);
		
	}
	
	
	
	
	
}
