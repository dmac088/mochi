package io.nzbee.search.dto.facet;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import io.nzbee.search.ISearchDimension;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(
	    use = JsonTypeInfo.Id.NAME,
	    include = JsonTypeInfo.As.PROPERTY,
	    property="type")
@JsonSubTypes( {@JsonSubTypes.Type(value = SearchFacet.class, name = "SearchFacet"),
			    @JsonSubTypes.Type(value = EntityFacet.class, name = "EntityFacet")})
public interface IFacet {

	public String getId();
	
	public String getDesc();
	
	public boolean isHierarchical();

	@JsonIgnore
	public ISearchDimension getPayload();
	
	public String getType();
	
	public String getPayloadType();

	public String getValue();
	
	public String getFacetingName();
	
	public int getCount();
	
}
