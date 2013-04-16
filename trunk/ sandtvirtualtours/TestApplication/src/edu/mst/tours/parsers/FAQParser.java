package edu.mst.tours.parsers;

import java.util.HashSet;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import android.content.Context;
import edu.mst.tours.model.FAQ;
import edu.mst.tours.model.FAQEntry;
import edu.mst.tours.xml.Raw;
import edu.mst.tours.xml.XMLUtils;

public class FAQParser {
	
	private static final String FAQ_XML = "faq";
	
	private Context context;
	
	public FAQParser(Context context) {
		this.context = context;
	}
	
	public FAQ getFAQ() {
		String xml = Raw.openRaw(context, FAQ_XML);
		return parseXML(xml);
	}

	private FAQ parseXML(String xml) {
		HashSet<FAQEntry> entries = new HashSet<FAQEntry>();
		
		Element root = XMLUtils.getRoot(xml, "UTF-8");
		List<Node> nodeEntries = XMLUtils.getChildren(root, "entry");
		for (Node node : nodeEntries) {
			FAQEntry entry = new FAQEntry();
			entry.setQuestion(XMLUtils.getText(node, "question"));
			entry.setAnswer(XMLUtils.getText(node, "answer"));
			entries.add(entry);
		}
		
		return new FAQ(entries);
	}
}