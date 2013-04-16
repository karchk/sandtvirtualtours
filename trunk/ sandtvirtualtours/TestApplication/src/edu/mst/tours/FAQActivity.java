package edu.mst.tours;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import edu.mst.tours.model.FAQ;
import edu.mst.tours.parsers.FAQParser;

public class FAQActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		FAQ faq = new FAQParser(this).getFAQ();
		
		Toast.makeText(this, faq.toString(), Toast.LENGTH_LONG).show();
		
		TextView textView = new TextView(this);
		textView.setTextSize(40);
		textView.setText("FAQ");
		setContentView(textView);
	}
}