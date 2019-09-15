package io.nzbee.dto.category;

import java.util.List;

import io.nzbee.dto.brand.Brand;

public class BrandCategory extends Category {

	public BrandCategory(Long id, String categoryCode, String categoryDesc, Long categoryLevel, String categoryType,
			String lclCd, String parentCode) {
		super(id, categoryCode, categoryDesc, categoryLevel, categoryType, lclCd, parentCode);
		// TODO Auto-generated constructor stub
	}

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
