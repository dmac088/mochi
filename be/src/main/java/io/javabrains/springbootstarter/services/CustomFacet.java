package io.javabrains.springbootstarter.services;

import org.apache.lucene.search.Query;
import org.hibernate.search.query.facet.Facet;

public class CustomFacet implements Facet {

	private Facet facet;

	
	@Override
	public String getFacetingName() {
		// TODO Auto-generated method stub
		return this.facet.getFacetingName();
	}

	public Facet getFacet() {
		return facet;
	}

	public void setFacet(Facet facet) {
		this.facet = facet;
	}

	@Override
	public String getFieldName() {
		// TODO Auto-generated method stub
		return this.facet.getFieldName();
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return this.facet.getValue();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return this.facet.getCount();
	}

	@Override
	public Query getFacetQuery() {
		// TODO Auto-generated method stub
		return this.facet.getFacetQuery();
	}

}
