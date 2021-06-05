package io.nzbee.search.facet;

import java.util.HashSet;
import java.util.Set;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.springframework.context.ApplicationContext;

import io.nzbee.enums.FacetNameEnum;
import io.nzbee.search.ISearchDimensionService;

public class SearchFacetHelper {

	private FacetNameEnum facetingName;
	
	private String type;
	
	private Set<String> codes = new HashSet<String>();

	public String getFacetingName() {
		return facetingName.toString();
	}

	public void setFacetingName(FacetNameEnum facetingName) {
		this.facetingName = facetingName;
	}

	public Set<String> getCodes() {
		return codes;
	}

	public void setCodes(Set<String> codes) {
		this.codes = codes;
	}
	
	@SuppressWarnings("rawtypes")
	public ISearchDimensionService getBean(ApplicationContext appContext) {
		System.out.println(appContext.getBean(this.getFacetingName() + "FacetService").toString());
		return (ISearchDimensionService) appContext.getBean(this.getFacetingName() + "FacetService");
	}
	
	public String getType() {
		return this.type;
	}
	
	public void setType(String type) {
		this.type = type;
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
	      eb.append(this.type, that.type);
	      return eb.isEquals();
	}
	
}
