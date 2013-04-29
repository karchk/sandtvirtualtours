package edu.mst.tours.util;

import android.content.Context;
import android.net.Uri;

import com.google.android.maps.GeoPoint;

import edu.mst.tours.model.Building;

public class MapsURLBuilder {
	
	private final static String GOOGLEMAPS_URL_PREFIX = "http://maps.google.com/maps?saddr=";
	private final static String GOOGLEMAPS_URL_TO_APPEND = "&daddr=";
	private static final String GOOGLEMAPS_URL_NEXTDESTINATION_APPEND = "%20to:";
	private final static String GOOGLEMAPS_URL_TYPE_APPEND = "&dirflg=w";
	
	public static Uri getFromPoint(float startLat, float startLng, float desLat, float desLng) {
		return Uri.parse(GOOGLEMAPS_URL_PREFIX + startLat
				+ ',' + startLng + GOOGLEMAPS_URL_TO_APPEND
				+ desLat + ',' + desLng + GOOGLEMAPS_URL_TYPE_APPEND);
	}
	
	public static Uri getTour(Context context, Building[] buildings){		
		String url = new String(GOOGLEMAPS_URL_PREFIX);
		url += getGeoPointString(buildings[0].getLocation());
		url += GOOGLEMAPS_URL_TO_APPEND;
		for (int i = 1; i < buildings.length; i++) {
			url += getGeoPointString(buildings[i].getLocation());
			if(i != buildings.length-1) url += GOOGLEMAPS_URL_NEXTDESTINATION_APPEND;
		}
		url += GOOGLEMAPS_URL_TYPE_APPEND;
		
		return Uri.parse(url);
	}
	
	private static String getGeoPointString(GeoPoint point){
		float lat = (float) (point.getLatitudeE6() / 1E6);
		float lng = (float) (point.getLongitudeE6() / 1E6);
		return lat + "," + lng;
	}

}
