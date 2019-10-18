package io.nzbee.ui.component.web.search.facet;


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

public class SearchFacetContainer {

	
	private List<IFacet> facets = new ArrayList<IFacet>();

	@JsonIgnore
	public List<IFacet> getFacets() {
		return facets;
	}

	@SuppressWarnings("unchecked")
	public List<IFacet<ProductCategory>> getProductCategories() {
		return this.facets.stream()
				.filter(f -> 
				f.getEntity().getClass().getSimpleName().equals(ProductCategory.class.getSimpleName()))
				.map(f -> (SearchFacet<ProductCategory>) f)
				.collect(Collectors.toList());
	}
	
	@SuppressWarnings("unchecked")
	public List<IFacet<BrandCategory>> getBrandCategories() {
		return this.facets.stream()
				.filter(f -> 
				f.getEntity().getClass().getSimpleName().equals(BrandCategory.class.getSimpleName()))
				.map(f -> (SearchFacet<BrandCategory>) f)
				.collect(Collectors.toList());
	}

	@SuppressWarnings("unchecked")
	public List<IFacet<Brand>> getBrands() {
		return this.facets.stream()
				.filter(f -> 
				f.getEntity().getClass().getSimpleName().equals(Brand.class.getSimpleName()))
				.map(f -> (SearchFacet<Brand>) f)
				.collect(Collectors.toList());
	}

	@SuppressWarnings("unchecked")
	public List<IFacet<Tag>> getTags() {
		return this.facets.stream()
				.filter(f -> 
				f.getEntity().getClass().getSimpleName().equals(Tag.class.getSimpleName()))
				.map(f -> (SearchFacet<Tag>) f)
				.collect(Collectors.toList());
	}

	@SuppressWarnings("unchecked")
	public List<SearchFacet<Object>> getPrices() {
		return this.facets.stream()
				.filter(f -> 
				f.getEntity().getClass().getSimpleName().equals(Product.class.getSimpleName() 
														  + ".productMarkdown"
														   ))
				.map(f -> (SearchFacet<Object>) f).collect(Collectors.toList());
	}
	
	public void setCategories(List<IFacet<Category>> categories) {
		this.facets.addAll(categories);
	}
	
	public void setBrands(List<IFacet<Brand>> brands) {
		this.facets.addAll(brands);
	}
	
	public void setTags(List<IFacet<Tag>> tags) {
		this.facets.addAll(tags);
	}
	
	public void setPrices(List<IFacet<Object>> prices) {
		this.facets.addAll(prices);
	}
	
	public void setProductCategories(List<IFacet<ProductCategory>> productCategories) {
		this.facets.addAll(productCategories);
	}
	
	public void setBrandCategories(List<IFacet<BrandCategory>> brandCategories) {
		this.facets.addAll(brandCategories);
	}
	
	public void setFacets(List<IFacet> collect) {
		// TODO Auto-generated method stub
		this.facets = collect;
	}

	
}
