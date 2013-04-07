package edu.mst.tours;

import java.util.HashSet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;
import edu.mst.tours.model.Building;
import edu.mst.tours.parsers.LocationsParser;

public class MainActivity extends Activity {

	public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        LocationsParser l = new LocationsParser();
        HashSet<Building> buildings = l.getBuildings(this);
        Toast.makeText(this, buildings.toString(), Toast.LENGTH_LONG).show();
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public void goToTour(View view) {
    	Intent intent = new Intent(this, DisplayMessageActivity.class);
    	startActivity(intent);
    }
    
    public void goToFAQ(View view) {
    	Intent intent = new Intent(this, FAQActivity.class);
    	startActivity(intent);
    }
    
    public void goToDirections(View view) {
    	Intent intent = new Intent(this, DirectionsActivity.class);
    	startActivity(intent);
    }
    
}
