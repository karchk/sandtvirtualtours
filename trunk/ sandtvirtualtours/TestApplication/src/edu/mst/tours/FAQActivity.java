package edu.mst.tours;

import android.app.ListActivity;
import android.os.Bundle;
import edu.mst.tours.adapters.FAQListAdapter;
import edu.mst.tours.model.FAQ;
import edu.mst.tours.parsers.FAQParser;

public class FAQActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.faqactivity);
		
		FAQ faq = new FAQParser(this).getFAQ();
		
		setListAdapter(new FAQListAdapter(this, faq));
	}
}