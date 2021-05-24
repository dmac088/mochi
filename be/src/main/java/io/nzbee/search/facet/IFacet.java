package io.nzbee.search.facet;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(
	    use = JsonTypeInfo.Id.NAME,
	    include = JsonTypeInfo.As.PROPERTY,
	    property="type")
@JsonSubTypes( {@JsonSubTypes.Type(value = SearchFacetDiscrete.class, name = "SearchFacet"),
			    @JsonSubTypes.Type(value = EntityFacet.class, name = "EntityFacet"),
			    @JsonSubTypes.Type(value = EntityFacetHierarchical.class, name = "EntityFacetHierarchical")})

public interface IFacet  {

	String getId();
	
	String getDesc();
	
	Boolean isHierarchical();
	
	String getType();
	
	String getObjectType();

	String getFacetingName();

	int getCount();

	String getValue();
	
}


