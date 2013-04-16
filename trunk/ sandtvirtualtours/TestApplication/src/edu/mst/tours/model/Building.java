package edu.mst.tours.model;

import android.graphics.Bitmap;

import com.google.android.maps.GeoPoint;

public class Building implements Comparable<Building> {
	
	private GeoPoint location;
	private String name;
	private String imageURL;
	private Bitmap image;
	
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

	public String getImageURL() {
		return imageURL;
	}
	
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
	
	public Bitmap getImage() {
		return image;
	}
	
	public void setImage(Bitmap image) {
		this.image = image;
	}

	@Override
	public int compareTo(Building other) {
		return getName().compareTo(other.getName());
	}
}
