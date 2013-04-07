package edu.mst.tours;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
//import android.widget.AdapterView;
//import android.widget.EditText;
//import android.widget.AdapterView.OnItemSelectedListener;
//import android.content.res.Resources;

public class FindActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_find);
		// Show the Up button in the action bar.
		setupActionBar();
		Spinner spinner = (Spinner) findViewById(R.id.depsel);
		// Create an ArrayAdapter using the string array and a default spinner layout
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
		        R.array.departments, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {  
			  public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {  
				// hide selection text  
				// if you want you can change background here  
				Resources r = getResources();
				String[] adapter = r.getStringArray(R.array.building_options);
				TextView tv = (TextView) findViewById(R.id.Building);
				if(position == 0)
				{
					//cs
					tv.setText(adapter[0]);
				}
				else if(position == 1 || position == 2)
				{
					//havener
					tv.setText(adapter[1]);
				}
				else if(position > 2 && position < 5)
				{
					//toomey
					tv.setText(adapter[4]);
				}
				else if(position == 5)
				{
					//library
					tv.setText(adapter[2]);
				}
				else if(position >= 5)
				{
					//parker
					tv.setText(adapter[3]);
				}
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
		getMenuInflater().inflate(R.menu.find, menu);
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
	
	public void Building_Selected(View view) {
		Intent intent = new Intent(this, DirectionsActivity.class);
		TextView tv = (TextView) findViewById(R.id.Building);
		String value = tv.toString();
		intent.putExtra("Building", value);
    	startActivity(intent);
	}

}
