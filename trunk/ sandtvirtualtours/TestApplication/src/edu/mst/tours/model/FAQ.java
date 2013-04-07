package edu.mst.tours.model;

import java.util.HashSet;

public class FAQ {
	
	private HashSet<FAQEntry> entries;
	
	public FAQ(HashSet<FAQEntry> entries) {
		this.entries = entries;
	}
	
	public HashSet<FAQEntry> getEntries() {
		return entries;
	}

}
