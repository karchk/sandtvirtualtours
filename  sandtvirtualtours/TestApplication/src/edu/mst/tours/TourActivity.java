package edu.mst.tours;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class TourActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TextView textView = new TextView(this);
		textView.setTextSize(40);
		textView.setText("Tour");
		setContentView(textView);
	}
}