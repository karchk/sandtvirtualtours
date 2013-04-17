package edu.mst.tours.adapters;

import android.content.Context;
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
            holder.tvAnswer = (TextView) row.findViewById(R.faqentryrow.tvanswer);
            
            row.setTag(holder);
        }else{
            holder = (FAQEntryHolder) row.getTag();
            holder.tvAnswer.setVisibility(View.INVISIBLE);
        }

        FAQEntry entry = (FAQEntry) getItem(position);

        holder.tvQuestion.setText(entry.getQuestion());
        holder.tvAnswer.setText(entry.getAnswer());
        
        row.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				TextView tvAnswer = (TextView) v.findViewById(R.faqentryrow.tvanswer);
				int visibility = tvAnswer.getVisibility() == View.INVISIBLE ? View.VISIBLE : View.INVISIBLE;
				tvAnswer.setVisibility(visibility);
			}
		});
        return row;
	}

}

class FAQEntryHolder
{
	TextView tvQuestion;
    TextView tvAnswer;
}
