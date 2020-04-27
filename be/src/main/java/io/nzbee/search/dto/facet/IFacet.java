package io.nzbee.search.dto.facet;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import io.nzbee.domain.ISearchDimension;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(
	    use = JsonTypeInfo.Id.NAME,
	    include = JsonTypeInfo.As.PROPERTY,
	    property="type")
@JsonSubTypes( {@JsonSubTypes.Type(value = SearchFacet.class, name = "SearchFacet"),
			    @JsonSubTypes.Type(value = EntityFacet.class, name = "EntityFacet")})
public interface IFacet {

	String getId();
	
	String getDisplayValue();
	
	boolean isHierarchical();

	@JsonIgnore
	ISearchDimension getPayload();
	
	String getType();
	
	String getPayloadType();

	String getValue();
	
	String getFacetingName();
	
	int getCount();
	
}
