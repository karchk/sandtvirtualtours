package edu.mst.tours;

import java.util.HashSet;
import java.util.Iterator;

import edu.mst.tours.model.Building;
import edu.mst.tours.parsers.LocationsParser;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class FindActivity extends Activity {

	private Spinner sp_departmentselector;
	private TextView tv_building;
	private Button bt_selectstartbuilding;
	private Button bt_selectendbuilding;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.findactivity);
		
		loadViews();
		
		sp_departmentselector.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				String[] adapter = getResources().getStringArray(R.array.building_options);
				int indexToGet = 0;
				if(position == 0) indexToGet = 0; //cs
				else if(position == 1 || position == 2) indexToGet = 1; //havener
				else if(position > 2 && position < 5) indexToGet = 4; //toomey
				else if(position == 5) indexToGet = 2; //library
				else if(position >= 5) indexToGet = 3; //parker
				
				tv_building.setText(adapter[indexToGet].toString());
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) { }
		});
	}

	private void loadViews() {
		sp_departmentselector = (Spinner) findViewById(R.findactivity.sp_departmentselector);
		tv_building = (TextView) findViewById(R.findactivity.tv_building);
		bt_selectstartbuilding = (Button) findViewById(R.findactivity.bt_selectstartbuilding);
		bt_selectendbuilding = (Button) findViewById(R.findactivity.bt_selectendbuildling);
		
		bt_selectstartbuilding.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
			}
		});
		bt_selectendbuilding.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
			}
		});
	}
}
