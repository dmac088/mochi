package io.nzbee.domain.bag;

import java.util.HashMap;
import java.util.Map;

public class BagIssues {
	private Map<String, BagItem> bagErrors = new HashMap<String, BagItem>();
	
	public void logItemError(String key, BagItem BagItem) {
		bagErrors.put(key,  BagItem);
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (String key : bagErrors.keySet()) {
			sb.append(key).append(bagErrors.get(key)).append("\n");
		}
		return sb.toString();
	}
	
	public boolean hasIssues() {
		return !bagErrors.isEmpty();
	}
}
