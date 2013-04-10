package edu.mst.tours;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
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
	
	public void QueryDirections(View view) {
		// do nothing for now
		EditText begBox = (EditText) findViewById(R.id.start_location);
		EditText endBox = (EditText) findViewById(R.id.end_location);
		Intent intent = new Intent(android.content.Intent.ACTION_VIEW, 
			    Uri.parse("http://maps.google.com/maps?saddr=" + begBox.getText() + "&daddr=" + endBox.getText()));
			startActivity(intent);
	}
}