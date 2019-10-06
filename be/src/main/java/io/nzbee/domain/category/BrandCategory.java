package io.nzbee.domain.category;

import java.util.List;

import io.nzbee.domain.brand.Brand;

public class BrandCategory extends Category {


	public BrandCategory() {
		super();
	}

	private List<Brand> brands;
	
	public List<Brand> getBrands() {
		return brands;
	}

	public void setBrands(List<Brand> brands) {
		this.brands = brands;
	}

	
}
