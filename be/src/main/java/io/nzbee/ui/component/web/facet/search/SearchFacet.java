package io.nzbee.ui.component.web.facet.search;

import org.apache.lucene.search.Query;
import io.nzbee.domain.IDomainObject;
import io.nzbee.ui.component.web.facet.IFacet;

public class SearchFacet  implements org.hibernate.search.query.facet.Facet, IFacet {
	
	private final org.hibernate.search.query.facet.Facet delegate;
	
	private IDomainObject entity;
	
	private String value;
	 
	public SearchFacet(org.hibernate.search.query.facet.Facet f, IDomainObject entity) {
	  this.delegate = f;
	  this.entity = entity;
	  if(!(f==null)) {
		 this.value = f.getValue();
	  }
	}
	
	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return ((IDomainObject)this.getPayload()).getCode();
	}

	@Override
	public String getDisplayValue() {
		// TODO Auto-generated method stub
		return ((IDomainObject)this.getPayload()).getDesc() /*+ " (" + delegate.getCount() + ")"*/;
	}

	@Override
	public boolean isHierarchical() {
		// TODO Auto-generated method stub
		return ((IDomainObject)this.getPayload()).isHierarchical();
	}

	@Override
	public IDomainObject getPayload() {
	   return this.entity;
	} 
	
	public void setPayload(IDomainObject domainObject) {
		this.entity = domainObject;
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
		return value;
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

	public void setValue(String value) {
		this.value = value;
	}

}