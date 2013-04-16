package edu.mst.tours.parsers;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import android.content.Context;
import edu.mst.tours.model.Building;
import edu.mst.tours.model.Department;
import edu.mst.tours.xml.Raw;
import edu.mst.tours.xml.XMLUtils;

public class DepartmentsParser {
	
	private static final String DEPARTMENTS_XML = "departments";
	
	private HashMap<String, Building> buildings;
	private Context context;
	
	public DepartmentsParser(Context context) {
		this.context = context;
        buildings = BuildingsParser.getBuildings(context);
	}
	
	public HashSet<Department> getDepartments() {
		String xml = Raw.openRaw(context, DEPARTMENTS_XML);
		return parseXML(xml);
	}

	private HashSet<Department> parseXML(String xml) {
		HashSet<Department> departments = new HashSet<Department>();
		
		Element root = XMLUtils.getRoot(xml, "UTF-8");
		List<Node> nodeDepartments = XMLUtils.getChildren(root, "department");
		for (Node node : nodeDepartments) {
			String name = XMLUtils.getText(node, "name");
			String buildingName = XMLUtils.getText(node, "buildingname");
			
			Building building = buildings.get(buildingName);
			departments.add(new Department(name, building));
		}
		
		return departments;
	}
}
