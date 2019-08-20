package io.nzbee.domain;

import java.util.List;

public class BrandCategory extends Category {

	private List<Brand> brands;

	public List<Brand> getBrands() {
		return brands;
	}

	public void setBrands(List<Brand> brands) {
		this.brands = brands;
	}
	
}
