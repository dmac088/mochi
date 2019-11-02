package io.nzbee.helpers;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class StringPair {

	private String valueA;
	
	private String valueB;

	public String getValueA() {
		return valueA;
	}

	public void setValueA(String valueA) {
		this.valueA = valueA;
	}

	public String getValueB() {
		return valueB;
	}

	public void setValueB(String valueB) {
		this.valueB = valueB;
	}
	
	@Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder();
        hcb.append(valueA);
        hcb.append(valueB);
        return hcb.toHashCode();
    }
 
	@Override
	public boolean equals(Object obj) {
	    if (this == obj) {
	        return true;
        }
	    if (!(obj instanceof StringPair)) {
	            return false;
	    }
	    StringPair that = (StringPair) obj;
	      EqualsBuilder eb = new EqualsBuilder();
	      eb.append(this.valueA, that.valueA);
	      eb.append(this.valueB, that.valueB);
	      return eb.isEquals();
	}
	
}
