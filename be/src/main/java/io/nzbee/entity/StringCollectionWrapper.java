package io.nzbee.entity;

import java.util.Set;
import java.util.TreeSet;
import org.apache.tomcat.util.buf.StringUtils;

public class StringCollectionWrapper {
	private Set<String> codes;

	public StringCollectionWrapper(Set<String> codes) {
	    super();
	    this.codes = codes;
	}

	public String getCacheKey(){
		return StringUtils.join(new TreeSet<String>(codes));
	}
	
	public Set<String> getCodes() {
		return codes;
	}
}
