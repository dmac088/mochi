package io.nzbee.search.facet;

import java.util.Objects;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.springframework.context.ApplicationContext;

import io.nzbee.domain.IDomainService;

public class SearchFacetWithFieldHelper {

	private String type;
	
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
	
	public String getType() {
		return this.type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	@SuppressWarnings("rawtypes")
	public IDomainService getBean(ApplicationContext appContext) {
		return (IDomainService) appContext.getBean(this.getFacetingName() + "EntityService");
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
