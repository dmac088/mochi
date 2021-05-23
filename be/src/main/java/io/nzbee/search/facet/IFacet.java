package io.nzbee.search.facet;

import org.hibernate.search.query.facet.Facet;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(
	    use = JsonTypeInfo.Id.NAME,
	    include = JsonTypeInfo.As.PROPERTY,
	    property="type")
@JsonSubTypes( {@JsonSubTypes.Type(value = SearchFacetDiscrete.class, name = "SearchFacet"),
			    @JsonSubTypes.Type(value = EntityFacet.class, name = "EntityFacet")})
public interface IFacet extends Facet {

	public String getId();
	
	public String getDesc();
	
	public boolean isHierarchical();

	public String getType();
	
	public String getObjectType();

	
}
