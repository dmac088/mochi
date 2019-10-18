package io.nzbee.ui.component.web.search.facet;
import org.apache.lucene.search.Query;

import io.nzbee.domain.IDomainObject;

public class SearchFacet<T> implements org.hibernate.search.query.facet.Facet, IFacet<T> {
	
	private final org.hibernate.search.query.facet.Facet delegate;
	private final IDomainObject<T> entity;
	 
	public SearchFacet(org.hibernate.search.query.facet.Facet f, IDomainObject<T> entity) {
	  this.delegate = f;
	  this.entity = entity;
	}
	
	@Override
	public String getCode() {
		// TODO Auto-generated method stub
		return ((IDomainObject<T>)this.getEntity()).getCode();
	}

	@Override
	public String getDesc() {
		// TODO Auto-generated method stub
		return ((IDomainObject<T>)this.getEntity()).getDesc();
	}

	@Override
	public boolean isHierarchical() {
		// TODO Auto-generated method stub
		return ((IDomainObject<T>)this.getEntity()).isHierarchical();
	}

	@Override
	public IDomainObject<T> getEntity() {
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
	public int getCount() {
		// TODO Auto-generated method stub
		return delegate.getCount();
	}

	@Override
	public Query getFacetQuery() {
		// TODO Auto-generated method stub
		return delegate.getFacetQuery();
	}

}