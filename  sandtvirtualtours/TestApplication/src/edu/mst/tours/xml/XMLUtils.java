package edu.mst.tours.xml;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLUtils {

	public static Element getRoot(String xml, String charset){
		try{
			InputStream in = null;
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setValidating(false);
			byte[] bytes = xml.getBytes(charset);
			in = new ByteArrayInputStream(bytes);
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document dom = builder.parse(in);
			Element root = dom.getDocumentElement();
			if(root == null){
				throw new RuntimeException("Invalid XML");
			}
			return root;
		}catch(Exception e){
			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	public static String getText(Node node, String tag){
		Node n = getChild(node, tag);
		if(n != null){
			Node text = n.getFirstChild();
			if(text != null){
				String s = text.getNodeValue();
				return s.trim();
			}
		}
		return null;
	}
	
	public static String getText(Node node){
		Node text = node.getFirstChild();
		if(text != null){
			String s = text.getNodeValue();
			return s.trim();
		}
		return null;
	}
	
	public static List<Node> getChildren(Node node, String name){
		List<Node> children = new ArrayList<Node>();
		NodeList nodes = node.getChildNodes();
		if(nodes != null && nodes.getLength() >= 1){
			for (int i = 0; i < nodes.getLength(); i++) {
				Node n = nodes.item(i);
				if(name.equals(n.getNodeName())){
					children.add(n);
				}
			}
		}
		return children;
	}
	
	public static Node getChild(Node node, String tag){
		if(node == null) return null;
		
		NodeList childNodes = node.getChildNodes();
		if(childNodes == null) return null;
		
		for (int i = 0; i < childNodes.getLength(); i++) {
			Node item = childNodes.item(i);
			if(item != null){
				String name = item.getNodeName();
				if(tag.equalsIgnoreCase(name)){
					return item;
				}
			}
		}
		return null;
	}
}
