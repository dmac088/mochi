package io.nzbee.ui.component.web.facet;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.nzbee.domain.category.BrandCategory;
import io.nzbee.domain.category.ProductCategory;
import io.nzbee.domain.brand.Brand;
import io.nzbee.domain.category.Category;
import io.nzbee.domain.product.Product;
import io.nzbee.domain.tag.Tag;

public class NavFacetContainer {

	
	private List<EntityFacet<?>> facets = new ArrayList<EntityFacet<?>>();

	@JsonIgnore
	public List<EntityFacet<?>> getFacets() {
		return facets;
	}

	@SuppressWarnings("unchecked")
	public List<EntityFacet<ProductCategory>> getProductCategories() {
		return this.facets.stream()
				.filter(f -> 
				f.getEntity().getClass().getSimpleName().equals(ProductCategory.class.getSimpleName()))
				.map(f -> (EntityFacet<ProductCategory>) f)
				.collect(Collectors.toList());
	}
	
	@SuppressWarnings("unchecked")
	public List<EntityFacet<BrandCategory>> getBrandCategories() {
		return this.facets.stream()
				.filter(f -> 
				f.getEntity().getClass().getSimpleName().equals(BrandCategory.class.getSimpleName()))
				.map(f -> (EntityFacet<BrandCategory>) f)
				.collect(Collectors.toList());
	}

	@SuppressWarnings("unchecked")
	public List<EntityFacet<Brand>> getBrands() {
		return this.facets.stream()
				.filter(f -> 
				f.getEntity().getClass().getSimpleName().equals(Brand.class.getSimpleName()))
				.map(f -> (EntityFacet<Brand>) f)
				.collect(Collectors.toList());
	}

	@SuppressWarnings("unchecked")
	public List<EntityFacet<Tag>> getTags() {
		return this.facets.stream()
				.filter(f -> 
				f.getEntity().getClass().getSimpleName().equals(Tag.class.getSimpleName()))
				.map(f -> (EntityFacet<Tag>) f)
				.collect(Collectors.toList());
	}

	@SuppressWarnings("unchecked")
	public List<EntityFacet<Object>> getPrices() {
		return this.facets.stream()
				.filter(f -> 
				f.getEntity().getClass().getSimpleName().equals(Product.class.getSimpleName() 
														  + ".productMarkdown"
														   ))
				.map(f -> (EntityFacet<Object>) f).collect(Collectors.toList());
	}
	
	public void setCategories(List<EntityFacet<Category>> categories) {
		this.facets.addAll(categories);
	}
	
	public void setBrands(List<EntityFacet<Brand>> brands) {
		this.facets.addAll(brands);
	}
	
	public void setTags(List<EntityFacet<Tag>> tags) {
		this.facets.addAll(tags);
	}
	
	public void setPrices(List<EntityFacet<Object>> prices) {
		this.facets.addAll(prices);
	}
	
	public void setProductCategories(List<EntityFacet<ProductCategory>> productCategories) {
		this.facets.addAll(productCategories);
	}
	
	public void setBrandCategories(List<EntityFacet<BrandCategory>> brandCategories) {
		this.facets.addAll(brandCategories);
	}
	
	public void setFacets(List<EntityFacet<?>> collect) {
		// TODO Auto-generated method stub
		this.facets = collect;
	}

	
}
