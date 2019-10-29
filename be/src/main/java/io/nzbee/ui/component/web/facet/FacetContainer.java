package io.nzbee.ui.component.web.facet;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.nzbee.domain.category.BrandCategory;
import io.nzbee.domain.category.ProductCategory;
import io.nzbee.domain.brand.Brand;
import io.nzbee.domain.product.Product;
import io.nzbee.domain.tag.Tag;

public class FacetContainer {

	
	private List<Facet> facets = new ArrayList<Facet>();

	@JsonIgnore
	public List<Facet> getFacets() {
		return facets;
	}

	public List<Facet> getProductCategories() {
		return this.facets.stream()
				.filter(f -> 
				f.getPayload().getClass().getSimpleName().equals(ProductCategory.class.getSimpleName()))
				.map(f -> (Facet) f)
				.collect(Collectors.toList());
	}
	
	public List<Facet> getBrandCategories() {
		return this.facets.stream()
				.filter(f -> 
				f.getPayload().getClass().getSimpleName().equals(BrandCategory.class.getSimpleName()))
				.map(f -> (Facet) f)
				.collect(Collectors.toList());
	}

	public List<Facet> getBrands() {
		return this.facets.stream()
				.filter(f -> 
				f.getPayload().getClass().getSimpleName().equals(Brand.class.getSimpleName()))
				.map(f -> (Facet) f)
				.collect(Collectors.toList());
	}

	public List<Facet> getTags() {
		return this.facets.stream()
				.filter(f -> 
				f.getPayload().getClass().getSimpleName().equals(Tag.class.getSimpleName()))
				.map(f -> (Facet) f)
				.collect(Collectors.toList());
	}

	public List<Facet> getPrices() {
		return this.facets.stream()
				.filter(f -> 
				f.getPayload().getClass().getSimpleName().equals(Product.class.getSimpleName() 
														  + ".productMarkdown"
														   ))
				.map(f -> (Facet) f)
				.collect(Collectors.toList());
	}
	
	public void setCategories(List<Facet> categories) {
		this.facets.addAll(categories);
	}
	
	public void setBrands(List<Facet> brands) {
		this.facets.addAll(brands);
	}
	
	public void setTags(List<Facet> tags) {
		this.facets.addAll(tags);
	}
	
	public void setPrices(List<Facet> prices) {
		this.facets.addAll(prices);
	}
	
	public void setProductCategories(List<Facet> productCategories) {
		this.facets.addAll(productCategories);
	}
	
	public void setBrandCategories(List<Facet> brandCategories) {
		this.facets.addAll(brandCategories);
	}
	
	public void setFacets(List<Facet> collect) {
		this.facets = collect;
	}
}
