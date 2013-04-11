package edu.mst.tours;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Spinner;

public class DirectionsActivity extends Activity {

	private final static String GOOGLEMAPS_URL_PREFIX = "http://maps.google.com/maps?saddr=";
	private final static String GOOGLEMAPS_URL_TO_APPEND = "&daddr=";
	
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
		
		bt_getdirections.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String from = sp_from.getSelectedItem().toString();
				String to = sp_to.getSelectedItem().toString();
				Intent intent = new Intent(android.content.Intent.ACTION_VIEW, 
					    Uri.parse(GOOGLEMAPS_URL_PREFIX + from + GOOGLEMAPS_URL_TO_APPEND + to));
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