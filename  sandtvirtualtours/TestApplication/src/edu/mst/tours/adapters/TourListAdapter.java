package edu.mst.tours.adapters;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
import edu.mst.tours.R;
import edu.mst.tours.model.Building;
import edu.mst.tours.view.DDListView;

public class TourListAdapter extends ArrayAdapter<Building> {
	private final static int LAYOUT_RESOURCE = R.layout.tourrow;
	
	private LayoutInflater inflater;
	private DDListView listView;
	private ArrayList<Building> mListContent;
	private ArrayList<Boolean> checkedList;
	
	public TourListAdapter(Context context, DDListView listView, ArrayList<Building> objects) {
		super(context, LAYOUT_RESOURCE, objects);
		inflater = LayoutInflater.from(context);
		this.listView = listView;
		mListContent = objects;
		checkedList = new ArrayList<Boolean>();
		for (int i = 0; i < mListContent.size(); i++) {
			checkedList.add(Boolean.valueOf(true));
		}
	}

	public View getView(final int position, View rowView, ViewGroup parent) {
		final View v = inflater.inflate(LAYOUT_RESOURCE, null);

		TextView rowTitle = (TextView) v.findViewById(R.tourrow.tv_name);
		rowTitle.setText(mListContent.get(position).getName());
		
		final CheckBox cbEnabled = (CheckBox) v.findViewById(R.tourrow.cb_active);
		cbEnabled.setChecked(checkedList.get(position).booleanValue());
		cbEnabled.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@SuppressLint("NewApi")
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				checkedList.set(position, Boolean.valueOf(isChecked));
				MotionEvent me = MotionEvent.obtain(100, 100, MotionEvent.ACTION_UP, 100, 100, 0);
				listView.onTouchEvent(me);
				me.recycle();
				notifyDataSetChanged();
			}
		});

		return v;
	}
	
	public ArrayList<Building> getOrderedList(){
		ArrayList<Building> blds = new ArrayList<Building>();
		for (int i = 0; i < mListContent.size(); i++) {
			if(checkedList.get(i).booleanValue()) blds.add(mListContent.get(i));
		}
		return blds;
	}

	public void setDropChange(int from, int to){
		mListContent.add(to, mListContent.remove(from));
		checkedList.add(to, checkedList.remove(from));
		notifyDataSetChanged();
	}
}
