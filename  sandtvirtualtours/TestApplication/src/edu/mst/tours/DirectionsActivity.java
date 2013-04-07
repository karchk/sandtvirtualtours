package edu.mst.tours;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class DirectionsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_directions);
		// Show the Up button in the action bar.
		setupActionBar();
		Intent intent = getIntent();
		String message = intent.getStringExtra("Building");
		if(message != null)
		{
			
		}
		Spinner spinner = (Spinner) findViewById(R.id.bldsel1);
		// Create an ArrayAdapter using the string array and a default spinner layout
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
		        R.array.building_options, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		spinner.setAdapter(adapter);
		Spinner spinner2 = (Spinner) findViewById(R.id.bldsel2);
		spinner2.setAdapter(adapter);
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {  
		  public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {  
			// hide selection text  
			((TextView)view).setText(null);  
			// if you want you can change background here 
			Resources r = getResources();
			String[] adapter = r.getStringArray(R.array.building_locs);
			EditText box1 = (EditText) findViewById(R.id.start_location);
			box1.setText(adapter[position]);
		  }  
		  public void onNothingSelected(AdapterView<?> arg0) {}  
		 });
		spinner2.setOnItemSelectedListener(new OnItemSelectedListener() {  
			  public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {  
				// hide selection text  
				((TextView)view).setText(null);  
				// if you want you can change background here 
				Resources r = getResources();
				String[] adapter = r.getStringArray(R.array.building_locs);
				EditText box1 = (EditText) findViewById(R.id.end_location);
				box1.setText(adapter[position]);
			  }  
			  public void onNothingSelected(AdapterView<?> arg0) {}  
			 });
	}
	
	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.directions, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void QueryDirections(View view) {
		// do nothing for now
		EditText begBox = (EditText) findViewById(R.id.start_location);
		EditText endBox = (EditText) findViewById(R.id.end_location);
		Intent intent = new Intent(android.content.Intent.ACTION_VIEW, 
			    Uri.parse("http://maps.google.com/maps?saddr=" + begBox.getText() + "&daddr=" + endBox.getText()));
			startActivity(intent);
	}
	
	public void FindBuilding(View view) {
		Intent intent = new Intent(this, FindActivity.class);
    	startActivity(intent);
	}

}
