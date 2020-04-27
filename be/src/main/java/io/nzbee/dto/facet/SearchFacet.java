package io.nzbee.dto.facet;

import java.util.Objects;
import org.apache.lucene.search.Query;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.nzbee.domain.ISearchDimension;

@JsonTypeName("SearchFacet")
public class SearchFacet  implements org.hibernate.search.query.facet.Facet, IFacet {
	
	private final org.hibernate.search.query.facet.Facet delegate;
	
	private ISearchDimension entity;
	
	private String value;
	 
	public SearchFacet(org.hibernate.search.query.facet.Facet f, ISearchDimension entity) {
	  this.delegate = f;
	  this.entity = entity;
	  if(!(f==null)) {
		 this.value = f.getValue();
	  }
	}
	
	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return ((ISearchDimension)this.getPayload()).getCode();
	}

	@Override
	public String getDisplayValue() {
		// TODO Auto-generated method stub
		return ((ISearchDimension)this.getPayload()).getDesc();
	}

	@Override
	public boolean isHierarchical() {
		// TODO Auto-generated method stub
		return ((ISearchDimension)this.getPayload()).isHierarchical();
	}

	@Override
	public ISearchDimension getPayload() {
	   return this.entity;
	} 
	
	public void setPayload(ISearchDimension domainObject) {
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
	
	@Override
	public boolean equals(Object o) {
		 if (this == o) return true;
	     if (o == null || getClass() != o.getClass()) return false;
	     SearchFacet sf = (SearchFacet) o;
	     return this.getValue().equals(sf.getValue());
	}

	@Override
	public int hashCode() {
		return Objects.hash(31);
	}

}