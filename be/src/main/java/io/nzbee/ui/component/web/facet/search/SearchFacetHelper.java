package io.nzbee.ui.component.web.facet.search;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.context.ApplicationContext;

import io.nzbee.domain.IService;

public class SearchFacetHelper {

	private String facetingName;
	
	private Set<String> codes = new HashSet<String>();

	public String getFacetingName() {
		return facetingName;
	}

	public void setFacetingName(String facetingName) {
		this.facetingName = facetingName;
	}

	public Set<String> getCodes() {
		return codes;
	}

	public void setCodes(Set<String> codes) {
		this.codes = codes;
	}
	
	@SuppressWarnings("rawtypes")
	public IService getBean(ApplicationContext appContext) {
		return (IService) appContext.getBean(this.getFacetingName() + "DomainService");
	}

	@Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder();
        hcb.append(facetingName);
        return hcb.toHashCode();
    }
 
	@Override
	public boolean equals(Object obj) {
	    if (this == obj) {
	        return true;
        }
	    if (!(obj instanceof SearchFacetHelper)) {
	            return false;
	    }
	    SearchFacetHelper that = (SearchFacetHelper) obj;
	      EqualsBuilder eb = new EqualsBuilder();
	      eb.append(this.facetingName, that.facetingName);
	      eb.append(this.codes, that.codes);
	      return eb.isEquals();
	}
	
}
