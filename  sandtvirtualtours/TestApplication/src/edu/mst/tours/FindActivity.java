package edu.mst.tours;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class FindActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_find);
		
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
}
