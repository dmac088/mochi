package io.nzbee.ui.component.web.facet.search;

import java.util.Objects;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.context.ApplicationContext;

import io.nzbee.domain.IService;

public class SearchFacetWithFieldHelper {

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
	
	@SuppressWarnings("rawtypes")
	public IService getBean(ApplicationContext appContext) {
		return (IService) appContext.getBean(this.getFacetingName() + "DomainService");
	}

	@Override
	public boolean equals(Object obj) {
	    if (this == obj) {
	        return true;
        }
	    if (!(obj instanceof SearchFacetWithFieldHelper)) {
	            return false;
	    }
	    SearchFacetWithFieldHelper that = (SearchFacetWithFieldHelper) obj;
	      EqualsBuilder eb = new EqualsBuilder();
	      eb.append(this.getFacetingName(), that.getFacetingName());
	      eb.append(this.getFieldName(), that.getFieldName());
	      return eb.isEquals();
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(31);
	}
	
}
