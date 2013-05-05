package edu.mst.tours.adapters;

import android.app.Dialog;
import android.content.Context;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import edu.mst.tours.R;
import edu.mst.tours.model.FAQ;
import edu.mst.tours.model.FAQEntry;

public class FAQListAdapter extends BaseAdapter {

	private static final int LAYOUT_RESOURCE = R.layout.faqentryrow;
	
	private LayoutInflater inflater;
	private FAQ faq;
	
	public FAQListAdapter(Context context, FAQ faq) {
		this.inflater = LayoutInflater.from(context);
		this.faq = faq;
	}
	
	@Override
	public int getCount() {
		return faq.getEntries().size();
	}

	@Override
	public Object getItem(int position) {
		return faq.getEntries().toArray()[position];
	}

	@Override
	public long getItemId(int position) {
		return faq.getEntries().toArray()[position].hashCode();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		FAQEntryHolder holder = null;
        
        if(row == null){
            row = inflater.inflate(LAYOUT_RESOURCE, parent, false);
            
            holder = new FAQEntryHolder();
            holder.tvQuestion = (TextView) row.findViewById(R.faqentryrow.tvquestion);
            
            row.setTag(holder);
        }else{
            holder = (FAQEntryHolder) row.getTag();
        }

        final FAQEntry entry = (FAQEntry) getItem(position);

        holder.tvQuestion.setText(entry.getQuestion());
        
        row.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Dialog d = new Dialog(v.getContext());
				TextView tv = new TextView(v.getContext());
				tv.setText(entry.getAnswer());
				d.setTitle(entry.getQuestion());
				TextView tv_title = ((TextView) d.findViewById(android.R.id.title));
				tv_title.setLines(3);
				tv_title.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
				tv.setPadding(10, 10, 10, 10);
				d.setContentView(tv);
				d.show();
			}
		});
        return row;
	}

}

class FAQEntryHolder
{
	TextView tvQuestion;
}
