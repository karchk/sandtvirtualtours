package edu.mst.tours.model;

import com.google.android.maps;

public class Building {
	
	private GeoPoint location;
	private String name;
	
	public GeoPoint getLocation() {
		return location;
	}
	
	public void setLocation(GeoPoint location) {
		this.location = location;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

}
