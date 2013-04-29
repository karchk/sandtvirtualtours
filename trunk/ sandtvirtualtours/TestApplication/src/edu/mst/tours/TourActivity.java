package edu.mst.tours;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import edu.mst.tours.adapters.TourListAdapter;
import edu.mst.tours.model.Building;
import edu.mst.tours.parsers.BuildingsParser;
import edu.mst.tours.util.MapsURLBuilder;
import edu.mst.tours.view.DDListView;
import edu.mst.tours.view.DDListView.DropListener;

public class TourActivity extends ListActivity {

	private HashMap<String, Building> buildings;
	private DDListView listView;
	private Button bt_starttour;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.touractivity);
		buildings = BuildingsParser.getBuildings(this);
		
		listView = (DDListView) getListView();
		bt_starttour = (Button) findViewById(R.touractivity.bt_starttour);
		
		listView.setOnCreateContextMenuListener(this);
		listView.setDropListener(new DropListener() {
			@Override
			public void drop(int from, int to) {
				((TourListAdapter) listView.getAdapter()).setDropChange(from, to);
			}
		});
        ArrayList<Building> arrBuildings = new ArrayList<Building>();
        for (Building building : buildings.values()) {
			arrBuildings.add(building);
		}
        listView.setAdapter(new TourListAdapter(this, listView, arrBuildings));
        
        bt_starttour.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				ArrayList<Building> list = ((TourListAdapter) listView.getAdapter()).getOrderedList();
				Log.e(MainActivity.TAG, list.toString());
				
				if(list.size() > 0){					
					Uri uri = MapsURLBuilder.getTour(v.getContext(), list.toArray(new Building[list.size()]));
					Log.d(MainActivity.TAG, "Mapping to " + uri.toString());
					Intent intent = new Intent(android.content.Intent.ACTION_VIEW, 
							uri);
					startActivity(intent);
				}else{
					Toast.makeText(v.getContext(), R.string.touractivity_selectatleastone, Toast.LENGTH_SHORT).show();
				}
			}
		});
	}
}