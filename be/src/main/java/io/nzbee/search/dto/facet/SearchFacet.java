package io.nzbee.search.dto.facet;

import java.util.Objects;
import org.apache.lucene.search.Query;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
		return ((ISearchDimension)this.getPayload()).getCode();
	}

	@Override
	public String getDisplayValue() {
		return ((ISearchDimension)this.getPayload()).getDesc();
	}

	@Override
	public boolean isHierarchical() {
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
		return delegate.getFacetingName();
	}

	@Override
	public String getFieldName() {
		return delegate.getFieldName();
	}

	@Override
	public String getValue() {
		return value;
	}

	@Override
	public int getCount() {
		return delegate.getCount();
	}
	
	@Override
	@JsonIgnore
	public Query getFacetQuery() {
		return delegate.getFacetQuery();
	}

	@Override
	public String getType() {
		return this.getClass().getSimpleName();
	}

	@Override
	public String getPayloadType() {
		return this.getPayload().getClass().getSimpleName();
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