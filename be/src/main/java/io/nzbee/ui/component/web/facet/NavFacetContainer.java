package io.nzbee.ui.component.web.facet;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.nzbee.domain.brand.Brand;
import io.nzbee.domain.category.BrandCategory;
import io.nzbee.domain.category.Category;
import io.nzbee.domain.category.ProductCategory;
import io.nzbee.domain.product.Product;
import io.nzbee.domain.tag.Tag;

public class NavFacetContainer {

	
	private List<NavFacet<?>> facets = new ArrayList<NavFacet<?>>();

	@JsonIgnore
	public List<NavFacet<?>> getFacets() {
		return facets;
	}

	@SuppressWarnings("unchecked")
	public List<NavFacet<ProductCategory>> getProductCategories() {
		return this.facets.stream()
				.filter(f -> f.getFacetClassName().equals(ProductCategory.class.getSimpleName()))
				.map(f -> (NavFacet<ProductCategory>) f)
				.collect(Collectors.toList());
	}
	
	@SuppressWarnings("unchecked")
	public List<NavFacet<BrandCategory>> getBrandCategories() {
		return this.facets.stream()
				.filter(f -> f.getFacetClassName().equals(BrandCategory.class.getSimpleName()))
				.map(f -> (NavFacet<BrandCategory>) f)
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
	
	public void setProductCategories(List<NavFacet<ProductCategory>> productCategories) {
		this.facets.addAll(productCategories);
	}
	
	public void setBrandCategories(List<NavFacet<BrandCategory>> brandCategories) {
		this.facets.addAll(brandCategories);
	}
	
	


	public void setFacets(List<NavFacet<?>> collect) {
		// TODO Auto-generated method stub
		this.facets = collect;
	}

	
}
