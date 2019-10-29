package io.nzbee.ui.component.web.facet;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import io.nzbee.domain.IDomainObject;
import io.nzbee.ui.component.web.facet.navigation.EntityFacet;
import io.nzbee.ui.component.web.facet.search.SearchFacet;


@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY)
@JsonSubTypes({
    @JsonSubTypes.Type(value = SearchFacet.class, name = "SearchFacet"),

    @JsonSubTypes.Type(value = EntityFacet.class, name = "EntityFacet") }
)
public abstract class Facet {

	private String name;

    public String getName() {
    	System.out.println(name);
        return name;
    }

    public void setName(String name) {
    	System.out.println(name);
        this.name = name;
    }
	
	abstract String getId();
	
	abstract String getDisplayValue();
	
	abstract boolean isHierarchical();

	@JsonIgnore
	public abstract IDomainObject getPayload();
	
	abstract String getType();
	
	abstract String getValue();
	
	abstract String getPayloadType();
	
}
