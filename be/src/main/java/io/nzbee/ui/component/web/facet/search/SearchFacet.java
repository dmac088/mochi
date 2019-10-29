package io.nzbee.ui.component.web.facet.search;

import org.apache.lucene.search.Query;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.nzbee.domain.IDomainObject;
import io.nzbee.ui.component.web.facet.Facet;

public class SearchFacet extends Facet implements org.hibernate.search.query.facet.Facet {
	
	private final org.hibernate.search.query.facet.Facet delegate;
	private final IDomainObject entity;
	 
	public SearchFacet(org.hibernate.search.query.facet.Facet f, IDomainObject entity) {
	  this.delegate = f;
	  this.entity = entity;
	  this.setName(this.getClass().getSimpleName());
	}
	
	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return ((IDomainObject)this.getPayload()).getCode();
	}

	@Override
	public String getDisplayValue() {
		// TODO Auto-generated method stub
		return ((IDomainObject)this.getPayload()).getDesc() + " (" + this.getCount() + ")";
	}

	@Override
	public boolean isHierarchical() {
		// TODO Auto-generated method stub
		return ((IDomainObject)this.getPayload()).isHierarchical();
	}

	@Override
	@JsonIgnore
	public IDomainObject getPayload() {
	   return this.entity;
	} 
	
	@Override
	public String getFacetingName() {
		// TODO Auto-generated method stub
		return delegate.getFacetingName();
	}

	@Override
	public String getFieldName() {
		// TODO Auto-generated method stub
		return delegate.getFieldName();
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return delegate.getValue();
	}

	@Override
	@JsonIgnore
	public int getCount() {
		// TODO Auto-generated method stub
		return delegate.getCount();
	}

	@Override
	public Query getFacetQuery() {
		// TODO Auto-generated method stub
		return delegate.getFacetQuery();
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return this.getClass().getSimpleName();
	}

	@Override
	public String getPayloadType() {
		// TODO Auto-generated method stub
		return this.getPayload().getClass().getSimpleName();
	}

}