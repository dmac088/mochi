package io.nzbee.domain;

import java.util.List;

public class BrandCategory extends Category {

	private List<Brand> brands;

	private Long brandCount;
	
	public Long getBrandCount() {
		return brandCount;
	}

	public void setBrandCount(Long brandCount) {
		this.brandCount = brandCount;
	}

	public List<Brand> getBrands() {
		return brands;
	}

	public void setBrands(List<Brand> brands) {
		this.brands = brands;
	}

	@Override
	public Long getCount() {
		// TODO Auto-generated method stub
		return brandCount;
	}

	@Override
	public void setCount(Long brandCount) {
		this.brandCount = brandCount;
	}
	
}
