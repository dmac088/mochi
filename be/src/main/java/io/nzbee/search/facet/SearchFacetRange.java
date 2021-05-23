package io.nzbee.search.facet;

import java.util.Objects;
import org.apache.lucene.search.Query;
import org.hibernate.search.query.facet.Facet;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeName;


@JsonTypeName("SearchFacet")
public class SearchFacetRange  implements Facet, IFacet {
	
	private final Facet delegate;
	
	private String value;
	 
	public SearchFacetRange(Facet f) {
	  this.delegate = f;
	  if(!(f==null)) {
		 this.value = f.getValue();
	  }
	}
	
	@Override
	public String getId() {
		return value.toString();
	}

	@Override
	public String getDesc() {
		return value.toString();
	}

	@Override
	public Boolean isHierarchical() {
		return false;
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
		return "RangeFacetImpl";
	}

	@Override
	@JsonIgnore
	public String getObjectType() {
		return this.value.getClass().getSimpleName();
	}
	
	@Override
	public boolean equals(Object o) {
		 if (this == o) return true;
	     if (o == null || getClass() != o.getClass()) return false;
	     SearchFacetRange sf = (SearchFacetRange) o;
	     return this.getValue().equals(sf.getValue());
	}

	@Override
	public int hashCode() {
		return Objects.hash(31);
	}

}