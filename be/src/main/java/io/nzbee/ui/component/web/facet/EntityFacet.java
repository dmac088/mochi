package io.nzbee.ui.component.web.facet;

import java.lang.annotation.Annotation;

import org.hibernate.search.annotations.Facet;
import org.hibernate.search.annotations.FacetEncodingType;

public class EntityFacet<T> implements Facet {
	  private final Facet delegate;
	  private final T entity;
	  
	  
	  public EntityFacet(Facet delegate, T entity) {
	    this.delegate = delegate;
	    this.entity = entity;
	  }

	  // delegate all Facet methods to the delegate

	  public T getEntity() {
	    return entity;
	  }

	@Override
	public Class<? extends Annotation> annotationType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String name() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String forField() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FacetEncodingType encoding() {
		// TODO Auto-generated method stub
		return null;
	}
	}