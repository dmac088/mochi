package io.nzbee.search.facet;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import io.nzbee.domain.category.BrandCategory;
import io.nzbee.domain.category.ProductCategory;
import io.nzbee.domain.brand.Brand;
import io.nzbee.domain.product.Product;
import io.nzbee.domain.promotion.Promotion;
import io.nzbee.domain.tag.Tag;

public class FacetContainer {

	
	private Set<IFacet> facets = new HashSet<IFacet>();

	//@JsonIgnore
	public Set<IFacet> getFacets() {
		return facets;
	}

	public Set<IFacet> getProductCategories() {
		return this.facets.stream()
				.filter(f -> 
				f.getObjectType().equals(ProductCategory.class.getSimpleName()))
				.map(f -> (IFacet) f)
				.collect(Collectors.toSet());
	}
	
	public Set<IFacet> getBrandCategories() {
		return this.facets.stream()
				.filter(f -> 
				f.getObjectType().equals(BrandCategory.class.getSimpleName()))
				.map(f -> (IFacet) f)
				.collect(Collectors.toSet());
	}

	public Set<IFacet> getBrands() {
		return this.facets.stream()
				.filter(f -> 
				f.getObjectType().equals(Brand.class.getSimpleName()))
				.map(f -> (IFacet) f)
				.collect(Collectors.toSet());
	}

	public Set<IFacet> getTags() {
		return this.facets.stream()
				.filter(f -> 
				f.getObjectType().getClass().getSimpleName().equals(Tag.class.getSimpleName()))
				.map(f -> (IFacet) f)
				.collect(Collectors.toSet());
	}
	
	public Set<IFacet> getPromotions() {
		return this.facets.stream()
				.filter(f -> 
				f.getObjectType().getClass().getSimpleName().equals(Promotion.class.getSimpleName()))
				.map(f -> (IFacet) f)
				.collect(Collectors.toSet());
	}

	public Set<IFacet> getPrices() {
		return this.facets.stream()
				.filter(f -> 
				f.getObjectType().getClass().getSimpleName().equals(Product.class.getSimpleName() 
														  + ".productMarkdown"
														   ))
				.map(f -> (IFacet) f)
				.collect(Collectors.toSet());
	}
	
	public void setCategories(Set<IFacet> categories) {
		this.facets.addAll(categories);
	}
	
	public void setBrands(Set<IFacet> brands) {
		this.facets.addAll(brands);
	}
	
	public void setTags(Set<IFacet> tags) {
		this.facets.addAll(tags);
	}
	
	public void setPrices(Set<IFacet> prices) {
		this.facets.addAll(prices);
	}
	
	public void setProductCategories(Set<IFacet> productCategories) {
		this.facets.addAll(productCategories);
	}
	
	public void setBrandCategories(Set<IFacet> brandCategories) {
		this.facets.addAll(brandCategories);
	}
	
	public void setFacets(Set<IFacet> collect) {
		this.facets = collect;
	}
}
