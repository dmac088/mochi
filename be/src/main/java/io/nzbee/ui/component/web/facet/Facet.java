package io.nzbee.ui.component.web.facet;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.nzbee.domain.IDomainObject;
import io.nzbee.ui.component.web.facet.navigation.EntityFacet;
import io.nzbee.ui.component.web.facet.search.SearchFacet;


@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(
	    use = JsonTypeInfo.Id.MINIMAL_CLASS,
	    include = JsonTypeInfo.As.PROPERTY,
	    property = "@class")
//@JsonSubTypes({
//    @JsonSubTypes.Type(value = SearchFacet.class, name = "SearchFacet"),
//
//    @JsonSubTypes.Type(value = EntityFacet.class, name = "EntityFacet") }
//)
public abstract class Facet {

	public abstract String getId();
	
	public abstract String getDisplayValue();
	
	public abstract boolean isHierarchical();

	@JsonIgnore
	public abstract IDomainObject getPayload();
	
	public abstract String getType();
	
	public abstract String getPayloadType();
	
}
