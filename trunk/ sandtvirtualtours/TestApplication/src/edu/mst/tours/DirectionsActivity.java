package edu.mst.tours;

import java.util.HashSet;
import java.util.Iterator;

import edu.mst.tours.model.Building;
import edu.mst.tours.parsers.LocationsParser;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class DirectionsActivity extends Activity {

	private final static String GOOGLEMAPS_URL_PREFIX = "http://maps.google.com/maps?saddr=";
	private final static String GOOGLEMAPS_URL_TO_APPEND = "&daddr=";
	private final static String GOOGLEMAPS_URL_TYPE_APPEND = "&dirflg=w";
	
	private Button bt_getdirections, bt_finddepartment;
	private Spinner sp_from, sp_to;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.directionsactivity);
		loadViews();
	}
	
	private void loadViews() {
		bt_getdirections = (Button) findViewById(R.directionsactivity.bt_getdirections);
		bt_finddepartment = (Button) findViewById(R.directionsactivity.bt_finddepartment);
		sp_from = (Spinner) findViewById(R.directionsactivity.sp_from);
		sp_to = (Spinner) findViewById(R.directionsactivity.sp_to);
		
		Intent loadIntent = getIntent();
		boolean isStart = loadIntent.getBooleanExtra(FindActivity.START_ADDR, false);
		String bname = loadIntent.getStringExtra(FindActivity.BUILDING_NAME);
		if(isStart && bname != null)
		{
			int position = 0;
			String[] adapter = getResources().getStringArray(R.array.building_options);
			for(int i = 0; i < adapter.length;  i++)
			{
				if(adapter[i].equals(bname))
				{
					position = i;
					break;
				}
			}
			sp_from.setSelection(position);
		}
		else if(!isStart && bname != null)
		{
			int position = 0;
			String[] adapter = getResources().getStringArray(R.array.building_options);
			for(int i = 0; i < adapter.length;  i++)
			{
				if(adapter[i].equals(bname))
				{
					position = i;
					break;
				}
			}
			sp_to.setSelection(position);
		}
		
		bt_getdirections.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String from = sp_from.getSelectedItem().toString();
				String to = sp_to.getSelectedItem().toString();
				LocationsParser l = new LocationsParser();
		        HashSet<Building> buildings = l.getBuildings(v.getContext());
		        Iterator<Building> i = buildings.iterator();
		        float startlat = 0, startlng = 0, deslat = 0, deslng = 0;
		        while(i.hasNext())
		        {
		        	Building cur = i.next();
		        	if(cur.getName().equals(from))
		        	{
		        		startlat = (float) (cur.getLocation().getLatitudeE6() / 1E6);
		        		startlng = (float) (cur.getLocation().getLongitudeE6() / 1E6);
		        	}
		        	else if(cur.getName().equals(to))
		        	{
		        		deslat = (float) (cur.getLocation().getLatitudeE6() / 1E6);
		        		deslng = (float) (cur.getLocation().getLongitudeE6() / 1E6);
		        	}
		        }
				Intent intent = new Intent(android.content.Intent.ACTION_VIEW, 
						Uri.parse(GOOGLEMAPS_URL_PREFIX + startlat + ',' + startlng + GOOGLEMAPS_URL_TO_APPEND + deslat + ',' + deslng + GOOGLEMAPS_URL_TYPE_APPEND));
				startActivity(intent);
			}
		});
		
		bt_finddepartment.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), FindActivity.class);
		        startActivity(intent);
			}
		});
	}
}