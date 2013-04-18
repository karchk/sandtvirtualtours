package edu.mst.tours;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import edu.mst.tours.model.Building;
import edu.mst.tours.parsers.BuildingsParser;

public class DirectionsActivity extends Activity {

	private final static String GOOGLEMAPS_URL_PREFIX = "http://maps.google.com/maps?saddr=";
	private final static String GOOGLEMAPS_URL_TO_APPEND = "&daddr=";
	private final static String GOOGLEMAPS_URL_TYPE_APPEND = "&dirflg=w";
	
	private HashMap<String, Building> buildings;

	private Button bt_getdirections, bt_finddepartment;
	private Spinner sp_from, sp_to;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.directionsactivity);
		buildings = BuildingsParser.getBuildings(this);
		
		loadViews();
	}
	
	private void loadViews() {
		bt_getdirections = (Button) findViewById(R.directionsactivity.bt_getdirections);
		bt_finddepartment = (Button) findViewById(R.directionsactivity.bt_finddepartment);
		ArrayAdapter<String> spinnersAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, new ArrayList<String>(buildings.keySet()));
		spinnersAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp_from = (Spinner) findViewById(R.directionsactivity.sp_from);
		sp_to = (Spinner) findViewById(R.directionsactivity.sp_to);
		sp_from.setAdapter(spinnersAdapter);
		sp_to.setAdapter(spinnersAdapter);
		
		bt_getdirections.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String from = sp_from.getSelectedItem().toString();
				String to = sp_to.getSelectedItem().toString();
				HashMap<String, Building> buildings = BuildingsParser.getBuildings(v.getContext());
		        Iterator<Building> i = buildings.values().iterator();
		        float startlat = 0, startlng = 0, deslat = 0, deslng = 0;
		        while(i.hasNext()) {
		        	Building cur = i.next();
		        	if(cur.getName().equals(from)) {
		        		startlat = (float) (cur.getLocation().getLatitudeE6() / 1E6);
		        		startlng = (float) (cur.getLocation().getLongitudeE6() / 1E6);
		        	} else if(cur.getName().equals(to)) {
		        		deslat = (float) (cur.getLocation().getLatitudeE6() / 1E6);
		        		deslng = (float) (cur.getLocation().getLongitudeE6() / 1E6);
		        	}
		        }
		        Intent intent = new Intent(android.content.Intent.ACTION_VIEW, 
						Uri.parse(GOOGLEMAPS_URL_PREFIX + startlat
								+ ',' + startlng + GOOGLEMAPS_URL_TO_APPEND
								+ deslat + ',' + deslng + GOOGLEMAPS_URL_TYPE_APPEND));
				startActivity(intent);
			}
		});
		
		bt_finddepartment.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), FindActivity.class);
		        startActivityForResult(intent,1);
			}
		});
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		  if (requestCode == 1) {
		     if(resultCode == RESULT_OK){      
		    	 //Intent data = getIntent();
		 		 boolean isStart = data.getBooleanExtra(FindActivity.START_ADDR, false);
		 		 String bname = data.getStringExtra(FindActivity.BUILDING_NAME);
		 		 Object[] adapter = buildings.keySet().toArray();
		 		 if(isStart && bname != null) {
		 			int position = 0;
		 			for(int i = 0; i < adapter.length;  i++) {
		 				if(adapter[i].equals(bname)) {
		 					position = i;
		 					break;
		 				}
		 			 }
		 			sp_from.setSelection(position);
		 		 } else if(!isStart && bname != null) {
		 			int position = 0;
		 			for(int i = 0; i < adapter.length;  i++) {
		 				if(adapter[i].equals(bname)) {
		 					position = i;
		 					break;
		 				}
		 			}
		 			sp_to.setSelection(position);
		 			}        
		     	}
		  }
		     if (resultCode == RESULT_CANCELED) {    
		         //Write your code if there's no result
		     }
		  }
}