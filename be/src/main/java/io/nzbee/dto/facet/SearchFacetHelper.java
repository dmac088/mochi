package io.nzbee.dto.facet;

import java.util.HashSet;
import java.util.Set;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.springframework.context.ApplicationContext;
import io.nzbee.domain.IProductDimensionService;

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
	public IProductDimensionService getBean(ApplicationContext appContext) {
		return (IProductDimensionService) appContext.getBean(this.getFacetingName() + "DomainService");
	}

	@Override
    public int hashCode() {
        return 31;
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
	      eb.append(this.getFacetingName(), that.getFacetingName());
	      eb.append(this.getCodes(), that.getCodes());
	      return eb.isEquals();
	}
	
}
