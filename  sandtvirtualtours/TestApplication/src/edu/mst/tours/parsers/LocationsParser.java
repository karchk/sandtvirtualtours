package edu.mst.tours.parsers;

import java.util.HashSet;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import android.content.Context;
import android.view.View.OnClickListener;

import com.google.android.maps.GeoPoint;

import edu.mst.tours.model.Building;
import edu.mst.tours.xml.Raw;
import edu.mst.tours.xml.XMLUtils;

public class LocationsParser {
	
	private static final String BUILDINGS_XML = "gps_coordinates";
	
	public HashSet<Building> getBuildings(Context context) {
		String xml = Raw.openRaw(context, BUILDINGS_XML);
		return parseXML(xml);
	}

	private HashSet<Building> parseXML(String xml) {
		HashSet<Building> buildings = new HashSet<Building>();
		
		Element root = XMLUtils.getRoot(xml, "UTF-8");
		List<Node> nodeBuildings = XMLUtils.getChildren(root, "building");
		for (Node node : nodeBuildings) {
			Building b = new Building();
			b.setName(XMLUtils.getText(node, "name"));
			
			float lat = Float.valueOf(XMLUtils.getText(node, "lat"));
			float lng = Float.valueOf(XMLUtils.getText(node, "lng"));
			GeoPoint gp = new GeoPoint((int)(lat * 1E6), (int)(lng * 1E6));
			b.setLocation(gp);
			buildings.add(b);
		}
		
		return buildings;
	}
}
