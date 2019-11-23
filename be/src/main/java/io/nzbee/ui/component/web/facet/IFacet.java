package io.nzbee.ui.component.web.facet;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import io.nzbee.domain.IDomainObject;
import io.nzbee.ui.component.web.facet.navigation.EntityFacet;
import io.nzbee.ui.component.web.facet.search.SearchFacet;

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

	IDomainObject getPayload();
	
	String getType();
	
	String getPayloadType();

	String getValue();
	
	int getCount();
	
}
