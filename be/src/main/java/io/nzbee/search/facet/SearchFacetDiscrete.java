package io.nzbee.search.facet;

import java.util.Objects;
import org.apache.lucene.search.Query;
import org.hibernate.search.query.facet.Facet;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeName;

import io.nzbee.search.ISearchDimension;

@JsonTypeName("SearchFacet")
public class SearchFacetDiscrete implements IFacet, Facet {
	
	private final Facet delegate;
	
	private ISearchDimension entity;
	
	private String value;
	 
	public SearchFacetDiscrete(Facet f, ISearchDimension entity) {
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
	public String getDesc() {
		return ((ISearchDimension)this.getPayload()).getDesc();
	}

	@Override
	public Boolean isHierarchical() {
		return ((ISearchDimension)this.getPayload()).isHierarchical();
	}

	@JsonIgnore
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
	@JsonIgnore
	public String getType() {
		return "SimpleFacet";
	}

	@Override
	@JsonIgnore
	public String getObjectType() {
		return this.getPayload().getClass().getSimpleName();
	}
	
	@Override
	public boolean equals(Object o) {
		 if (this == o) return true;
	     if (o == null || getClass() != o.getClass()) return false;
	     SearchFacetDiscrete sf = (SearchFacetDiscrete) o;
	     return this.getValue().equals(sf.getValue());
	}

	@Override
	public int hashCode() {
		return Objects.hash(31);
	}

}