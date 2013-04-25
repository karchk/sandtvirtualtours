package edu.mst.tours;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff.Mode;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;
import edu.mst.tours.model.Building;
import edu.mst.tours.parsers.BuildingsParser;

public class DirectionsActivity extends Activity {

	private final static String GOOGLEMAPS_URL_PREFIX = "http://maps.google.com/maps?saddr=";
	private final static String GOOGLEMAPS_URL_TO_APPEND = "&daddr=";
	private final static String GOOGLEMAPS_URL_TYPE_APPEND = "&dirflg=w";
	
	private final static Integer REQUESTCODE_FINDDEPARTMENT = 1;

	private HashMap<String, Building> buildings;

	private Button bt_getdirections, bt_finddepartment;
	private ImageButton bt_cur;
	private Spinner sp_from, sp_to;
	
	private boolean useCur = false;

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
		bt_cur = (ImageButton) findViewById(R.directionsactivity.bt_cur);
		ArrayAdapter<String> spinnersAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, new ArrayList<String>(buildings.keySet()));
		spinnersAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp_from = (Spinner) findViewById(R.directionsactivity.sp_from);
		sp_to = (Spinner) findViewById(R.directionsactivity.sp_to);
		sp_from.setAdapter(spinnersAdapter);
		sp_to.setAdapter(spinnersAdapter);

		bt_cur.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				useCur = !useCur;
				int toast = !useCur ? R.string.directionsactivity_nocurrentlocation : R.string.directionsactivity_usecurrentlocation;
				if(useCur) bt_cur.getBackground().setColorFilter(Color.CYAN, Mode.SRC_ATOP);
				else bt_cur.getBackground().setColorFilter(null);
				Toast.makeText(v.getContext(), toast, Toast.LENGTH_SHORT).show();
				sp_from.setEnabled(!useCur);
			}
		});
		
		bt_getdirections.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String from = sp_from.getSelectedItem().toString();
				String to = sp_to.getSelectedItem().toString();
				if(from.equals(to) && !useCur){
					Toast.makeText(v.getContext(),
							"Your starting point is the same as the target.\nPlease change one of them.", Toast.LENGTH_SHORT).show();
				}else{
					Building buildingFrom = buildings.get(from);
					Building buildingTo = buildings.get(to);
					float startlat = (float) (buildingFrom.getLocation().getLatitudeE6() / 1E6);
					float startlng = (float) (buildingFrom.getLocation().getLongitudeE6() / 1E6);
					if(useCur) {
						Criteria locCrit = new Criteria();
						locCrit.setAccuracy(Criteria.ACCURACY_FINE);
						locCrit.setAltitudeRequired(false);
						locCrit.setBearingRequired(false);
						locCrit.setCostAllowed(false);
						locCrit.setPowerRequirement(Criteria.POWER_LOW);
						locCrit.setSpeedRequired(false);
						
						LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
						String bProvider = lm.getBestProvider(locCrit, true);
						
						Location cur = lm.getLastKnownLocation(bProvider); 
						startlat = (float) (cur.getLatitude());
						startlng = (float) (cur.getLongitude());
					}
					
					float deslat = (float) (buildingTo.getLocation().getLatitudeE6() / 1E6);
					float deslng = (float) (buildingTo.getLocation().getLongitudeE6() / 1E6);

					Intent intent = new Intent(android.content.Intent.ACTION_VIEW, 
							Uri.parse(GOOGLEMAPS_URL_PREFIX + startlat
									+ ',' + startlng + GOOGLEMAPS_URL_TO_APPEND
									+ deslat + ',' + deslng + GOOGLEMAPS_URL_TYPE_APPEND));
					startActivity(intent);
				}
			}
		});

		bt_finddepartment.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), FindActivity.class);
				startActivityForResult(intent, REQUESTCODE_FINDDEPARTMENT);
			}
		});
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == REQUESTCODE_FINDDEPARTMENT) {
			if(resultCode == RESULT_OK){
				boolean isStart = data.getBooleanExtra(FindActivity.START_ADDR, false);
				String bName = data.getStringExtra(FindActivity.BUILDING_NAME);
				
				int position = 0;
				if(bName != null){
					Object[] adapter = buildings.keySet().toArray();					
					position = getArrayPositionFromName(bName, adapter);
				}
				
				if(isStart) sp_from.setSelection(position);
				else sp_to.setSelection(position);
			}
		}
	}

	private int getArrayPositionFromName(String name, Object[] array) {
		int position = 0;
		for(int i = 0; i < array.length;  i++) {
			if(array[i].toString().equals(name)) {
				position = i;
				break;
			}
		}
		return position;
	}
}