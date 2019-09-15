package io.nzbee.domain.category;

import java.util.List;


public class BrandCategory extends Category {

	public BrandCategory(Long id, String categoryCode, String categoryDesc, Long categoryLevel, String categoryType,
			String lclCd, String parentCode, Long brandCount) {
		super(id, categoryCode, categoryDesc, categoryLevel, categoryType, lclCd, parentCode, brandCount);
		// TODO Auto-generated constructor stub
	}

	public BrandCategory() {
		super();
	}
//
//	private List<Brand> brands;
//	
//	public List<Brand> getBrands() {
//		return brands;
//	}
//
//	public void setBrands(List<Brand> brands) {
//		this.brands = brands;
//	}

	
}
