package io.nzbee.ui.component.web.facet;

import java.util.ArrayList;
import java.util.List;

import io.nzbee.domain.Brand;
import io.nzbee.domain.Category;
import io.nzbee.domain.Tag;

public class NavFacetContainer {

	
	private List<NavFacet<Category>> categories = new ArrayList<NavFacet<Category>>();
	
	private List<NavFacet<Brand>> brands = new ArrayList<NavFacet<Brand>>();

	private List<NavFacet<Tag>> tags = new ArrayList<NavFacet<Tag>>();

	public List<NavFacet<Category>> getCategories() {
		return categories;
	}

	public void setCategories(List<NavFacet<Category>> categories) {
		this.categories = categories;
	}

	public List<NavFacet<Brand>> getBrands() {
		return brands;
	}

	public void setBrands(List<NavFacet<Brand>> brands) {
		this.brands = brands;
	}

	public List<NavFacet<Tag>> getTags() {
		return tags;
	}

	public void setTags(List<NavFacet<Tag>> tags) {
		this.tags = tags;
	}	
}
