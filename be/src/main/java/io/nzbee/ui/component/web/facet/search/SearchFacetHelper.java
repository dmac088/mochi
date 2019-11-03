package io.nzbee.ui.component.web.facet.search;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import io.nzbee.domain.IService;

public class SearchFacetHelper {

	private String facetingName;
	
	private String fieldName;

	public String getFacetingName() {
		return facetingName;
	}

	public void setFacetingName(String facetingName) {
		this.facetingName = facetingName;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	
	public IService getBean(ApplicationContext appContext) {
		
		return this.facetingName.equals("CategoryFR" )
				? (IService) appContext.getBean("categoryDomainService")
				: (IService) appContext.getBean("brandDomainService");
	}

	@Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder();
        hcb.append(facetingName);
        hcb.append(fieldName);
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
	      eb.append(this.fieldName, that.fieldName);
	      return eb.isEquals();
	}
	
}
