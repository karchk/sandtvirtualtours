package edu.mst.tours;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import edu.mst.tours.asynctasks.GetBuildingImageTask;
import edu.mst.tours.model.Department;
import edu.mst.tours.parsers.DepartmentsParser;

public class FindActivity extends Activity {
	public final static String BUILDING_NAME = "edu.mst.tours.building";
	public final static String START_ADDR = "edu.mst.tours.start";

	private HashSet<Department> departments;
	
	private Spinner sp_departmentselector;
	private TextView tv_building;
	private Button bt_selectstartbuilding;
	private Button bt_selectendbuilding;
	private ImageView iv_image;
	private GetBuildingImageTask imageTask;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.findactivity);
		departments = new DepartmentsParser(this).getDepartments();
		loadViews();		
		
		sp_departmentselector.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				if(imageTask != null) imageTask.cancel(true);
				
				Department dept = null;
				for (Department department : departments) {
					if(department.getName().equals(parent.getSelectedItem().toString())){
						dept = department;
						break;
					}
				}
				
				imageTask = new GetBuildingImageTask(iv_image, dept.getBuilding());
				imageTask.execute();
				tv_building.setText(dept.getBuilding().getName());
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) { }
		});
	}

	private void loadViews() {
		sp_departmentselector = (Spinner) findViewById(R.findactivity.sp_departmentselector);
		ArrayList<String> departmentsNames = new ArrayList<String>();
		for (Department dept : departments) {
			departmentsNames.add(dept.getName());
		}
		Collections.sort(departmentsNames);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, departmentsNames);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp_departmentselector.setAdapter(adapter);
		tv_building = (TextView) findViewById(R.findactivity.tv_building);
		bt_selectstartbuilding = (Button) findViewById(R.findactivity.bt_selectstartbuilding);
		bt_selectendbuilding = (Button) findViewById(R.findactivity.bt_selectendbuildling);
		iv_image = (ImageView) findViewById(R.findactivity.iv_image);
		
		bt_selectstartbuilding.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startDirectionsActivity(true);
			}
		});
		
		bt_selectendbuilding.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startDirectionsActivity(false);
			}
		});
	}
	
	private void startDirectionsActivity(Boolean isStart) {
		Intent startDirections = new Intent(this, DirectionsActivity.class);
		String blding = tv_building.getText().toString();
		startDirections.putExtra(BUILDING_NAME, blding);
		startDirections.putExtra(START_ADDR, isStart);
		setResult(RESULT_OK, startDirections);
		finish();
	}
}
