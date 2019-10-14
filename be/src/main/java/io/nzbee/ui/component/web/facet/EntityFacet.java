package io.nzbee.ui.component.web.facet;
import org.apache.lucene.search.Query;

public class EntityFacet<T> implements org.hibernate.search.query.facet.Facet {
	
	private final org.hibernate.search.query.facet.Facet delegate;
	private final T entity;
	  
	  
	public EntityFacet(org.hibernate.search.query.facet.Facet f, T entity) {
	  this.delegate = f;
	  this.entity = entity;
	}

	  // delegate all Facet methods to the delegate

	public T getEntity() {
	   return entity;
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