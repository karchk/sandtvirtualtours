package edu.mst.tours;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

	public static final String TAG = "msttours";
	
	private Button bt_tour, bt_faq, bt_directions;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainactivity);
        
        loadViews();        
    }
    
    private void loadViews() {
    	bt_tour = (Button) findViewById(R.mainactivity.bt_tour);
    	bt_directions = (Button) findViewById(R.mainactivity.bt_directions);
    	bt_faq = (Button) findViewById(R.mainactivity.bt_faq);
    	
    	setOnClickActivityStart(bt_tour, TourActivity.class);
    	setOnClickActivityStart(bt_faq, FAQActivity.class);
    	setOnClickActivityStart(bt_directions, DirectionsActivity.class);
	}
    
    private void setOnClickActivityStart(View v, final Class<?> clazz) {
    	v.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				v.getContext().startActivity(new Intent(v.getContext(), clazz));
			}
		});
	}    
}