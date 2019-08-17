package io.nzbee.ui.component.web.facet;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import io.nzbee.domain.Brand;
import io.nzbee.domain.Category;
import io.nzbee.domain.Product;
import io.nzbee.domain.Tag;

public class NavFacetContainer {

	
	private List<NavFacet<?>> facets = new ArrayList<NavFacet<?>>();

	public List<NavFacet<?>> getFacets() {
		return facets;
	}

	@SuppressWarnings("unchecked")
	public List<NavFacet<Category>> getCategories() {
		return this.facets.stream()
				.filter(f -> f.getFacetClassName().equals(Category.class.getSimpleName()))
				.map(f -> (NavFacet<Category>) f)
				.collect(Collectors.toList());
	}

	@SuppressWarnings("unchecked")
	public List<NavFacet<Brand>> getBrands() {
		return this.facets.stream()
				.filter(f -> f.getFacetClassName().equals(Brand.class.getSimpleName()))
				.map(f -> (NavFacet<Brand>) f)
				.collect(Collectors.toList());
	}

	@SuppressWarnings("unchecked")
	public List<NavFacet<Tag>> getTags() {
		return this.facets.stream()
				.filter(f -> f.getFacetClassName().equals(Tag.class.getSimpleName()))
				.map(f -> (NavFacet<Tag>) f)
				.collect(Collectors.toList());
	}

	@SuppressWarnings("unchecked")
	public List<NavFacet<Object>> getPrices() {
		return this.facets.stream()
				.filter(f -> f.getFacetClassName().equals(Product.class.getSimpleName() 
														  + ".productMarkdown"
														   ))
				.map(f -> (NavFacet<Object>) f).collect(Collectors.toList());
	}
	
	public void setCategories(List<NavFacet<Category>> categories) {
		this.facets.addAll(categories);
	}
	
	public void setBrands(List<NavFacet<Brand>> brands) {
		this.facets.addAll(brands);
	}
	
	public void setTags(List<NavFacet<Tag>> tags) {
		this.facets.addAll(tags);
	}
	
	public void setPrices(List<NavFacet<Object>> prices) {
		this.facets.addAll(prices);
	}


	public void setFacets(List<NavFacet<?>> collect) {
		// TODO Auto-generated method stub
		this.facets = collect;
	}

	
}
