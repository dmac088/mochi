package io.nzbee.ui.component.web.search.facet;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.nzbee.domain.category.BrandCategory;
import io.nzbee.domain.category.ProductCategory;
import io.nzbee.domain.brand.Brand;
import io.nzbee.domain.product.Product;
import io.nzbee.domain.tag.Tag;

public class SearchFacetContainer {

	
	private List<IFacet> facets = new ArrayList<IFacet>();

	@JsonIgnore
	public List<IFacet> getFacets() {
		return facets;
	}

	public List<IFacet> getProductCategories() {
		return this.facets.stream()
				.filter(f -> 
				f.getPayload().getClass().getSimpleName().equals(ProductCategory.class.getSimpleName()))
				.map(f -> (IFacet) f)
				.collect(Collectors.toList());
	}
	
	public List<IFacet> getBrandCategories() {
		return this.facets.stream()
				.filter(f -> 
				f.getPayload().getClass().getSimpleName().equals(BrandCategory.class.getSimpleName()))
				.map(f -> (IFacet) f)
				.collect(Collectors.toList());
	}

	public List<IFacet> getBrands() {
		return this.facets.stream()
				.filter(f -> 
				f.getPayload().getClass().getSimpleName().equals(Brand.class.getSimpleName()))
				.map(f -> (IFacet) f)
				.collect(Collectors.toList());
	}

	public List<IFacet> getTags() {
		return this.facets.stream()
				.filter(f -> 
				f.getPayload().getClass().getSimpleName().equals(Tag.class.getSimpleName()))
				.map(f -> (IFacet) f)
				.collect(Collectors.toList());
	}

	public List<IFacet> getPrices() {
		return this.facets.stream()
				.filter(f -> 
				f.getPayload().getClass().getSimpleName().equals(Product.class.getSimpleName() 
														  + ".productMarkdown"
														   ))
				.map(f -> (IFacet) f).collect(Collectors.toList());
	}
	
	public void setCategories(List<IFacet> categories) {
		this.facets.addAll(categories);
	}
	
	public void setBrands(List<IFacet> brands) {
		this.facets.addAll(brands);
	}
	
	public void setTags(List<IFacet> tags) {
		this.facets.addAll(tags);
	}
	
	public void setPrices(List<IFacet> prices) {
		this.facets.addAll(prices);
	}
	
	public void setProductCategories(List<IFacet> productCategories) {
		this.facets.addAll(productCategories);
	}
	
	public void setBrandCategories(List<IFacet> brandCategories) {
		this.facets.addAll(brandCategories);
	}
	
	public void setFacets(List<IFacet> collect) {
		// TODO Auto-generated method stub
		this.facets = collect;
	}

	
}
