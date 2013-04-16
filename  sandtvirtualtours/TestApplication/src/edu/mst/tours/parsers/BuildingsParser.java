package edu.mst.tours.parsers;

import java.util.HashMap;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import android.content.Context;

import com.google.android.maps.GeoPoint;

import edu.mst.tours.model.Building;
import edu.mst.tours.xml.Raw;
import edu.mst.tours.xml.XMLUtils;

public abstract class BuildingsParser {
	
	private static final String BUILDINGS_XML = "buildings";
	private static HashMap<String, Building> ref;
	
	public static HashMap<String, Building> getBuildings(Context context){
		if (ref == null) ref = getBuildingsFromXML(context);
		return ref;
	}
	
	private static HashMap<String, Building> getBuildingsFromXML(Context context) {
		String xml = Raw.openRaw(context, BUILDINGS_XML);
		return parseXML(xml);
	}

	private static HashMap<String, Building> parseXML(String xml) {
		HashMap<String, Building> buildings = new HashMap<String, Building>();
		
		Element root = XMLUtils.getRoot(xml, "UTF-8");
		List<Node> nodeBuildings = XMLUtils.getChildren(root, "building");
		for (Node node : nodeBuildings) {
			Building b = new Building();
			b.setName(XMLUtils.getText(node, "name"));
			
			float lat = Float.valueOf(XMLUtils.getText(node, "lat"));
			float lng = Float.valueOf(XMLUtils.getText(node, "lng"));
			GeoPoint gp = new GeoPoint((int)(lat * 1E6), (int)(lng * 1E6));
			b.setLocation(gp);
			
			b.setImageURL(XMLUtils.getText(node, "img"));
			buildings.put(b.getName(), b);
		}
		
		return buildings;
	}
}
